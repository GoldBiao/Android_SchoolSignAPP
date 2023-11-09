package lewis.com.sign.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import lewis.com.sign.bean.Login;
import lewis.com.sign.utils.ACache;

public abstract class BaseFragment extends Fragment {

    //这个activity就是MainActivity
    public Activity mActivity;
    private Unbinder bind;
    protected Login.DataBean userbean;
    protected boolean isCreated;

    // Fragment被创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();// 获取所在的activity对象
        userbean = (Login.DataBean) ACache.get(mActivity).getAsObject("userbean");
    }

    // 初始化Fragment布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView(inflater,container);
        bind = ButterKnife.bind(this, view);
        isCreated = true;
        return view;
    }
    public void jumpAct(Class s){
        Intent intent = new Intent(mActivity, s);
        startActivity(intent);
    } public void jumpAct(Class s,int res){
        Intent intent = new Intent(mActivity, s);
        startActivityForResult(intent,res);
    }
    public void jumpAct(Class s,Bundle bundle){
        Intent intent = new Intent(mActivity, s);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        if (bind != null) {
            bind.unbind();
        }
        super.onDestroyView();
    }
    public void toast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
    // activity创建结束
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化布局, 子类必须实现
     * @param inflater
     * @param container
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化数据, 子类可以不实现
     */
    public void initData() {

    }
}