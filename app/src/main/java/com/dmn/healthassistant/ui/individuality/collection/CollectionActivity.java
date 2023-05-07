package com.dmn.healthassistant.ui.individuality.collection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.collection.adapter.CollectionAdapter;
import com.dmn.healthassistant.ui.individuality.collection.bean.CollectionItemBean;
import com.dmn.healthassistant.ui.information.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    private ListView mListView;
    private List<CollectionItemBean> mBeanList;
    private CollectionAdapter mCollectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initView();
        initData();
        initEvent();
    }

    private void initEvent(){
        mCollectionAdapter = new CollectionAdapter(this,mBeanList);
        mListView.setAdapter(mCollectionAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CollectionItemBean itemBean = mBeanList.get(i);
                String title = itemBean.getTitle();
                Intent intent = new Intent(CollectionActivity.this, NewsDetailActivity.class);
                intent.putExtra("item",itemBean);
                startActivity(intent);

                Toast.makeText(CollectionActivity.this, "你点击了"+i+title, Toast.LENGTH_SHORT).show();
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

        CollectionItemBean newsBean1 = new CollectionItemBean();
        newsBean1.setTitle("雨中漫步");
        newsBean1.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean1.setImgResId(R.drawable.test1);

        CollectionItemBean newsBean2 = new CollectionItemBean();
        newsBean2.setTitle("林间穿梭");
        newsBean2.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean2.setImgResId(R.drawable.test2);

        CollectionItemBean newsBean3 = new CollectionItemBean();
        newsBean3.setTitle("旅行花海");
        newsBean3.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean3.setImgResId(R.drawable.test3);

        CollectionItemBean newsBean4 = new CollectionItemBean();
        newsBean4.setTitle("非平衡的线");
        newsBean4.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean4.setImgResId(R.drawable.test4);

        CollectionItemBean newsBean5 = new CollectionItemBean();
        newsBean5.setTitle("坐看云起时");
        newsBean5.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean5.setImgResId(R.drawable.test5);

        CollectionItemBean newsBean6 = new CollectionItemBean();
        newsBean6.setTitle("美好的记忆");
        newsBean6.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean6.setImgResId(R.drawable.test6);

        CollectionItemBean newsBean7 = new CollectionItemBean();
        newsBean7.setTitle("久违的感动");
        newsBean7.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean7.setImgResId(R.drawable.test7);

        CollectionItemBean newsBean8 = new CollectionItemBean();
        newsBean8.setTitle("流浪日记");
        newsBean8.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean8.setImgResId(R.drawable.test8);

        CollectionItemBean newsBean9 = new CollectionItemBean();
        newsBean9.setTitle("山的尽头");
        newsBean9.setContent("人生到处知何似，应似飞鸿踏雪泥。人的一生，辗转各处，像什么呢？正像到处飞的鸟类。到处飞是鸟的命运，是必然，偶尔在雪地上留下脚印，是偶然。");
        newsBean9.setImgResId(R.drawable.test9);

        CollectionItemBean newsBean10 = new CollectionItemBean();
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