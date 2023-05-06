package com.dmn.healthassistant.ui.information.journal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.information.NewsDetailActivity;
import com.dmn.healthassistant.ui.information.adapter.MyAdapter;
import com.dmn.healthassistant.ui.information.article.ArticleAdapterActivity;
import com.dmn.healthassistant.ui.information.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class JournalAdapterActivity extends AppCompatActivity {

    private ListView mListView;
    private List<ItemBean> mBeanList;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_adapter);

        initView();
        initData();
        initEvent();
    }

    private void initEvent(){
        mMyAdapter = new MyAdapter(this,mBeanList);
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemBean itemBean = mBeanList.get(i);
                String title = itemBean.getTitle();
                Intent intent = new Intent(JournalAdapterActivity.this, NewsDetailActivity.class);
                intent.putExtra("item",itemBean);
                startActivity(intent);

                Toast.makeText(JournalAdapterActivity.this, "你点击了"+i+title, Toast.LENGTH_SHORT).show();
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
//        newsBean1.setImgResId(R.drawable.test1);


        mBeanList.add(newsBean1);
    }

    private void initView(){
        mListView = findViewById(R.id.lv);
    }
}