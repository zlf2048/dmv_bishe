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

//这段代码定义了一个名为 MyAdapter 的类，它继承自 BaseAdapter 类，用于为 ListView 或 GridView 等控件提供数据。
//
//在 MyAdapter 类的构造方法中，传入了一个 Context 对象和一个 ItemBean 对象的列表。Context 对象用于获取 LayoutInflater 对象，ItemBean 对象的列表用于存储要显示的数据。
//
//在 getCount 方法中，返回了 ItemBean 对象列表的大小，表示有多少个数据项。
//
//在 getItem 方法中，返回了 ItemBean 对象列表中指定位置的对象。
//
//在 getItemId 方法中，返回了数据项的位置。
//
//在 getView 方法中，创建了一个视图控件，并为其填充数据。首先通过 LayoutInflater 对象的 inflate 方法创建了一个视图控件，并设置其布局文件为 R.layout.list_item_layout。然后通过 findViewById 方法获取了布局文件中的各个控件，包括图片、标题、内容和收藏图标等。
//
//接下来，从 ItemBean 对象列表中获取了指定位置的对象，并为各个控件填充数据。最后为收藏图标设置了一个点击监听器，在点击时打印一条信息
