package bases.datashare.service;

import bases.baseinterface.BaseCallBack;
import bases.datashare.utils.CallBackCenter;
import home.presenter.TakePhotoListener;

/**
 * Created by LFZ on 2017/11/20.
 * 回调注册的业务管理
 */

public class CallBackManager {
    public static final String MAIN_ACTIVITY_RESULT = "mainActivityResult";
    public static final String SETTING_ACTIVITY_RESULT = "settingActivityResult";

    /**
     * 图片选择的注册
     * @param listener
     */
    public static void registMainResultListener(TakePhotoListener listener){
        CallBackCenter.registListener(MAIN_ACTIVITY_RESULT,listener);
    }

    public static TakePhotoListener getMainResultListener(){
        BaseCallBack listener = CallBackCenter.getListener(MAIN_ACTIVITY_RESULT);
        if(listener != null){
            return (TakePhotoListener)listener;
        }
        return null;
    }

    /**
     * 设置页图片选择的回调
     * @param listener
     */
    public static void registSettingResultListener(TakePhotoListener listener){
        CallBackCenter.registListener(SETTING_ACTIVITY_RESULT,listener);
    }

    public static TakePhotoListener getSettingResultListener(){
        BaseCallBack listener = CallBackCenter.getListener(SETTING_ACTIVITY_RESULT);
        if(listener != null){
            return (TakePhotoListener)listener;
        }
        return null;
    }
}
