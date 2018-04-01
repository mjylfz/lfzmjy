package app;

import android.app.Application;
import android.content.Context;

import constants.ImgUrl;
import face.FaceManager;
import utils.AppException;

/**
 * Created by LFZ on 2017/10/24.
 * 全局
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //图片路径
        ImgUrl.initImageUrlList();
        FaceManager.getInstance().init();
//        AppException appException = AppException.getInstance();
//        appException.init(context);
    }

    public static Context getContext(){
        return context;
    }
}
