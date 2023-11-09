package lewis.com.sign.ui.frg;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseFragment;
import lewis.com.sign.bean.Login;
import lewis.com.sign.ui.act.LoginActivity;

import lewis.com.sign.ui.act.SetAct;
import lewis.com.sign.ui.act.UserInfoAct;
import lewis.com.sign.utils.ACache;
import lewis.com.sign.utils.Contance;
import okhttp3.Call;

/**
 * Created by Administrator on 2020/3/17.
 */

public class MineFrg extends BaseFragment{
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.iv)
    ImageView iv;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.frg_mine,container,false);
    }


    @Override
    public void onResume() {
        super.onResume();
        getInfo();

    }

    @Override
    public void initData() {
        super.initData();

    }
    //查询用户信息
    private void getInfo(){
        OkHttpUtils
                .post()
                .url(Contance.getTercherInfo)
                .addParams("id", userbean.id+"")

                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("sss",e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Login login = new Gson().fromJson(response, Login.class);
                        if (login!=null&&login.data!=null){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //渲染用户信息到页面
                                    Login.DataBean data = login.data;
                                    tvAccount.setText(data.name);



                                }
                            });

                        }
                    }
                });

    }

    @OnClick({ R.id.tv_exit, R.id.tv_up_pwd,R.id.tv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_up_pwd:
                startActivity(new Intent(mActivity,UserInfoAct.class));
                break;

            case R.id.tv_set:
                jumpAct(SetAct.class);
                break;




            case R.id.tv_exit:
                ACache.get(getActivity()).clear();
                jumpAct(LoginActivity.class);
                mActivity.finish();

                break;
        }
    }




}
