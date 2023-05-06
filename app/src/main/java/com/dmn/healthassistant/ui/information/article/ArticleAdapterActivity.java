package com.dmn.healthassistant.ui.information.article;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.information.NewsDetailActivity;
import com.dmn.healthassistant.ui.information.adapter.MyAdapter;
import com.dmn.healthassistant.ui.information.bean.ItemBean;
import com.minapp.android.sdk.database.Record;
import com.minapp.android.sdk.database.Table;
import com.minapp.android.sdk.database.query.Query;
import com.minapp.android.sdk.util.PagedList;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArticleAdapterActivity extends AppCompatActivity {

    private ListView mListView;
    private List<ItemBean> mBeanList;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_adapter);

        initView();
        initData();
        initEvent();

//        Table article = new Table("article");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Record record = article.fetchRecord("644e83326d7f8c413ec0ebdc");
//                    String content = record.getString("content");
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            WebView webView = new WebView(ArticleAdapterActivity.this);
//                            setContentView(webView);
//                            webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
//                        }
//                    });
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        }).start();
    }

    private void initEvent(){
        mMyAdapter = new MyAdapter(this,mBeanList);
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemBean itemBean = mBeanList.get(i);
                String title = itemBean.getTitle();
                Intent intent = new Intent(ArticleAdapterActivity.this, NewsDetailActivity.class);
                intent.putExtra("item",itemBean);
                startActivity(intent);

                Toast.makeText(ArticleAdapterActivity.this, "你点击了"+i+title, Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
    }

    private void initData(){
        Table article = new Table("article");
        Record[] record = new Record[20];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Query query = new Query().offset(0).limit(20);
                    PagedList<Record> records = article.query(query);
                    List<Record> list = records.getObjects();

                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        record[i] = list.get(i);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mBeanList = new ArrayList<>();

        for (int i = 0; i < record.length && record[i] != null; i++) {
            Record record1 = record[i];

            ItemBean newsBean = new ItemBean();
            newsBean.setTitle(record1.getString("title"));
            newsBean.setContent(record1.getString("abstract"));

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = null;
                    try {
                        URL url = new URL(record1.getString("img"));
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(input);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    newsBean.setImgBitmap(bitmap);
                }
            });
            thread1.start();

            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mBeanList.add(newsBean);
        }
    }

    private void initView(){
        mListView = findViewById(R.id.lv);
    }
}