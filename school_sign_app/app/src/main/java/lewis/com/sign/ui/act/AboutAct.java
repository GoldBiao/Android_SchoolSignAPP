package lewis.com.sign.ui.act;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import android.os.Bundle;

import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;

/**
 * Created by Administrator on 2019/12/11.
 */
//关于页面
public class AboutAct extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int intiLayout() {
        return R.layout.act_about;
    }

    @Override
    public void initView() {
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("关于");

    }

    @Override
    public void initData() {

    }


}