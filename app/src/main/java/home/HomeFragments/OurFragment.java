package home.HomeFragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lfz.lfzmjy.R;


import activity.SettingActivity;
import bases.views.BaseFragment;
import home.activity.AnivsryActivity;
import home.presenter.BgSelectManager;
import utils.ImageSelectUtils;
import utils.ImageUtils.ImageLoadUtils;

/**
 * Created by LFZ on 2017/11/6.
 * 首页
 */

public class OurFragment extends BaseFragment{

    //选择背景
    private Button btnSbg;
    //背景
    private ImageView img;
    private Bitmap oldBitmap;

    private CardView anivsary;
    private CardView FaceId;

    //------生命周期方法------
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_us;
    }

    @Override
    protected void initView() {
        btnSbg = findViewById(R.id.btn_sbg);
        img = findViewById(R.id.img);
        anivsary = findViewById(R.id.anivsary);
        FaceId = findViewById(R.id.FaceId);
    }

    @Override
    protected void initListener() {
        setClickListener(btnSbg);
        setClickListener(anivsary);
        setClickListener(FaceId);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sbg:
                selectBg();
                break;
            case R.id.anivsary:
                startActivity(new Intent(getActivity(), AnivsryActivity.class));
                break;
            case R.id.FaceId:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;

        }

    }

    //------对外---------


    //------私有---------
    private void initTopPager(){
    }

    /**
     * 选择背景图
     */
    private void selectBg(){
        BgSelectManager manager = new BgSelectManager();
        manager.gbSelect(this, getActivity());
    }



    //----接口----

    public void showBackground(Bitmap bitmap){
        releaseBitmap();
        img.setImageBitmap(bitmap);
        oldBitmap = bitmap;
    }

    public void showBackground(int imageId){
        releaseBitmap();
        img.setImageResource(imageId);
    }

    public void releaseBitmap(){
        if(oldBitmap != null){
            oldBitmap.recycle();
            oldBitmap = null;
        }
    }
}
