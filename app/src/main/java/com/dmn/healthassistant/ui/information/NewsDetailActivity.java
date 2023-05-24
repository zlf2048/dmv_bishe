package com.dmn.healthassistant.ui.information;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.information.bean.ItemBean;
import com.minapp.android.sdk.database.Record;
import com.minapp.android.sdk.database.Table;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = (String) bundle.getSerializable("id");
        String type = (String) bundle.getSerializable("type");
        String html = (String) bundle.getSerializable("content");
        if (type != null && !type.isEmpty()){
            Table article = new Table(type);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Record record = article.fetchRecord(id);
                        String style = "<style>img{display: inline; height: auto; max-width: 100%;}</style>";
                        String content = style + record.getString("content");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                WebView webView = new WebView(NewsDetailActivity.this);
                                setContentView(webView);
                                webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
                            }
                        });
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }).start();
        } else {
            String style = "<style>img{display: inline; height: auto; max-width: 100%;}</style>";
            String content = style + html;
            WebView webView = new WebView(NewsDetailActivity.this);
            setContentView(webView);
            webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
        }
    }
}

//这段代码是一个 Android 应用程序中的 NewsDetailActivity 类。它继承自 AppCompatActivity 类，用于显示新闻详情。
//
//在 onCreate 方法中，首先通过 getIntent 方法获取了启动该活动的 Intent 对象。然后通过 getExtras 方法获取了 Intent 对象中携带的 Bundle 对象。接着从 Bundle 对象中获取了 id 和 type 两个值。
//
//然后创建了一个 Table 对象，并传入 type 值作为参数。接着，在一个新的线程中执行了获取新闻详情的操作。调用了 Table 类的 fetchRecord 方法，传入 id 值，获取指定新闻的详细信息。
//
//接着，在主线程中创建了一个 WebView 控件，并设置其内容为新闻详情。最后将 WebView 控件设置为当前活动的内容视图。