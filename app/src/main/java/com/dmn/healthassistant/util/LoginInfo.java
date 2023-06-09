package com.dmn.healthassistant.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dmn.healthassistant.model.Userinfo;
import com.minapp.android.sdk.database.Table;
import com.minapp.android.sdk.user.User;

public class LoginInfo extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "healthassistant.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "loginInfo";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_NICKNAME = "nickname";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";

    public LoginInfo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY," +
                COLUMN_NICKNAME + " TEXT," +
                COLUMN_GENDER + " INTEGER," +
                COLUMN_CITY + " TEXT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PHONE + " TEXT" +
                ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ...
    }

    public void saveLoginInfo(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_NICKNAME, user.getNickname());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_CITY, user.getCity());
        values.put(COLUMN_EMAIL, user.getEmail());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Userinfo getLoginInfo() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String nickname = cursor.getString(cursor.getColumnIndex(COLUMN_NICKNAME));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            @SuppressLint("Range") Integer gender = cursor.getInt(cursor.getColumnIndex(COLUMN_GENDER));
            @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            cursor.close();
            db.close();
            return new Userinfo(id, nickname, username, gender, city, email);
        } else {
            cursor.close();
            db.close();
            return null;
        }
    }

    public void updateLoginInfo(Userinfo userinfo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NICKNAME, userinfo.getNickname());
        values.put(COLUMN_GENDER, userinfo.getGender());
        values.put(COLUMN_CITY, userinfo.getCity());
        values.put(COLUMN_EMAIL, userinfo.getEmail());
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(userinfo.getId())};
        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }

    public void deleteLoginInfo(Userinfo userinfo) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(userinfo.getId())};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }
}
