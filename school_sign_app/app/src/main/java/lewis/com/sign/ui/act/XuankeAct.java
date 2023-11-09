package lewis.com.sign.ui.act;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lewis.com.sign.MainActivity;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.bean.ComResult;
import lewis.com.sign.bean.Login;
import lewis.com.sign.utils.ACache;
import lewis.com.sign.utils.BasisTimesUtils;
import lewis.com.sign.utils.Contance;
import lewis.com.sign.utils.TypeDialog;
import okhttp3.Call;

public class XuankeAct extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_time)
    TextView tvTime;  @BindView(R.id.tv_date)
    TextView tv_date;
    private TypeDialog typeDialog;
    private List<String> types=new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.act_xuanke;
    }

    @Override
    public void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("上课选课");
        types.add("计算机科学与技术");
        types.add("高数");
        types.add("大学英语");
        types.add("毛概");
        types.add("软件工程理论");
        types.add("软件理论");
    }

    @Override
    public void initData() {

    }

    /**
     * 年月日选择
     */
    private void showYearMonthDayPicker() {
        BasisTimesUtils.showDatePickerDialog(this, BasisTimesUtils.THEME_HOLO_DARK, "请选择年月日", 2022, 1, 1, new BasisTimesUtils.OnDatePickerListener() {

            @Override
            public void onConfirm(int year, int month, int dayOfMonth) {
                tv_date.setText(year + "-" + (month>10?month:("0"+month)) + "-" +(dayOfMonth>10?dayOfMonth:("0"+dayOfMonth)) );
            }

            @Override
            public void onCancel() {
                toast("cancle");
            }
        });
    }
    /**
     * 时间选择
     */
    private void showTimerPicker() {
        BasisTimesUtils.showTimerPickerDialog(this, true, "请选择时间", 12, 00, true, new BasisTimesUtils.OnTimerPickerListener() {
            @Override
            public void onConfirm(int hourOfDay, int minute) {
                tvTime.setText(hourOfDay + ":" + (minute>10?minute:("0"+minute)) );
            }

            @Override
            public void onCancel() {
                toast("cancle");
            }
        });

    }
    @OnClick({R.id.iv_back, R.id.tv_name, R.id.tv_time,  R.id.tv_date, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_name:
                showkcList();
                break;
            case R.id.tv_time:
                showTimerPicker();
                break;
            case R.id.tv_date:
                showYearMonthDayPicker();
                break;
            case R.id.bt_ok:
                if (TextUtils.isEmpty(tvName.getText().toString())) {
                    Toast.makeText(this, "请选择名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etAddress.getText().toString())) {
                    Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tv_date.getText().toString())) {
                    Toast.makeText(this, "请选择日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tvTime.getText().toString())) {
                    Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                add();
                break;
        }
    }

    private void add() {
        OkHttpUtils
                .post()
                .url(Contance.addKecheng)
                .addParams("name", tvName.getText().toString())
                .addParams("address", etAddress.getText().toString())
                .addParams("time", tv_date.getText().toString()+" "+tvTime.getText().toString())
                .addParams("tid", userbean.id+"")

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

                            toast("添加成功");

                            finish();
                        }
                    }
                });

    }
    public void showkcList(){
        if(typeDialog ==null){
            typeDialog =new TypeDialog(this);
            typeDialog.setListener(new TypeDialog.onSelectListener() {
                @Override
                public void onSelect(String model) {
                    tvName.setText(model);
                    typeDialog.dismiss();

                }
            });
        }
        typeDialog.setContent("选择课程名称",types);
        typeDialog.show();
    }
}
