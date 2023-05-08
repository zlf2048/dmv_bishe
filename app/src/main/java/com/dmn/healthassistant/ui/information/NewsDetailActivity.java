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
    }
}