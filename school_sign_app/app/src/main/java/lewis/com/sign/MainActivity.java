package lewis.com.sign;

import android.support.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.RelativeLayout;

import butterknife.BindView;
import lewis.com.sign.base.BaseActivity;
import lewis.com.sign.ui.frg.HomeFrg;
import lewis.com.sign.ui.frg.MineFrg;

//系统主页
public class MainActivity extends BaseActivity {

    //初始话控件
    @BindView(R.id.continer)
    RelativeLayout continer;
    @BindView(R.id.bnv_bottom_activity)
    BottomNavigationView bnvBottomActivity;
    private HomeFrg homeFrg;

    private MineFrg mineFrg;

    private androidx.fragment.app.Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment
    private MenuItem lastItem;
    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initFragment();
    }

    @Override
    public void initData() {

    }
    //初始化fragment
    private void initFragment() {

        homeFrg = new HomeFrg();
        mineFrg=new MineFrg();


        fragments = new Fragment[]{homeFrg, mineFrg};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.continer, homeFrg).show(homeFrg).commit();


        bnvBottomActivity.setOnNavigationItemSelectedListener(changeFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //切换fragment
            switch (item.getItemId()) {
                case R.id.home: {
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;

                    }

                    return true;
                }

                case R.id.mine: {
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;

                    }

                    return true;
                }




            }


            return false;
        }
    };

    //切换Fragment
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.continer, fragments[index]);


        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }





}
