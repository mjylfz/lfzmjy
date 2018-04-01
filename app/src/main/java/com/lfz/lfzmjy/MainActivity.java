package com.lfz.lfzmjy;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bases.baseinterface.BaseCallBack;
import bases.datashare.service.CallBackManager;
import bases.views.SlidingMenuActivity;
import home.HomeFragment;
import home.presenter.TakePhotoListener;
import utils.ThemeUtils;
import utils.ToastUtils;

/**
 * create by lfz
 * 2017/10/23
 * 主页面
 */
public class MainActivity extends SlidingMenuActivity {


    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private static final String HOME = "HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.app_bar_main;
    }

    @Override
    protected void initView() {
        super.initView();
        initBars();
        initFrag();
    }

    @Override
    protected void setListeners() {
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void requestData() {

    }

    //--------------私有方法-----------

    /**
     * 初始化状态栏和工具栏
     */
    private void initBars(){

    }

    /**
     * 初始化界面
     */
    private void initFrag(){
        fragmentManager = getSupportFragmentManager();

        homeFragment = (HomeFragment)fragmentManager.findFragmentByTag(HOME);
        if(homeFragment == null){
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_home, homeFragment, HOME)
                    .show(homeFragment)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_menu:
                slidingMenu.toggle(true);
                break;
        }
    }


    //按两次退出
    private boolean shouldExit = false;
    Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        if(shouldExit){
            super.onBackPressed();
            return;
        }
        ToastUtils.shortToast(getResources().getString(R.string.exit_signal));
        shouldExit = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shouldExit = false;
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获得注册了拍照监听的listener
        TakePhotoListener listener = CallBackManager.getMainResultListener();
        if(listener != null){
            listener.dataBack(requestCode, resultCode, data);
        }
    }
}
