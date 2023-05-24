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
//这段代码定义了一个名为 MySqlite 的类，它继承自 SQLiteOpenHelper 类，用于创建和管理 SQLite 数据库。
//
//在 MySqlite 类的构造方法中，调用了父类的构造方法，传入了 context、数据库名称和版本号等参数。
//
//在 onCreate 方法中，使用 execSQL 方法执行了四条 SQL 语句，分别创建了 intoTable、outTable、intoTypes 和 outTypes 四个表。
//
//在 onUpgrade 方法中，处理数据库升级的逻辑。但该方法并未实现任何功能。
