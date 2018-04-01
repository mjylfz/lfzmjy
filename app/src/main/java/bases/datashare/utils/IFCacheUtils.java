package bases.datashare.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import app.MyApplication;
import utils.StrUtils;

/**
 * Created by LFZ on 2017/11/17.
 * 封装prefutils工具
 */

public class IFCacheUtils {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String NAME = "cache";

    private static IFCacheUtils ifCacheInstance;

    public IFCacheUtils(){
        context = MyApplication.getContext();
        sharedPreferences = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static IFCacheUtils getInstance() {
        if (ifCacheInstance == null) {
            synchronized (IFCacheUtils.class) {
                if (ifCacheInstance == null) {
                    ifCacheInstance = new IFCacheUtils();
                }
            }
        }
        return ifCacheInstance;
    }

    //对外接口
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void saveSet(String key, Set value) {
        editor.putStringSet(key, value);
        editor.commit();
    }

    public Set getSet(String key) {
        return sharedPreferences.getStringSet(key, new HashSet<String>());
    }

    public void saveObject(String key, Object object){
        String value = StrUtils.object2String(object);
        editor.putString(key, value);
        editor.commit();
    }

    public Object getObject(String key){
        Object object = StrUtils.string2Object(sharedPreferences.getString(key,""));
        return object;
    }
}
