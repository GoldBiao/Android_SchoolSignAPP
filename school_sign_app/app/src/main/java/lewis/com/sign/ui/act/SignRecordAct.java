package lewis.com.sign.ui.act;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.bean.ComResult;
import lewis.com.sign.bean.KechengList;
import lewis.com.sign.bean.SrecordList;
import lewis.com.sign.utils.Contance;
import lewis.com.sign.utils.SimpleAdapter;
import okhttp3.Call;

public class SignRecordAct extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<SrecordList.DataBean> beanList=new ArrayList<>();
    private SimpleAdapter<SrecordList.DataBean> adapter;
    private KechengList.DataBean bean;
    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private AlertDialog alertDialog;

    @Override
    public int intiLayout() {
        return R.layout.act_list;
    }

    @Override
    public void initView() {
        Bundle extras = getIntent().getExtras();
        bean = (KechengList.DataBean) extras.getSerializable("bean");
        tvTitle.setText("签到情况");
        ivBack.setVisibility(View.VISIBLE);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //给列表绑定adapter并渲染数据
        adapter = new SimpleAdapter<SrecordList.DataBean>(R.layout.item_record, beanList, new SimpleAdapter.ConVert<SrecordList.DataBean>() {
            @Override
            public void convert(final BaseViewHolder helper, final SrecordList.DataBean dataBean) {

                helper.setText(R.id.tv_account,"学号："+dataBean.account);
                helper.setText(R.id.tv_name,"姓名："+dataBean.name+"    班级："+dataBean.banji );
                SrecordList.DataBean.SrecordBean srecord = dataBean.srecord;
                helper.setText(R.id.tv_time,"签到时间:"+(srecord==null?"无":srecord.time));
                CircleImageView iv = helper.getView(R.id.iv);
                Glide.with(SignRecordAct.this).load(dataBean.pic).into(iv);
                TextView tv_sign = helper.getView(R.id.tv_sign);
                TextView tv_status = helper.getView(R.id.tv_status);
                if (srecord==null){
                    tv_sign.setVisibility(View.VISIBLE);
                    tv_status.setText("未到");
                    helper.getView(R.id.tv_sign).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showDialog(dataBean.id+"");
                        }
                    });

                }else {
                    tv_sign.setVisibility(View.GONE);
                    Long rtime = Long.parseLong(getNumeric(srecord.time));
                    Long ktime = Long.parseLong(getNumeric(bean.time));
                    if (rtime>ktime){
                        tv_status.setText("迟到");
                    }else {
                        tv_status.setText("正常");
                    }
                }




            }
        });
        recycler.setAdapter(adapter);
    }
    public  String getNumeric(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    @Override
    public void initData() {
        getdata();
    }

    private void  showDialog(final String sid){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确定补签吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addRecord(sid);
                alertDialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
    private void addRecord(String sid) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        OkHttpUtils
                .post()
                .url(Contance.addRecord)
                .addParams("time",format)
                .addParams("kid",bean.id+"")
                .addParams("sid", sid)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ComResult login = new Gson().fromJson(response, ComResult.class);
                        if (login != null ) {

                            toast("成功");

                            getdata();
                        }
                    }
                });

    }
    //获取数据
    private void getdata(){
        beanList.clear();
        OkHttpUtils
                .post()
                .url(Contance.getAllStudentRecordBYKid)
                .addParams("kid",bean.id+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss",e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("sss",response);
                        final SrecordList comResult = new Gson().fromJson(response, SrecordList.class);
                        if (comResult!=null&&comResult.data!=null){
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    beanList.addAll(comResult.data);
                                    adapter.notifyDataSetChanged();
                                }
                            });



                        }
                    }
                });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
