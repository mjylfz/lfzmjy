package bases.datashare.service;

import bases.datashare.utils.IFCacheUtils;

/**
 * Created by LFZ on 2017/11/20.
 * prefences的管理者,业务层
 */

public class DataPreferencesManager {

    private static final String FIRST_INSTALL = "FIRST_INSTALL";
    private static final String FACE_DETECTOR = "FACE_DETECTOR";

    /**
     * 首次安装
     */
    public static void firstInstall(){
        IFCacheUtils.getInstance().saveBoolean(FIRST_INSTALL, true);
    }

    public static boolean hasInstall(){
        return IFCacheUtils.getInstance().getBoolean(FIRST_INSTALL);
    }

    /**
     * 面部识别是否开启
     * @param faceDetect
     */
    public static void setFaceDetect(boolean faceDetect){
        IFCacheUtils.getInstance().saveBoolean(FACE_DETECTOR, faceDetect);
    }

    public static boolean getFaceDetect(){
        return IFCacheUtils.getInstance().getBoolean(FACE_DETECTOR);
    }
}
