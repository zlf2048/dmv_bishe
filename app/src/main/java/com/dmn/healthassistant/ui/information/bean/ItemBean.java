package com.dmn.healthassistant.ui.information.bean;

import java.io.Serializable;

public class ItemBean implements Serializable {

    private String title;
    private String content;
    private int imgResId;


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

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgResId=" + imgResId +
                '}';
    }
}
