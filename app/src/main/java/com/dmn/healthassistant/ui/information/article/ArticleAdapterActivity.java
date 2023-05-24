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
            newsBean.setHtml(record1.getString("content"));
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

//这段代码是一个 Android 应用程序中的 ArticleAdapterActivity 类。它继承自 AppCompatActivity 类，用于显示文章列表。
//
//在 onCreate 方法中，调用了 setContentView 方法设置了该活动的布局文件为 R.layout.activity_article_adapter。然后分别调用了 initView、initData 和 initEvent 方法，用于初始化视图、数据和事件。
//
//在 initView 方法中，通过 findViewById 方法获取了布局文件中的 ListView 控件。
//
//在 initData 方法中，首先创建了一个 Table 对象，并传入 “article” 作为参数。然后在一个新的线程中执行了获取文章列表的操作。调用了 Table 类的 query 方法，传入一个 Query 对象，获取指定范围内的文章列表。
//
//接着，在主线程中等待新线程执行完毕。然后创建了一个固定大小的线程池，并使用线程池执行获取文章图片的操作。
//
//最后，在主线程中等待线程池中所有任务执行完毕。然后遍历文章列表，为每篇文章创建一个 ItemBean 对象，并将其添加到 ItemBean 对象列表中。
//
//在 initEvent 方法中，创建了一个 MyAdapter 对象，并传入 ItemBean 对象列表作为参数。然后将 MyAdapter 对象设置为 ListView 的适配器。接着为 ListView 设置了一个点击监听器。当用户点击某个数据项时，会跳转到 NewsDetailActivity，并传递该数据项的 id 和 type 值。