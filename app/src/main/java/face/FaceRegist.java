package face;

import com.arcsoft.facerecognition.AFR_FSDKFace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFZ on 2018/1/23.
 * 面部信息
 */

public class FaceRegist {

    String name;
    List<AFR_FSDKFace> faceList;

    public FaceRegist(String name){
        this.name = name;
        this.faceList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AFR_FSDKFace> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<AFR_FSDKFace> faceList) {
        this.faceList = faceList;
    }

    public void addFace(AFR_FSDKFace afr_fsdkFace){
        faceList.add(afr_fsdkFace);
    }
}
