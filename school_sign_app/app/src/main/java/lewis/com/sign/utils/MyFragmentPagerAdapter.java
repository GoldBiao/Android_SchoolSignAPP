package lewis.com.sign.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import lewis.com.sign.base.BaseFragment;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    List<BaseFragment> list;//需要显示的fragment在构造器中传入
    List<String> titleList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> list, List<String> titleList) {

        super(fm);
        this.list = list;
        this.titleList = titleList;

    }

    //返回显示的Fragment总数
    @Override

    public int getCount() {

        return list.size();

    }

    //返回要显示的Fragment的某个实例
    @Override

    public Fragment getItem(int arg0) {

        return list.get(arg0);

    }
//
////返回一个自定义tab视图（用于自定义Tablayout标签，不自定义可忽略）
//    public View getTabView(int position) {
//
//
//        int layout_ids = R.layout.tab_view_monthly;
//
//        if (position == 1) {
//
//            layout_ids = R.layout.tab_view_daily;
//
//        }
//
//        View v = LayoutInflater.from(MainActivity.this).inflate(layout_ids, null);
//
//        return v;
//
//    }

    //返回每个Tab的标题，当要自定义Tab的时候不应该重写该方法
    @Override

    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

}