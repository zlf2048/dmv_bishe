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

//这段代码定义了一个名为 ItemBean 的类，它实现了 Serializable 接口，表示该类的对象可以被序列化。
//
//ItemBean 类中定义了四个成员变量，分别表示标题、内容、图片和 ID。并为每个成员变量提供了对应的 getter 和 setter 方法，用于获取和设置成员变量的值。
//
//此外，还重写了 toString 方法，用于返回 ItemBean 对象的字符串表示形式。
//
//ItemBean 类通常用于存储 ListView 或 GridView 等控件中每个数据项的信息。
