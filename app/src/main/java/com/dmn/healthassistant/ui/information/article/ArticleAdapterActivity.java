package com.dmn.healthassistant.ui.information.article;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        mBeanList = new ArrayList<>();

        ItemBean newsBean1 = new ItemBean();
        newsBean1.setTitle("雨中漫步");
        newsBean1.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean1.setImgResId(R.drawable.test1);

        ItemBean newsBean2 = new ItemBean();
        newsBean2.setTitle("林间穿梭");
        newsBean2.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean2.setImgResId(R.drawable.test2);

        ItemBean newsBean3 = new ItemBean();
        newsBean3.setTitle("旅行花海");
        newsBean3.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean3.setImgResId(R.drawable.test3);

        ItemBean newsBean4 = new ItemBean();
        newsBean4.setTitle("非平衡的线");
        newsBean4.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean4.setImgResId(R.drawable.test4);

        ItemBean newsBean5 = new ItemBean();
        newsBean5.setTitle("坐看云起时");
        newsBean5.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean5.setImgResId(R.drawable.test5);

        ItemBean newsBean6 = new ItemBean();
        newsBean6.setTitle("美好的记忆");
        newsBean6.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean6.setImgResId(R.drawable.test6);

        ItemBean newsBean7 = new ItemBean();
        newsBean7.setTitle("久违的感动");
        newsBean7.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean7.setImgResId(R.drawable.test7);

        ItemBean newsBean8 = new ItemBean();
        newsBean8.setTitle("流浪日记");
        newsBean8.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean8.setImgResId(R.drawable.test8);

        ItemBean newsBean9 = new ItemBean();
        newsBean9.setTitle("山的尽头");
        newsBean9.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean9.setImgResId(R.drawable.test9);

        ItemBean newsBean10 = new ItemBean();
        newsBean10.setTitle("行到水穷处");
        newsBean10.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean10.setImgResId(R.drawable.test10);


        mBeanList.add(newsBean1);
        mBeanList.add(newsBean2);
        mBeanList.add(newsBean3);
        mBeanList.add(newsBean4);
        mBeanList.add(newsBean5);
        mBeanList.add(newsBean6);
        mBeanList.add(newsBean7);
        mBeanList.add(newsBean8);
        mBeanList.add(newsBean9);
        mBeanList.add(newsBean10);
        mBeanList.add(newsBean1);
        mBeanList.add(newsBean2);
        mBeanList.add(newsBean3);
        mBeanList.add(newsBean4);
        mBeanList.add(newsBean5);
        mBeanList.add(newsBean6);
        mBeanList.add(newsBean7);
        mBeanList.add(newsBean8);
        mBeanList.add(newsBean9);
        mBeanList.add(newsBean10);
    }

    private void initView(){
        mListView = findViewById(R.id.lv);
    }
}