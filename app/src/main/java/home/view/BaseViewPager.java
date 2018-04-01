package home.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by LFZ on 2017/11/6.
 * viewPager的基类
 */

public class BaseViewPager extends ViewPager {

    private boolean changeByScroll = true;

    public BaseViewPager(Context context) {
        super(context);
    }

    public BaseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return changeByScroll && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return changeByScroll && super.onInterceptTouchEvent(ev);
    }


    //设置滚动
    public void setChangeByScroll(boolean changeByScroll) {
        this.changeByScroll = changeByScroll;
    }
}
