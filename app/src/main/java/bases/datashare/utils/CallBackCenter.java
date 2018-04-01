package bases.datashare.utils;

import java.util.HashMap;
import java.util.Map;

import bases.baseinterface.BaseCallBack;
import bases.simple.SimpleListenterCallBack;

/**
 * Created by LFZ on 2017/11/20.
 * 接口的统一注册和调用，用来管理消息通知
 */

public class CallBackCenter {
    private static Map<String,BaseCallBack> listenersMap = new HashMap<>();

    /**
     * 注册监听
     * @param key
     * @param callBack
     */
    public static void registListener(String key, BaseCallBack callBack){
        listenersMap.put(key, callBack);
    }

    /**
     * 触发回调, 只有简单接口
     * @param key
     */
    public static void queryListener(String key){
        if(hasRegistListener(key)){
            BaseCallBack callBack = listenersMap.get(key);
            if(callBack instanceof SimpleListenterCallBack){
                ((SimpleListenterCallBack)callBack).callback();
            }
        }
    }

    /**
     * 获得listener
     * @param key
     */
    public static BaseCallBack getListener(String key){
        if(hasRegistListener(key)){
            return listenersMap.get(key);
        }
        return null;
    }

    /**
     * 取消监听
     * @param key
     */
    public static void unregisterListener(String key){
        listenersMap.remove(key);
    }

    /**
     * 是否注册监听
     * @param key
     * @return
     */
    public static boolean hasRegistListener(String key){
        return listenersMap.containsKey(key);
    }
}
