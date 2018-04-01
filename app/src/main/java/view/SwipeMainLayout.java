package view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import utils.DeviceUtils;

/**
 * Created by LFZ on 2018/3/30.
 * 滑动显示的主区域，和屏幕宽度一致
 */

public class SwipeMainLayout extends LinearLayout {

    private int screenWidth;

    public SwipeMainLayout(Context context) {
        this(context, null);
    }

    public SwipeMainLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeMainLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenWidth = DeviceUtils.getScreenWidth(getContext());
        int width = 0;
        int height = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST){
            width = MeasureSpec.getSize(widthMeasureSpec);
        }else if(widthMode == MeasureSpec.EXACTLY){
            //父类是EXACTLY，子类是MATCH_PARENT，跟随父类大小，这边使用屏幕宽度
            width = screenWidth;
        }else if(widthMode == MeasureSpec.UNSPECIFIED){
            width = screenWidth;
        }
        if(heightMode == MeasureSpec.AT_MOST){
            height = MeasureSpec.getSize(heightMeasureSpec);
        }else if(heightMode == MeasureSpec.EXACTLY){
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(width, height);
    }
}
