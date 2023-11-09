package lewis.com.sign.ui.act;

import android.content.Intent;
import android.view.WindowManager;

import lewis.com.sign.MainActivity;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.bean.Login;
import lewis.com.sign.utils.ACache;

//欢迎页面
public class WelcomAct extends BaseActivity {
    @Override
    public int intiLayout() {
        return R.layout.act_wel;
    }

    @Override
    public void initView() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否登录
                Login.DataBean userbean = (Login.DataBean) ACache.get(WelcomAct.this).getAsObject("userbean");

                if (userbean==null){
                    //跳转登录
                    Intent intent = new Intent(WelcomAct.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    //跳转首页

                    Intent intent= new Intent(WelcomAct.this,MainActivity.class);
                    startActivity(intent);




                }

                finish();
            }
        },2000);
    }

    @Override
    public void initData() {

    }
}
