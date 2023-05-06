package com.dmn.healthassistant.ui.information;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.information.bean.ItemBean;

public class NewsDetailActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private TextView mTvContent;
    private ImageView mIvImage;

    private ItemBean mNewsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        initData();
        initEvent();
    }

    private void initView(){

        mIvImage = findViewById(R.id.iv_img);
        mTvContent = findViewById(R.id.tv_content);
        mTvTitle = findViewById(R.id.tv_title);
    }

    private void initData(){
        Intent intent = getIntent();
        mNewsBean = (ItemBean) intent.getSerializableExtra("item");
    }

    private void initEvent(){
        if (mNewsBean != null){
            mTvTitle.setText(mNewsBean.getTitle());
            mTvContent.setText(mNewsBean.getContent());
            mIvImage.setImageBitmap(mNewsBean.getImgBitmap());
        }
    }

}