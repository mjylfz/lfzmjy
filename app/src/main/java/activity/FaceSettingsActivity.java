package activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lfz.lfzmjy.R;

import adatpters.FaceAdapter;
import bases.views.BaseActivity;
import face.FaceManager;
import view.FaceSettingRecyclerView;

/**
 * Created by LFZ on 2018/3/12.
 * 面部识别的设置
 */

public class FaceSettingsActivity extends BaseActivity {

    private FaceSettingRecyclerView faceView;
    private FaceAdapter faceAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_facesetting;
    }

    @Override
    protected void initView() {
        faceView = findById(R.id.faceList);
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {
        faceAdapter = new FaceAdapter(this, FaceManager.getInstance().getmFaceDB().getmRegister());
        faceAdapter.setHasStableIds(true);
        faceView.setLayoutManager(new LinearLayoutManager(this));
        faceView.setHasFixedSize(true);
        faceView.setAdapter(faceAdapter);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onClick(View v) {

    }
}
