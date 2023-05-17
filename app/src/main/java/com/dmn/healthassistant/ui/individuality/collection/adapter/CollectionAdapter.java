package com.dmn.healthassistant.ui.individuality.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.model.Collection;
import com.dmn.healthassistant.ui.individuality.collection.bean.CollectionItemBean;
import com.dmn.healthassistant.ui.information.bean.ItemBean;
import com.dmn.healthassistant.util.CollectionUtil;

import java.util.List;

public class CollectionAdapter extends BaseAdapter {

    private List<CollectionItemBean> mBeanList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CollectionAdapter(Context context,List<CollectionItemBean> beanList){
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
        View v = mLayoutInflater.inflate(R.layout.list_item_collection,viewGroup, false);
        ImageView imageView = v.findViewById(R.id.iv_img);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        TextView tvContent = v.findViewById(R.id.tv_content);
        Button favoriteButton = v.findViewById(R.id.btn_nofavorite);

        //为控件填充数据
        CollectionItemBean itemBean = mBeanList.get(i);
        imageView.setImageBitmap(itemBean.getImgBitmap());
        tvTitle.setText(itemBean.getTitle());
        tvContent.setText(itemBean.getContent());
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionUtil collectionUtil = new CollectionUtil(mContext);
                collectionUtil.deleteCollection(mBeanList.get(i).getId());
                Toast.makeText(mContext, "已取消收藏", Toast.LENGTH_SHORT).show();
                mBeanList.remove(itemBean);
                notifyDataSetChanged();
            }
        });

        return v;
    }
}