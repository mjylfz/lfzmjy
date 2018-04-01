package bases.datashare.utils;

import android.util.SparseArray;

import java.util.Map;

/**
 * Created by LFZ on 2017/11/20.
 * 数据共享中心,只能存储轻量级数据
 */

public class DataShareCenter {
    private static SparseArray<Object> sparseArray = new SparseArray<>();

    public static void saveData(int key, Object object){
        sparseArray.put(key, object);
    }

    public static Object getData(int key){
        return sparseArray.get(key);
    }

    public static void clearData(int key){
        sparseArray.remove(key);
    }

    public static boolean has(int key){
        return !(getData(key) == null);
    }
}
