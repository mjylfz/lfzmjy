package bases.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lfz.lfzmjy.R;

import utils.DeviceUtils;

/**
 * Created by LFZ on 2017/10/28.
 * 策划菜单的Activity
 */

public abstract class SlidingMenuActivity extends BaseActivity implements SlidingMenu.OnOpenedListener, SlidingMenu.OnClosedListener{

    protected SlidingMenu slidingMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initView() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);

        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.setFadeDegree(0);
//        slidingMenu.setBehindOffset(1);
        slidingMenu.setBehindScrollScale(0.3f);
        slidingMenu.setBehindWidth((int)(DeviceUtils.getScreenWidth(this) * 0.75));
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);

        slidingMenu.setOnOpenedListener(this);
        slidingMenu.setOnClosedListener(this);
    }

    @Override
    public void onOpened() {

    }

    @Override
    public void onClosed() {

    }

    @Override
    public void onBackPressed() {
        if(slidingMenu.isMenuShowing()){
            slidingMenu.toggle(true);
        }else{
            super.onBackPressed();
        }
    }
}
