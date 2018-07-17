package com.developpement.guide.solesmart;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by KGULJIN on 31/05/2018.
 */

public class SQLite extends SQLiteOpenHelper {

    private static final String TABLE_INFO = "table_informations";
    private static final String COL_ID = "ID";
    private static final String COL_CALORIES = "Calorie";
    private static final String COL_STEP = "Pas";
    private static final String COL_DATE = "Date";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_INFO  + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CALORIES + " INTEGER NOT NULL, "
            + COL_STEP+ " INTEGER NOT NULL, " + COL_DATE + " TEXT NOT NULL);";

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_INFO + ";");
        onCreate(db);
    }
}
