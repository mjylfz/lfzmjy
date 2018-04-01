package home.presenter;

import android.content.Intent;

import bases.baseinterface.BaseCallBack;

/**
 * Created by LFZ on 2017/11/22.
 */

public interface TakePhotoListener extends BaseCallBack {
    void dataBack(int requestCode, int resultCode, Intent data);
}
