package home.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lfz.lfzmjy.R;

/**
 * Created by LFZ on 2017/11/20.
 * 一个选项的控件
 */

public class SelectItem extends LinearLayout {

    private ImageView imageIcon;
    private TextView textView;
    private int uImage;
    private int sImage;
    private boolean isSelected;

    public SelectItem(Context context) {
        this(context, null, 0);
    }

    public SelectItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT,1));
        setOrientation(VERTICAL);
        setBackgroundColor(Color.WHITE);

        imageIcon = new ImageView(getContext());
        imageIcon.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0 ,2));
        imageIcon.setPadding(5,5,5,5);
        imageIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0 ,1));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(5,0,5,5);
        textView.setTextSize(10);
        textView.setTextColor(getResources().getColor(R.color.bottom_tc));

        addView(imageIcon);
        addView(textView);
    }

    /**
     * 选中的图标
     * @param id
     * @return
     */
    public SelectItem setsIcon(int id){
        sImage = id;
        return this;
    }

    /**
     * 未选中的图标
     * @param id
     * @return
     */
    public SelectItem setuIcon(int id){
        uImage = id;
        imageIcon.setImageResource(id);
        return this;
    }

    /**
     *
     * @param text
     * @return
     */
    public SelectItem setText(String text){
        textView.setText(text);
        return this;
    }

    public void setSelect(boolean isSelected){
        this.isSelected = isSelected;
        if(isSelected){
            imageIcon.setImageResource(sImage);
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else{
            imageIcon.setImageResource(uImage);
            textView.setTextColor(getResources().getColor(R.color.bottom_tc));
        }
    }
}
