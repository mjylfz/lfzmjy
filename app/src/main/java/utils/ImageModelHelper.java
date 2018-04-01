package utils;

import java.util.ArrayList;
import java.util.List;

import bases.views.topviewpagerwidget.WidgetModel;

/**
 * Created by LFZ on 2017/11/4.
 * 图片数据处理
 */

public class ImageModelHelper {

    /**
     * 把只有url的list转成图片的数据
     * @param urlList
     * @return
     */
    public static List<WidgetModel.DataModel> convertToImageModel(List<String> urlList){
        List<WidgetModel.DataModel> imageModelList = new ArrayList<>();
        for(int i = 0; i<urlList.size(); i++){
            imageModelList.add(new WidgetModel.DataModel(urlList.get(i), ""));
        }
        return imageModelList;
    }
}
