package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LFZ on 2017/12/3.
 * 数据库的基类
 */

public abstract class BaseDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mydatabase";
    public BaseDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
