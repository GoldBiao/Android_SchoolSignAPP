package lewis.com.sign.ui.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;


public class SetAct extends BaseActivity {
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_right;
    private TextView tv_set;
    private TextView tv_banben;
    private LinearLayout ll_ban;
    private TextView tv_hc;
    private LinearLayout ll_huancun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_set);
        initView();
    }

    @Override
    public int intiLayout() {
        return R.layout.act_set;
    }

    @Override
    public void initView() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("设置");

        tv_set = (TextView) findViewById(R.id.tv_set);
        tv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpAct(AboutAct.class);
            }
        });

        ll_ban = (LinearLayout) findViewById(R.id.ll_ban);
        ll_ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("已经是最新版");
            }
        });
        tv_hc = (TextView) findViewById(R.id.tv_hc);
        double v = Math.random() * 20;
        tv_hc.setText(v+"M");
        ll_huancun = (LinearLayout) findViewById(R.id.ll_huancun);
        ll_huancun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("清除成功");
                tv_hc.setText("0M");
            }
        });
    }

    @Override
    public void initData() {

    }
}
