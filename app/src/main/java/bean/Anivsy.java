package bean;

import java.util.Date;

/**
 * Created by LFZ on 2017/12/3.
 * 纪念日
 */

public class Anivsy {
    private String id;
    private String description;
    private Date date;
    private int gapDate;

    public Anivsy(){
    }

    public Anivsy(String id, String description, Date date, int gapDate){
        this.id = id;
        this.description = description;
        this.date = date;
         this.gapDate = gapDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGapDate() {
        return gapDate;
    }

    public void setGapDate(int gapDate) {
        this.gapDate = gapDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
