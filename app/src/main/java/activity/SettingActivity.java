package activity;

import android.content.Intent;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.lfz.lfzmjy.R;

import app.MyApplication;
import bases.datashare.service.CallBackManager;
import bases.datashare.service.DataPreferencesManager;
import bases.views.BaseActivity;
import constants.LocalFile;
import face.DetecterActivity;
import face.FaceManager;
import face.RegisterActivity;
import home.presenter.TakePhotoListener;
import utils.ImageSelectUtils;

/**
 * Created by LFZ on 2018/1/29.
 * 设置页
 */

public class SettingActivity extends BaseActivity {

    Button btnRegist;
    Button btnDetect;
    Button faceList;
    Switch aSwitch;
    private ImageSelectUtils imageSelectUtils;
    private String currentPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView() {
        btnRegist = findById(R.id.registFace);
        btnDetect = findById(R.id.detectFace);
        aSwitch = findById(R.id.openFaceDetected);
        faceList = findById(R.id.faceList);
    }

    @Override
    protected void setListeners() {
        setOnClickListener(btnRegist);
        setOnClickListener(btnDetect);
        setOnClickListener(aSwitch);
        setOnClickListener(faceList);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataPreferencesManager.setFaceDetect(isChecked);
            }
        });
    }

    @Override
    protected void initData() {
        aSwitch.setChecked(DataPreferencesManager.getFaceDetect());
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registFace:
                registerCallBack();

                imageSelectUtils = new ImageSelectUtils(SettingActivity.this);
                currentPath = LocalFile.FACE_IMAGE_PATH + "/" + System.currentTimeMillis() +"face"  +".jpg";
                imageSelectUtils.takePhoto(currentPath);
                break;
            case R.id.detectFace:
                if(FaceManager.getInstance().getmFaceDB().getmRegister().isEmpty()){
                    Toast.makeText(this, "没有注册人脸，请先注册！", Toast.LENGTH_SHORT).show();
                }else{
                    startDetect(1);
                }
                break;
            case R.id.faceList:
                startActivity(new Intent(this,FaceSettingsActivity.class));
                break;
        }
    }

    /**
     * 注册回调
     */
    private void registerCallBack(){
        CallBackManager.registSettingResultListener(new TakePhotoListener() {
            @Override
            public void dataBack(int requestCode, int resultCode, Intent data) {
                resolveData(requestCode, resultCode, data);
                Intent intent = new Intent(SettingActivity.this, RegisterActivity.class);
                intent.putExtra("imagePath", currentPath);
                startActivity(intent);
            }
        });
    }

    /**
     * 处理数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void resolveData(int requestCode, int resultCode, Intent data){

    }

    private void startDetect(int camera){
        Intent it = new Intent(this, DetecterActivity.class);
        it.putExtra("Camera", camera);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TakePhotoListener listener = CallBackManager.getSettingResultListener();
        if(listener != null){
            listener.dataBack(requestCode,resultCode,data);
        }
    }
}
