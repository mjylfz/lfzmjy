package utils.ImageUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import utils.BaseDBHelper;

/**
 * Created by LFZ on 2017/12/3.
 * 纪念日数据的工具类
 */

public class AnivsyDBHelper extends BaseDBHelper {

    public AnivsyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Anivsy(id varchar(50) primary key , description varchar(50), date varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
