package home.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.lfz.lfzmjy.R;

import bases.datashare.service.CallBackManager;
import constants.ImgUrl;
import constants.LocalFile;
import home.HomeFragments.OurFragment;
import utils.DialogUtils;
import utils.ImageSelectUtils;
import utils.ImageUtils.ImageZip;

import static android.app.Activity.RESULT_OK;

/**
 * Created by LFZ on 2017/11/21.
 * 背景图片选择的业务层
 */

public class BgSelectManager {
    private View view;
    private Button btnTakePhoto;
    private Button btnSelect;
    private Button btnDefault;
    private Context context;
    private ImageSelectUtils imageSelectUtils;
    private OurFragment fragment;
    private AlertDialog dialog;
    private String currentPhoto;

    public void gbSelect(OurFragment fragment, Context context) {
        this.context = context;
        this.fragment = fragment;
        imageSelectUtils = new ImageSelectUtils((Activity) context);
        bindView();
        showDialog(context);
        registPhotoSelect();
    }

    /**
     * 初始化view和监听事件
     */
    private void bindView() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_sb, null);
        btnTakePhoto = (Button) view.findViewById(R.id.take_photo);
        btnSelect = (Button) view.findViewById(R.id.select_image);
        btnDefault = (Button) view.findViewById(R.id.defaultbg);

        DialogSelectListener listener = new DialogSelectListener();
        btnTakePhoto.setOnClickListener(listener);
        btnSelect.setOnClickListener(listener);
        btnDefault.setOnClickListener(listener);
    }

    /**
     * 显示选择的对话框
     *
     * @param context
     */
    private void showDialog(Context context) {
        if (view != null) {
            dialog = DialogUtils.getInstance().showCustomedDialog(context, view);
        }
    }

    /**
     * 接收图片
     */
    private void registPhotoSelect() {
        //注册选择图片和拍照的监听
        CallBackManager.registMainResultListener(new TakePhotoListener() {
            @Override
            public void dataBack(int requestCode, int resultCode, Intent data) {
                resolveImageData(requestCode, resultCode, data);
            }
        });
    }

    /**
     * 处理图片数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void resolveImageData(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ImageSelectUtils.CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //压缩图片并显示
                    Bitmap bitmap = ImageZip.zipBitmapByPath(context, imageSelectUtils.getImagePath(data), 1920, 1080);
                    fragment.showBackground(bitmap);
                }

                break;

            case ImageSelectUtils.TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //压缩图片并显示
                    Bitmap bitmap = ImageZip.zipBitmapByPath(context, LocalFile.IMAGE_FILEPATH + currentPhoto + ".jpg", 800, 450);
                    fragment.showBackground(bitmap);
                }

                break;
        }
    }

    /**
     * 点击监听
     */
    class DialogSelectListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (imageSelectUtils == null) {
                return;
            }
            if(dialog != null){
                dialog.cancel();
            }
            switch (v.getId()) {
                case R.id.take_photo:
                    currentPhoto = String.valueOf(System.currentTimeMillis());
                    imageSelectUtils.takePhoto(LocalFile.IMAGE_FILEPATH + currentPhoto + ".jpg");
                    break;
                case R.id.select_image:
                    imageSelectUtils.choosePhoto();
                    break;
                case R.id.defaultbg:
                    fragment.showBackground(R.drawable.main);
                    break;
            }
        }
    }
}
