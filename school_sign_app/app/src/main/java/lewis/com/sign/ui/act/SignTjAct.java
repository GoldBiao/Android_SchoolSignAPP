package lewis.com.sign.ui.act;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.bean.TjBean;
import lewis.com.sign.utils.Contance;
import lewis.com.sign.utils.SimpleAdapter;
import okhttp3.Call;

public class SignTjAct extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<TjBean.DataBean> beanList=new ArrayList<>();
    private SimpleAdapter<TjBean.DataBean> adapter;
    private String kname;

    @Override
    public int intiLayout() {
        return R.layout.act_list;
    }

    @Override
    public void initView() {
        Bundle extras = getIntent().getExtras();
        kname = extras.getString("name");
        tvTitle.setText("课程签到统计");
        tv_right.setText("图表统计");
        ivBack.setVisibility(View.VISIBLE);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //给列表绑定adapter并渲染数据
        adapter = new SimpleAdapter<TjBean.DataBean>(R.layout.item_tj, beanList, new SimpleAdapter.ConVert<TjBean.DataBean>() {
            @Override
            public void convert(final BaseViewHolder helper, final TjBean.DataBean dataBean) {

                helper.setText(R.id.tv_account,"学号："+dataBean.stucent.account);
                helper.setText(R.id.tv_name,"姓名："+dataBean.stucent.name+"    班级："+dataBean.stucent.banji );
                CircleImageView iv = helper.getView(R.id.iv);
                Glide.with(SignTjAct.this).load(dataBean.stucent.pic).into(iv);
                helper.setText(R.id.tv_total,"应到次数："+dataBean.total);
                helper.setText(R.id.tv_zcnum,"签到次数："+dataBean.zcnum);
                helper.setText(R.id.tv_cdnum,"迟到次数："+dataBean.cdnum);
                helper.setText(R.id.tv_wdnum,"未到次数："+dataBean.wdnum);
            }
        });
        recycler.setAdapter(adapter);
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("list",beanList);
                jumpAct(ChartAct.class,bundle);
            }
        });
    }

    @Override
    public void initData() {
        getdata();
    }
    //获取数据
    private void getdata(){
        beanList.clear();
        OkHttpUtils
                .post()
                .url(Contance.getAllStudentRecordBYKname)
                .addParams("tid",userbean.id+"")
                .addParams("kname",kname)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss",e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("sss",response);
                        final TjBean comResult = new Gson().fromJson(response, TjBean.class);
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
