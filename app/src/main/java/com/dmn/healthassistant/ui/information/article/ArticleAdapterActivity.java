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
import com.dmn.healthassistant.ui.information.journal.JournalAdapterActivity;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    }

    private void initEvent(){
        mMyAdapter = new MyAdapter(this,mBeanList);
        System.out.println(mListView);
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemBean itemBean = mBeanList.get(i);
                Table article = new Table("article");

                Intent intent = new Intent(ArticleAdapterActivity.this, NewsDetailActivity.class);
                intent.putExtra("id", itemBean.getId());
                intent.putExtra("type", "article");
                startActivity(intent);
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

        int threadPoolSize = 5; // 线程池大小
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize); // 创建一个固定大小的线程池
        Bitmap[] bitmaps = new Bitmap[20];
        for (int i = 0; i < record.length && record[i] != null; i++) {
            final int[] index = new int[]{i};
            Record record1 = record[i];
            executor.execute(new Runnable() { // 提交任务给线程池执行
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

                    bitmaps[index[0]] = bitmap;
                    System.out.println(bitmaps[index[0]]);
                }
            });
        }
        executor.shutdown(); // 关闭线程池
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // 处理异常
        }

        mBeanList = new ArrayList<>();

        for (int i = 0; i < record.length && record[i] != null; i++) {
            Record record1 = record[i];

            ItemBean newsBean = new ItemBean();
            newsBean.setId(record1.getId());
            newsBean.setTitle(record1.getString("title"));
            newsBean.setContent(record1.getString("abstract"));
            newsBean.setImgBitmap(bitmaps[i]);

            mBeanList.add(newsBean);
        }
    }

    private void initView(){
        mListView = findViewById(R.id.lv);
    }
}