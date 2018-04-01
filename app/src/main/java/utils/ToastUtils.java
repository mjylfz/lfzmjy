package utils;

import android.widget.Toast;

import app.MyApplication;

/**
 * Created by LFZ on 2017/11/20.
 * 提示工具
 */

public class ToastUtils {

    public static void shortToast(String text){
        Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String text){
        Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_LONG).show();
    }
}
