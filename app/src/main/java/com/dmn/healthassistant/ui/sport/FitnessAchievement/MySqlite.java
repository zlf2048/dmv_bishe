package com.dmn.healthassistant.ui.sport.FitnessAchievement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlite extends SQLiteOpenHelper {
    private Context context;
    public MySqlite(@Nullable Context context, int version) {
        super(context, "caiwu.db", null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table intoTable("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "types text," +
                "money text," +
                "dates text," +
                "time text);");

        sqLiteDatabase.execSQL("create table outTable("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "types text," +
                "money text," +
                "dates text," +
                "time text);");

        sqLiteDatabase.execSQL("create table intoTypes("+
                "name text);");

        sqLiteDatabase.execSQL("create table outTypes("+
                "name text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
