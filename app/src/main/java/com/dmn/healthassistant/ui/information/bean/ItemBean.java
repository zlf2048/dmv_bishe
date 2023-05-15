package com.dmn.healthassistant.ui.information.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ItemBean implements Serializable {

    private String title;
    private String content;
    private Bitmap imgBitmap;

    private String id;
    private String html;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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
