package home;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.MyApplication;
import bean.Anivsy;
import utils.BaseDBHelper;
import utils.DateUtils;
import utils.ImageUtils.AnivsyDBHelper;

/**
 * Created by LFZ on 2017/12/3.
 * 纪念日的存储的业务层
 */

public class AnivsyDBManager {
    private static AnivsyDBHelper anivsyDBHelper;
    public static AnivsyDBHelper getDBInstance(){
        if(anivsyDBHelper == null){
            synchronized (AnivsyDBManager.class){
                if(anivsyDBHelper == null){
                    anivsyDBHelper = new AnivsyDBHelper(MyApplication.getContext(), BaseDBHelper.DB_NAME, null, 1 );
                }
            }
        }
        return anivsyDBHelper;
    }


    /**
     * 全部纪念日
     * @return
     */
    public static List<Anivsy> getAllAnivsy(){
        List<Anivsy> anivsies = new ArrayList<>();
        SQLiteDatabase db = getDBInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Anivsy",null);
        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Anivsy anivsy = new Anivsy();
                Date date = DateUtils.str2date(cursor.getString(cursor.getColumnIndex("date")),DateUtils.FMT);
                anivsy.setDate(date);
                anivsy.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                anivsy.setGapDate(DateUtils.getGapCount(date, new Date()));
                anivsies.add(anivsy);
            }
        }
        cursor.close();
        db.close();
        return anivsies;
    }

    /**
     * 获得一个纪念日
     * @param id
     * @return
     */
    public static Anivsy getAnivsy(String id){
        SQLiteDatabase db = getDBInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Anivsy where id= '" +  id +"'",null);
        if(cursor != null && cursor.getCount() > 0){
            if(cursor.moveToNext()){
                Anivsy anivsy = new Anivsy();
                Date date = DateUtils.str2date(cursor.getString(cursor.getColumnIndex("date")),DateUtils.FMT);
                anivsy.setDate(date);
                anivsy.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                anivsy.setGapDate(DateUtils.getGapCount(date, new Date()));
                cursor.close();
                db.close();
                return anivsy;
            }
        }
        return null;
    }

    /**
     * 插入一条纪念日
     * @param id
     * @param description
     * @param date
     */
    public static void insertAnivsy(String id, String description, String date){
        SQLiteDatabase db = getDBInstance().getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("description", description);
        contentValues.put("date", date);
        db.insert("Anivsy",null ,contentValues);
        db.close();
    }

    /**
     * 插入多条
     */
    public static void insertAllAnivsy(List<Anivsy> anivsies){
        for(int i = 0;i < anivsies.size(); i++){
            Anivsy anivsy = anivsies.get(i);
            insertAnivsy(anivsy.getId(),anivsy.getDescription(), DateUtils.date2str(anivsy.getDate(), DateUtils.FMT));
        }
    }

}
