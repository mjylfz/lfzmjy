package home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.lfz.lfzmjy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFZ on 2017/11/15.
 * 首页的底部
 */

public class MainPageBottomToolBar extends  BottomTool {

    private List<SelectItem> selectItems;

    public MainPageBottomToolBar(Context context) {
        super(context);
    }

    public MainPageBottomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainPageBottomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        super.initView();
        createBottom();

    }

    //-------私有方法------

    private void createBottom(){
        selectItems = new ArrayList<>();

        SelectItem item1 = new SelectItem(getContext());
        item1.setuIcon(R.drawable.u1)
                .setsIcon(R.drawable.s1)
                .setText(getResources().getString(R.string.mainpage))
                .setSelect(true);

        SelectItem item2 = new SelectItem(getContext());
        item2.setuIcon(R.drawable.u2)
                .setsIcon(R.drawable.s2)
                .setText(getResources().getString(R.string.us));

        SelectItem item3 = new SelectItem(getContext());
        item3.setuIcon(R.drawable.u3)
                .setsIcon(R.drawable.s3)
                .setText(getResources().getString(R.string.callme));

        SelectItem item4 = new SelectItem(getContext());
        item4.setuIcon(R.drawable.u4)
                .setsIcon(R.drawable.s4)
                .setText(getResources().getString(R.string.mg));

        addButtonView(item1);
        addButtonView(item2);
        addButtonView(item3);
        addButtonView(item4);
        selectItems.add(item1);
        selectItems.add(item2);
        selectItems.add(item3);
        selectItems.add(item4);

    }

    //点击事件后调用，传给ui层进行改变
    @Override
    protected void setSelectIndex(int index) {
        for(int i = 0;i < selectItems.size();i++){
            selectItems.get(i).setSelect(i == index);
        }
    }
}
