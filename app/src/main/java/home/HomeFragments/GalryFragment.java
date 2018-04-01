package home.HomeFragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lfz.lfzmjy.R;

import java.util.List;

import bases.views.BaseFragment;
import bases.views.topviewpagerwidget.TopViewPagerWidget;
import bases.views.topviewpagerwidget.WidgetModel;
import constants.ImgUrl;
import utils.ImageModelHelper;

/**
 * Created by LFZ on 2017/11/6.
 * 相册页面
 */

public class GalryFragment extends BaseFragment{

    TopViewPagerWidget topPager;
    List<Fragment> fragmentList;


    //------生命周期方法------
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pic;
    }

    @Override
    protected void initView() {
        topPager = findViewById(R.id.topPager);
        topPager.setParentFragment(this);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {
        initTopPager();

    }

    @Override
    public void onClick(View v) {

    }

    //------对外---------


    //------私有---------
    private void initTopPager(){
        WidgetModel widgetModel = new WidgetModel();
        widgetModel.setAuto(true).setTime(5000).setTouchStop(true)
                .setData(ImageModelHelper.convertToImageModel(ImgUrl.getUrls()));
        topPager.setWidgetModel(widgetModel);
    }

    //----接口----
}
