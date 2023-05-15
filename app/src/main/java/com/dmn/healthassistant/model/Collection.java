package com.dmn.healthassistant.model;

import android.graphics.Bitmap;

public class Collection {
    private String id;
    private String title;
    private Bitmap img;
    private String abstr;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Collection(String id, String title, Bitmap img, String abstr, String content) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.abstr = abstr;
        this.content = content;
    }
}
