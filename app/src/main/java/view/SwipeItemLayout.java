package view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.lfz.lfzmjy.R;

import adatpters.FaceAdapter;
import utils.DeviceUtils;

/**
 * Created by LFZ on 2018/3/20.
 * 侧滑控件的显示的部分
 * 当一个view调用scrollTo()时,是里面的内容进行滑动,不是本身view滑动!
 * 所以使用scrollTo方法，此LinearLayout就不能只作为主内容部分，要作为整体布局，包含隐藏的部分
 */

public class SwipeItemLayout extends LinearLayout {

    private int screenWidth;
    //隐藏部分的宽度
    private int hideWidth = 500;
    //开始滑动的回调
    private FaceAdapter.SwipeListener swipeListener;

    private Scroller mScroller;

    public SwipeItemLayout(Context context) {
        this(context, null);
    }

    public SwipeItemLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(getContext());
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
        //屏幕宽度加隐藏按钮的宽度
        setMeasuredDimension(width + hideWidth, height);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                //这句话会导致父view的onInterceptTouchEvent不再被调用
//                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:

                //不用加这句可能的原因是，down事件被谁消费了，move和up都会去直接给到消费down事件的view
//                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        //继承已经写好的ViewGroup尽量不要在onInterceptTouchEvent中直接return true或者false，容易影响控件本身的逻辑导致出现问题
        //最好使用super.onInterceptTouchEvent，通常super会返回false，并且执行一些自己的逻辑
        return super.onInterceptTouchEvent(ev);
    }



    int startX = 0;
    int startY = 0;
    int currentX = 0;
    int currentY = 0;
    boolean isOpen = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                if(swipeListener != null){
                    //开始滑动
                    swipeListener.onSwipe();
                }
                return true;
            case MotionEvent.ACTION_MOVE:

                currentX = (int) event.getRawX();
                currentY = (int) event.getRawY();

                //右滑
                if(startX <= currentX){
                    if(isOpen){
                        int margin = (currentX - startX) - hideWidth;
                        margin = margin > 0 ? 0 : margin;
                        smoothScrollTo(- margin, 0);
                    }else{
                        smoothScrollTo(0, 0);
                    }

                }else{
                    //左滑
                    if(startX - currentX > hideWidth){
                        //达到最大距离，保持打开
                        smoothScrollTo(hideWidth,0);
                    }else{
                        if(isOpen){
                            //打开时左滑，保持
                            smoothScrollTo(hideWidth, 0);
                        }else{
                            smoothScrollTo(-(currentX - startX), 0);
                        }

                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                if(Math.abs(currentY - startY) > Math.abs(currentX - startX) && Math.abs(currentY - startY) - Math.abs(currentX - startX) > 10){
                    break;
                }
                if(startX - currentX > 100){
                    isOpen = true;
                    smoothScrollTo(hideWidth, 0);
                }else{
                    isOpen = false;
                    smoothScrollTo(0, 0);
                }

                break;

            default:
                if(Math.abs(currentY - startY) > Math.abs(currentX - startX)){
                    break;
                }
                if(startX - currentX > 100){
                    isOpen = true;
                    smoothScrollTo(hideWidth, 0);
                }else{
                    isOpen = false;
                    smoothScrollTo(0, 0);
                }

                break;

        }
        return super.onTouchEvent(event);
    }

    public void setHideWidth(int hideWidth) {
        this.hideWidth = hideWidth;
    }

    public void setButtonCount(int buttonCount){
        int buttonWidth = (int) getContext().getResources().getDimension(R.dimen.hideButton);
        this.hideWidth = buttonWidth * buttonCount;
    }

    public void setSwipeListener(FaceAdapter.SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
    }

    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    /**
     * 打开侧滑
     */
    public void open(){
        isOpen = true;
        smoothScrollTo(hideWidth, 0);
    }

    /**
     * 隐藏
     */
    public void close(){
        isOpen = false;
        smoothScrollTo(0, 0);
    }


    @Override
    public void computeScroll() {

        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
        super.computeScroll();
    }

}
