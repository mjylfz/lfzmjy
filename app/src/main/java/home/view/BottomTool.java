package home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFZ on 2017/11/6.
 * 底部选择工具栏的父类
 */

public class BottomTool extends LinearLayout implements View.OnClickListener{

    private List<View> viewList;
    ToolButtonListener listener;
    private static final int TOOLHEIGHT = 50;

    public BottomTool(Context context) {
        this(context, null ,0);
    }

    public BottomTool(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTool(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView(){

        this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,TOOLHEIGHT));
        this.setOrientation(LinearLayout.HORIZONTAL);

        viewList = new ArrayList<>();

    }

    public void addButtonView(View view){
        view.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
        view.setId(viewList.size());
        addView(view);
        viewList.add(view);
        view.setOnClickListener(this);
    }

    public void setToolButtonListener(ToolButtonListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < viewList.size(); i++){
            if(viewList.get(i).getId() == v.getId()){
                if(listener != null){
                    listener.onToolButtonClick(viewList.get(i), i);
                    setSelectIndex(i);
                }
            }
        }
    }

    protected void setSelectIndex(int index){

    }

    public interface ToolButtonListener{
        void onToolButtonClick(View view, int position);
    }
}
