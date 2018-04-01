package bases.views.topviewpagerwidget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by LFZ on 2017/7/23.
 * 顶部轮播控件
 */

public class TopViewPagerWidget extends BaseViewPager {

    Timer timer = new Timer();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                if(isStop){
                    return;
                }
                if(getAdapter().getCount() != 0){
                    int index = (getCurrentItem() + 1) % getAdapter().getCount();
                    setCurrentItem(index);
                }
            }
        }
    };

    private boolean hasData = false;
    private WidgetModel widgetModel;
    private boolean isStop = false;

    List<WidgetModel.DataModel> images = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    ViewAdapter adapter;
    private Fragment parentFragment;

    public TopViewPagerWidget(Context context) {
        this(context, null);
    }

    public TopViewPagerWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //入口
    public void setWidgetModel(WidgetModel model) {
        this.widgetModel = model;
        show();
    }

    public void refresh(WidgetModel model){
        this.widgetModel = model;
    }

    public void show() {
        initData();
        //读取数据并显示
        initAdapter();
        initOthers();
    }

    //设置父fragment
    public void setParentFragment(Fragment fragment){
        parentFragment = fragment;
    }

    /**
     * 声明周期
     */

    private void initData() {
        images = widgetModel.getData();
        setOffscreenPageLimit(images.size());
    }

    private void initAdapter() {
        if (images.size() == 0) {
            return;
        }
        fragmentList.clear();
        for (int i = 0; i < images.size(); i++) {
            TopViewPagerFragment fragment = TopViewPagerFragment.getInstance(images.get(i).getTitle(), images.get(i).getUrl());
            fragmentList.add(fragment);
        }
        if (adapter == null) {
            FragmentManager fragmentManager = null;
            if(parentFragment != null){
                fragmentManager = parentFragment.getChildFragmentManager();
            }else{
                fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
            }
            adapter = new ViewAdapter(fragmentManager, fragmentList);
            this.setAdapter(adapter);
        } else {
            adapter.setFragmentList(fragmentList);
        }
    }

    private void initOthers(){
        if(widgetModel.isAuto()){
            //两秒后执行，过两秒再次执行
            timer.schedule(task,widgetModel.getTime(),widgetModel.getTime());
        }
    }


    /**
     * 定时器
     */


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(widgetModel !=null){
                    if(widgetModel.isTouchStop()){
                        isStop = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(widgetModel != null){
                    if(widgetModel.isTouchStop()){
                        isStop = false;
                    }
                }
        }
        return super.onTouchEvent(ev);
    }

    public void stop(){
        timer.cancel();
    }
}
