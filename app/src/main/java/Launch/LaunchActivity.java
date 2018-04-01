package Launch;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lfz.lfzmjy.MainActivity;
import com.lfz.lfzmjy.R;

import java.util.Date;

import bases.datashare.service.DataPreferencesManager;
import bases.views.BaseActivity;
import face.DetecterActivity;
import face.FaceManager;
import utils.AnivsyDataPrepare;
import utils.BackGroundUtils;
import utils.DataPrepareManager;
import utils.DateUtils;
import utils.FaceDataPrepare;

/**
 * Created by LFZ on 2017/11/20.
 * 欢迎页面
 */

public class LaunchActivity extends BaseActivity {
    private static final int launchTime = 3500;
    private ImageView launchImage;
    private LinearLayout launchContent;
    private TextView tvDate;
    private TextView tvDateCount;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView() {
        launchImage = findById(R.id.launch_image);
        launchImage.setImageResource(BackGroundUtils.getBackgroundId());

        launchContent = findById(R.id.launch_content);
        tvDate = findById(R.id.today);
        tvDateCount = findById(R.id.day);

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        registDataPrepare();
        firstInitData();
        initDate();
        startAnimation();
        startNewActivity();
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {

    }

    //----------成员方法---------

    private void initDate(){
        String today = DateUtils.date2str(new Date(),"yyyy年MM月dd日");
        String dayOfWeek = DateUtils.getDateOfWeek();
        tvDate.setText(today + "  " + dayOfWeek);

        int dayCount = DateUtils.getGapCount(DateUtils.str2date("2012/10/13","yyyy/MM/dd"),new Date());
        tvDateCount.setText("我们在一起" + dayCount + "天");
    }

    private void startAnimation(){
        ObjectAnimator alphaAnimate = ObjectAnimator.ofFloat(launchContent,"alpha",0.1f, 1f);
        ObjectAnimator translateAnimate = ObjectAnimator.ofFloat(launchContent,"translationY",0f, -500f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alphaAnimate).with(translateAnimate);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }

    private void startNewActivity(){
        Handler showHandler = new Handler();
        showHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(DataPreferencesManager.hasInstall()){
                    //面部识别
                    if(DataPreferencesManager.getFaceDetect() && !FaceManager.getInstance().getmFaceDB().getmRegister().isEmpty()){
                        Intent intent = new Intent(LaunchActivity.this, DetecterActivity.class);
                        intent.putExtra("Camera", 1);
                        startActivity(intent);
                    }else{
                        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                    }

                }else{
                    DataPreferencesManager.firstInstall();
                    startActivity(new Intent(LaunchActivity.this, GuideActivity.class));
                }
                finish();
            }
        }, launchTime);
    }

    /**
     * 注册所有的数据
     */
    private void registDataPrepare(){
        DataPrepareManager.getInstance().registDataPrepare(AnivsyDataPrepare.getInstance(), true);
        DataPrepareManager.getInstance().registDataPrepare(FaceDataPrepare.getInstance());
    }

    /**
     * 第一次进入时加载的数据
     */
    private void firstInitData(){
        DataPrepareManager.getInstance().initDataPrepare();
    }

}
