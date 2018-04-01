package constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFZ on 2017/10/30.
 * 图片的URL
 */

public class ImgUrl {

    private static boolean hasInit = false;
    private static ArrayList<String> urls;

    public static void initImageUrlList(){
        if(!hasInit){
            urls = new ArrayList<>();
            addDisneyUrl();
        }
        hasInit = true;
    }

    /**
     * 迪士尼图片
     */
    private static void addDisneyUrl(){
        urls.add(disney_url1);
        urls.add(disney_url2);
        urls.add(disney_url3);
        urls.add(disney_url4);
    }

    /**
     * 获取所有图片链接
     * @return
     */
    public static List<String> getUrls(){
        return urls;
    }
    //---------disney---------
    public static final String disney_url1 = "http://i1.bvimg.com/616218/7d4fc44ffa5bf0b8s.jpg";
    public static final String disney_url2 = "http://i1.bvimg.com/616218/a4b3ceca658e4b2ds.jpg";
    public static final String disney_url3 = "http://i1.bvimg.com/616218/68eb3dc8883ad02bs.jpg";
    public static final String disney_url4 = "http://i2.bvimg.com/616218/1d03c28134c4c3e2s.jpg";
}
