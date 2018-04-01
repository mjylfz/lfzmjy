package bases.views.topviewpagerwidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFZ on 2017/7/23.
 * 轮播设置
 */

public class WidgetModel {

    private List<DataModel> data;
    private int length;
    private boolean touchStop;
    private boolean auto;
    private long time;

    public int getLength() {
        return length;
    }

    public WidgetModel setLength(int length) {
        this.length = length;
        return this;
    }

    public boolean isTouchStop() {
        return touchStop;
    }

    public WidgetModel setTouchStop(boolean touchStop) {
        this.touchStop = touchStop;
        return this;
    }

    public boolean isAuto() {
        return auto;
    }

    public WidgetModel setAuto(boolean auto) {
        this.auto = auto;
        return this;
    }

    public long getTime() {
        return time;
    }

    public List<DataModel> getData() {
        return data;
    }

    public WidgetModel setData(List<DataModel> data) {
        this.data = data;
        return this;
    }

    public WidgetModel setTime(long time) {
        this.time = time;
        return this;
    }

    public WidgetModel(){
        this.length = 0;
        this.touchStop = false;
        this.auto = true;
        this.time = 2000;
        this.data = new ArrayList<>();
    }

    /**
     * 每张图片的信息
     */
    public static class DataModel{
        private String url = "";
        private String title = "";
        public DataModel(String url ,String title){
            this.url = url;
            this.title = title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
