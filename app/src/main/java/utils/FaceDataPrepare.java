package utils;

import face.FaceManager;

/**
 * Created by LFZ on 2018/3/11.
 */

public class FaceDataPrepare extends DataPrepare {

    private static FaceDataPrepare faceDataPrepare;

    public static FaceDataPrepare getInstance(){
        if(faceDataPrepare == null){
            synchronized (AnivsyDataPrepare.class){
                if(faceDataPrepare == null){
                    faceDataPrepare = new FaceDataPrepare();
                }
            }
        }
        return faceDataPrepare;
    }
    @Override
    public void init() {
        //加载所有面部
        FaceManager.getInstance().getmFaceDB().loadFaces();
    }
}
