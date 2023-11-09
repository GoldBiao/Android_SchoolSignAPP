package lewis.com.sign.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;


import butterknife.ButterKnife;


import lewis.com.sign.bean.Login;
import lewis.com.sign.utils.ACache;

public abstract class BaseActivity extends AppCompatActivity {
//    /***是否显示标题栏*/
//    private  boolean isshowtitle = true;
//    /***是否显示标题栏*/
//    private  boolean isshowstate = true;
    /***封装toast对象**/
    private static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    protected Login.DataBean userbean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(!isshowtitle){
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//        }
        userbean = (Login.DataBean) ACache.get(this).getAsObject("userbean");
//        if(isshowstate){
//            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//                    WindowManager.LayoutParams. FLAG_FULLSCREEN);
//        }
        //设置布局
        setContentView(intiLayout());
        ButterKnife.bind(this);
        //初始化控件
        initView();
        //设置数据
        initData();
    }
    public void jumpAct(Class s){
        Intent intent = new Intent(this, s);
        startActivity(intent);
    } public void jumpAct(Class s,int res){
        Intent intent = new Intent(this, s);
        startActivityForResult(intent,res);
    }
    public void jumpAct(Class s,Bundle bundle){
        Intent intent = new Intent(this, s);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
