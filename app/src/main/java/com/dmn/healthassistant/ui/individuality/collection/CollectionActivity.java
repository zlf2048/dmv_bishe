package com.dmn.healthassistant.ui.individuality.collection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.model.Collection;
import com.dmn.healthassistant.ui.individuality.collection.adapter.CollectionAdapter;
import com.dmn.healthassistant.ui.individuality.collection.bean.CollectionItemBean;
import com.dmn.healthassistant.ui.individuality.personal.BasicInformationActivity;
import com.dmn.healthassistant.ui.information.NewsDetailActivity;
import com.dmn.healthassistant.ui.information.article.ArticleAdapterActivity;
import com.dmn.healthassistant.ui.information.bean.ItemBean;
import com.dmn.healthassistant.util.CollectionUtil;
import com.dmn.healthassistant.util.LoginInfo;

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

                Intent intent = new Intent(CollectionActivity.this, NewsDetailActivity.class);
                intent.putExtra("id", itemBean.getId());
                intent.putExtra("content", itemBean.getHtml());
                startActivity(intent);
            }
        });
    }

    private void initData(){
        mBeanList = new ArrayList<>();

        CollectionUtil collectionUtil = new CollectionUtil(CollectionActivity.this);
        List<Collection> collections = collectionUtil.getCollections();

        if (collections.size() == 0) {
            // 数据库中没有收藏的文章
            TextView textView = new TextView(this);
            textView.setText("还未有收藏");
            textView.setTextSize(24); // 设置字体大小
            textView.setGravity(Gravity.CENTER); // 设置文字居中

            // 设置 TextView 的位置和大小
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            textView.setLayoutParams(layoutParams);

            setContentView(textView);
        } else {
            for (Collection collection : collections) {
                // 获取 Collection 对象的信息
                String id = collection.getId();
                String title = collection.getTitle();
                String abstr = collection.getAbstr();
                String content = collection.getContent();
                Bitmap img = collection.getImg();

                CollectionItemBean newsBean = new CollectionItemBean();
                newsBean.setId(id);
                newsBean.setTitle(title);
                newsBean.setContent(abstr);
                newsBean.setImgBitmap(img);
                newsBean.setHtml(content);

                mBeanList.add(newsBean);
            }
        }
    }

    private void initView(){
        mListView = findViewById(R.id.lv);
    }
}