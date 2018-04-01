package utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import app.MyApplication;

import static android.app.Activity.RESULT_OK;

/**
 * Created by LFZ on 2017/3/31.
 * 头像选择器
 */

public class ImageSelectUtils {

    private Activity context;
    //选择头像
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    public static final int CROP_PHOTO = 3;



    public ImageSelectUtils(Activity context) {
        this.context = context;
    }

    /**
     * 调用相机
     *
     * @param filePath
     */
    public void takePhoto(String filePath) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
            Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            media.setData(contentUri);
            context.sendBroadcast(media);
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, filePath);
            uri = MyApplication.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        context.startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 调用图库
     */
    public void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        context.startActivityForResult(intent, CHOOSE_PHOTO);
    }

    /**
     * 剪切
     */
    public void photoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, CROP_PHOTO);
    }

    /**
     *
     */
    public void showCropPhoto(ImageView img, Intent data, int resultCode) {
        Bundle bundle = new Bundle();
        try {
            bundle = data.getExtras();
            if (resultCode == RESULT_OK) {
                Bitmap bitmap = bundle.getParcelable("data");
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
                //修改ImageView的图片
                img.setImageBitmap(bitmap);
            }
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmap(Intent data, int resultCode) {
        Bundle bundle;
        bundle = data.getExtras();
        if (resultCode == RESULT_OK) {
            return bundle.getParcelable("data");
        }
        return null;
    }

    /**
     * 获得图片路径
     *
     * @param data
     * @return
     */
    public String getImagePath(Intent data) {
        Uri imageUri = data.getData();
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = MyApplication.getContext().getContentResolver().query(imageUri, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String imagePath = c.getString(columnIndex);
        c.close();
        return imagePath;
    }
}
