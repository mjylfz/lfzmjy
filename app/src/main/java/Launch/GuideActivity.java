package Launch;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.lfz.lfzmjy.MainActivity;
import com.lfz.lfzmjy.R;

import bases.views.BaseActivity;

/**
 * Created by LFZ on 2017/11/20.
 * 第一次安装的引导页
 */

public class GuideActivity extends BaseActivity {

    private Button button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        button = findById(R.id.btn_start);
    }

    @Override
    protected void setListeners() {
        setOnClickListener(button);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
                break;
        }
    }
}
