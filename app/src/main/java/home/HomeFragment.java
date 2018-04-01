package home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.lfz.lfzmjy.R;

import java.util.ArrayList;
import java.util.List;

import bases.views.BaseFragment;
import home.HomeFragments.GalryFragment;
import home.HomeFragments.OurFragment;
import home.adapter.GalryViewAdapter;
import home.view.BottomTool;
import home.view.GalryViewPager;

/**
 * Created by LFZ on 2017/10/29.
 * 主页fragment
 */

public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    private GalryViewPager galryViewPager;
    private BottomTool bottomTool;
    private List<Fragment> fragmentList;
    private OurFragment ourFragment;

    //------生命周期方法------
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        galryViewPager = findViewById(R.id.home_pager);
        galryViewPager.setChangeByScroll(false);
        bottomTool = findViewById(R.id.bottom_tool);

        bottomTool.setToolButtonListener(new BottomTool.ToolButtonListener() {
            @Override
            public void onToolButtonClick(View view, int position) {
                galryViewPager.setCurrentItem(position);
            }
        });

        //fragmentlist
        ourFragment = new OurFragment();
        GalryFragment galryFragment2 = new GalryFragment();
        GalryFragment galryFragment4 = new GalryFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(ourFragment);
        fragmentList.add(galryFragment2);
        fragmentList.add(galryFragment4);

        //adapter
        GalryViewAdapter galryViewAdapter = new GalryViewAdapter(getChildFragmentManager(), fragmentList);
        galryViewPager.setAdapter(galryViewAdapter);
        galryViewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initListener() {
        galryViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //------对外---------


    //------私有---------
}
