package constants;

import android.os.Environment;

/**
 * Created by LFZ on 2017/11/29.
 */

public class LocalFile {
    public static String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String IMAGE_FILEPATH = ROOT_PATH + "/com.lfz.lfzmjy/imageFile/bg";

    public static String FACE_IMAGE_PATH = ROOT_PATH + "/com.lfz.lfzmjy/imageFile/face";
}
