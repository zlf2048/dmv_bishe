package com.dmn.healthassistant.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dmn.healthassistant.model.Collection;
import com.minapp.android.sdk.user.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CollectionUtil extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "collection.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "collection";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_IMG = "img";
    private static final String COLUMN_ABSTRACT= "abstract";
    private static final String COLUMN_CONTENT = "content";

    public CollectionUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_IMG + " BLOB," +
                COLUMN_ABSTRACT + " TEXT," +
                COLUMN_CONTENT + " TEXT " +
                ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ...
    }

    public void saveCollection(Collection collection) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, collection.getId());
        values.put(COLUMN_TITLE, collection.getTitle());
        values.put(COLUMN_ABSTRACT, collection.getAbstr());
        values.put(COLUMN_CONTENT, collection.getContent());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        collection.getImg().compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        byte[] image = outputStream.toByteArray();
        values.put(COLUMN_IMG, image);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Collection> getCollections() {
        List<Collection> collections = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            @SuppressLint("Range") String abstr = cursor.getString(cursor.getColumnIndex(COLUMN_ABSTRACT));
            @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT));
            @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMG));
            Bitmap img = BitmapFactory.decodeByteArray(image, 0, image.length);

            Collection collection = new Collection(id, title, img, abstr, content);
            collections.add(collection);
        }
        cursor.close();
        db.close();
        return collections;
    }

    public void deleteCollection(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = new String[]{id};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    public boolean isCollectionExist(String id) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean isExist = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isExist;
    }
}
