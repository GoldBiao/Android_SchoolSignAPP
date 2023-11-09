package lewis.com.sign.ui.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.guoxiaoxing.phoenix.compress.picture.internal.PictureCompressor;
import com.guoxiaoxing.phoenix.core.PhoenixOption;
import com.guoxiaoxing.phoenix.core.listener.ImageLoader;
import com.guoxiaoxing.phoenix.core.model.MediaEntity;
import com.guoxiaoxing.phoenix.core.model.MimeType;
import com.guoxiaoxing.phoenix.picker.Phoenix;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.bean.ComResult;
import lewis.com.sign.bean.Login;
import lewis.com.sign.utils.ACache;
import lewis.com.sign.utils.Contance;
import okhttp3.Call;

/**
 * Created by Administrator on 2019/12/11.
 */
//个人信息
public class UserInfoAct extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_phone)
    EditText tvPhone;


    @Override
    public int intiLayout() {
        return R.layout.act_userinfo;
    }

    @Override
    public void initView() {
        Phoenix.config()
                .imageLoader(new ImageLoader() {
                    @Override
                    public void loadImage(Context mContext, ImageView imageView
                            , String imagePath, int type) {
                        Glide.with(mContext)
                                .load(imagePath)
                                .into(imageView);
                    }
                });
        tvTitle.setText("个人信息");
        tvRight.setText("修改");
        ivBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        getInfo();
    }


    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //返回
                finish();
                break;
            case R.id.tv_right:



                if (TextUtils.isEmpty(tvName.getText().toString())) {
                    toast("请输入姓名");
                    return;
                }  if (TextUtils.isEmpty(tvPwd.getText().toString())) {
                toast("请输入密码");
                return;
            }  if (TextUtils.isEmpty(tvPhone.getText().toString())) {
                toast("请输入电话号");
                return;
            }
                upInfo();
                break;


        }
    }





    //查询用户信息
    private void getInfo() {
        OkHttpUtils
                .post()
                .url(Contance.getTercherInfo)
                .addParams("id", userbean.id + "")

                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Login login = new Gson().fromJson(response, Login.class);
                        if (login != null && login.data != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //渲染用户信息到页面
                                    Login.DataBean data = login.data;
                                    tvAccount.setText(data.account);
                                    tvName.setText(data.name);
                                    tvPhone.setText(data.phone);
                                    tvPwd.setText(data.pwd);






                                }
                            });

                        }
                    }
                });

    }

    //更新用户信息
    private void upInfo() {

        OkHttpUtils
                .post()
                .url(Contance.upTercher)
                .addParams("id", userbean.id + "")
                .addParams("account", tvAccount.getText().toString())
                .addParams("name", tvName.getText().toString())
                .addParams("pwd", tvPwd.getText().toString())
                .addParams("phone", tvPhone.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ComResult comResult = new Gson().fromJson(response, ComResult.class);
                        if (comResult != null) {
                            toast(comResult.msg);
                            finish();


                        }
                    }
                });


    }




}
