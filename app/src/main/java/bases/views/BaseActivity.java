package bases.views;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;

/**
 * create by lfz on 2017/10/24
 * activity
 */

public abstract  class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private SparseArray<View> viewSparseArray;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(getLayoutId());
        initView();
        setListeners();
        initData();
        requestData();
    }

    /**
     * view的准备工作
     */
    protected void beforeSetContentView(){
        viewSparseArray = new SparseArray<>();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public <T extends View> T findById(int id){
        T view = (T)viewSparseArray.get(id);
        if(view == null){
            view = (T)findViewById(id);
            viewSparseArray.put(id, view);
        }
        return view;
    }

    public <T extends View> void setOnClickListener(T view){
        view.setOnClickListener(this);
    }

    //子类实现方法

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void setListeners();
    protected abstract void initData();
    protected abstract void requestData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
