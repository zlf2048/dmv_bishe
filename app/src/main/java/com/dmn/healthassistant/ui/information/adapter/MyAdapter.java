package com.dmn.healthassistant.ui.information.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.model.Collection;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;
import com.dmn.healthassistant.ui.information.bean.ItemBean;
import com.dmn.healthassistant.ui.sport.SportFragment;
import com.dmn.healthassistant.util.CollectionUtil;
import com.dmn.healthassistant.util.LogUtil;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<ItemBean> mBeanList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MyAdapter(Context context,List<ItemBean> beanList){
        this.mContext = context;
        this.mBeanList = beanList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return mBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //创建视图控件
        View v = mLayoutInflater.inflate(R.layout.list_item_layout,viewGroup, false);
        ImageView imageView = v.findViewById(R.id.iv_img);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        TextView tvContent = v.findViewById(R.id.tv_content);
        ImageView favoriteImageView = v.findViewById(R.id.iv_favorite);

        //为控件填充数据
        ItemBean itemBean = mBeanList.get(i);
        imageView.setImageBitmap(itemBean.getImgBitmap());
        tvTitle.setText(itemBean.getTitle());
        tvContent.setText(itemBean.getContent());

        //判断是否登录
        CollectionUtil collectionUtil = new CollectionUtil(mContext);
        if (LogUtil.judgeLogin(mContext)) {
            if (collectionUtil.isCollectionExist(mBeanList.get(i).getId())) {
                favoriteImageView.setImageResource(R.drawable.star);  //设置星星颜色
            }
        } else {
            favoriteImageView.setVisibility(View.GONE);  //隐藏星星
        }


        favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collection collection = new Collection(mBeanList.get(i).getId(), mBeanList.get(i).getTitle(), mBeanList.get(i).getImgBitmap(), mBeanList.get(i).getContent(), mBeanList.get(i).getHtml());

                if (collectionUtil.isCollectionExist(collection.getId())) {
                    // 文章已经被收藏
                    Toast.makeText(mContext, "已经被收藏了，无需重复收藏", Toast.LENGTH_SHORT).show();
                } else {
                    // 文章未被收藏
                    collectionUtil.saveCollection(collection);
                    Toast.makeText(mContext, "已收藏", Toast.LENGTH_SHORT).show();
                    favoriteImageView.setImageResource(R.drawable.star);
                }
            }
        });

        return v;
    }
}
