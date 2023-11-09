package lewis.com.sign.ui.act;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lewis.com.sign.R;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.utils.SimpleAdapter;
import okhttp3.Call;

public class KechengTjAct extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<String> beanList=new ArrayList<>();
    private SimpleAdapter<String> adapter;

    @Override
    public int intiLayout() {
        return R.layout.act_list;
    }

    @Override
    public void initView() {
        tvTitle.setText("课程统计");
        ivBack.setVisibility(View.VISIBLE);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //给列表绑定adapter并渲染数据
        adapter = new SimpleAdapter<String>(R.layout.item_kecheng, beanList, new SimpleAdapter.ConVert<String>() {
            @Override
            public void convert(final BaseViewHolder helper, String dataBean) {

                helper.setText(R.id.tv_name,dataBean);

            }
        });
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("name",beanList.get(position));
                jumpAct(SignTjAct.class,bundle);
            }
        });
    }

    @Override
    public void initData() {
        beanList.add("计算机科学与技术");
        beanList.add("高数");
        beanList.add("大学英语");
        beanList.add("毛概");
        beanList.add("软件工程理论");
        beanList.add("软件理论");
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
