package com.dmn.healthassistant.ui.individuality.collection.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class CollectionItemBean implements Serializable {

    private String title;
    private String content;
    private Bitmap imgBitmap;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

//    public void setImgBitmap(Bitmap bitmap) {
//        this.imgBitmap = bitmap;
//    }

    @Override
    public String toString() {
        return "CollectionItemBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgBitmap=" + imgBitmap +
                '}';
    }

    public void setImgResId(int test1) {
    }
}
