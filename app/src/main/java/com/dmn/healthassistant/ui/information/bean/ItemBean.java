package com.dmn.healthassistant.ui.information.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ItemBean implements Serializable {

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

    public void setImgBitmap(Bitmap bitmap) {
        this.imgBitmap = bitmap;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgBitmap=" + imgBitmap +
                '}';
    }
}
