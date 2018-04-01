package view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by LFZ on 2018/3/30.
 */

public class FaceSettingRecyclerView extends RecyclerView {
    public FaceSettingRecyclerView(Context context) {
        this(context, null);
    }

    public FaceSettingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceSettingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    int downX;
    int downY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int currentX;
        int currentY;
        switch (action){
            case MotionEvent.ACTION_DOWN:
                downX = (int)ev.getRawX();
                downY = (int)ev.getRawY();
                super.onInterceptTouchEvent(ev);
                //down事件不拦截，否则下方后续收不到move和up
                return false;
            case MotionEvent.ACTION_MOVE:
                currentX = (int)ev.getRawX();
                currentY = (int)ev.getRawY();
                Log.e("distance", Math.abs(currentY - downY) + "  " + Math.abs(currentX - downX));
                boolean intercept1 = super.onInterceptTouchEvent(ev);
                Log.e("intercept1", "move " + intercept1 + "");
                if(Math.abs(currentY - downY) > Math.abs(currentX - downX) && Math.abs(currentY - downY) - Math.abs(currentX - downX) > 10){
                    return true;
                }else{
                    return false;
                }

            case MotionEvent.ACTION_UP:
                super.onInterceptTouchEvent(ev);
                //不拦截up事件，让子View接收到Up事件进行处理
                return false;

        }

        return super.onInterceptTouchEvent(ev);
    }

}
