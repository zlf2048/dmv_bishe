package com.dmn.healthassistant.ui.sport.FitnessAchievement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dmn.healthassistant.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitnessAchievementActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;
    private TextView tv_add;

    private SharedPreferences sp;
    private TextView tv_dates,tv_time;
    private List<Map<String, String>> list;
    private MyAdapter adapter;
    private SharedPreferences.Editor editor;
    private ListView lv;
    private MySqlite mySqlite;
    private SQLiteDatabase db;
    private String user_id;//1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_achievement);

        iv_back =findViewById(R.id.iv_back);
        tv_add = findViewById(R.id.tv_add);
        tv_add.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        sp = getSharedPreferences("user",MODE_PRIVATE);
        editor = sp.edit();
        this.user_id = sp.getString("account","");//2

        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<>();
        getData();
        adapter = new MyAdapter(
                this,
                list,
                R.layout.item_into,
                new String[]{"dates","time"},
                new int[]{R.id.tv_dates,R.id.tv_time}
        );
        lv.setAdapter(adapter);


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FitnessAchievementActivity.this);
                builder.setMessage("确定要删除吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteRecord(list.get(i).get("id"));
                        Toast.makeText(FitnessAchievementActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        list.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.create().show();

                return true;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putString("types",list.get(i).get("types"));
                editor.putString("dates",list.get(i).get("dates"));
                editor.putString("time",list.get(i).get("time"));
                editor.putString("id",list.get(i).get("id"));
                editor.putString("money",list.get(i).get("money"));
                editor.commit();
                Intent intent = new Intent(FitnessAchievementActivity.this,AlterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                Intent intent1 = new Intent(FitnessAchievementActivity.this,AddActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public void deleteRecord(String i){
        mySqlite=new MySqlite(this,1);
        db=mySqlite.getWritableDatabase();
        db.delete("intoTable","id=?",new String[]{i});
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        adapter = new MyAdapter(
                this,
                list,
                R.layout.item_into,
                new String[]{"dates","time"},
                new int[]{R.id.tv_dates,R.id.tv_time}
        );
        lv.setAdapter(adapter);
    }

    private class MyAdapter extends SimpleAdapter {
        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource,
                         String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            tv_dates = v.findViewById(R.id.tv_dates);
            tv_time = v.findViewById(R.id.tv_time);


            tv_dates.setText(list.get(position).get("dates"));
            tv_time.setText(list.get(position).get("time"));
            return v;
        }
    }

    //从数据库中获取数据
    public List<Map<String, String>> getData(){
        list.clear();
        MySqlite mySQLite = new MySqlite(this, 1);
        SQLiteDatabase database = mySQLite.getReadableDatabase();
        String sql = "SELECT * FROM intoTable WHERE user_id = ? ORDER BY dates DESC";//3
        Cursor cursor = database.rawQuery(sql, new String[]{user_id});//4
//        Cursor cursor = database.rawQuery("select * from intoTable order by dates desc,time desc", null);
        System.out.println(cursor.getCount());
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String money = cursor.getString(cursor.getColumnIndex("money"));
            String dates = cursor.getString(cursor.getColumnIndex("dates"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String types = cursor.getString(cursor.getColumnIndex("types"));
            Map<String, String> map = new HashMap<>();
            map.put("id",id);
            map.put("money",money);
            map.put("dates",dates);
            map.put("time",time);
            map.put("types",types);
            list.add(map);
        }
        return list;
    }
}

//这段代码是一个 Android 应用程序中的 FitnessAchievementActivity 类。它继承自 AppCompatActivity 类并实现了 View.OnClickListener 接口，用于显示健身成就列表。
//
//在 onCreate 方法中，首先调用了 setContentView 方法设置了该活动的布局文件为 R.layout.activity_fitness_achievement。然后通过 getSharedPreferences 方法获取了 SharedPreferences 对象，并获取了 SharedPreferences.Editor 对象。接着通过 findViewById 方法获取了布局文件中的各个控件，包括返回图标、添加文本和 ListView 等。最后为返回图标和添加文本设置了点击监听器，并调用 getData 方法获取数据。然后创建了一个 MyAdapter 对象，并将其设置为 ListView 的适配器。接着为 ListView 设置了一个长按监听器和一个点击监听器。
//
//在 onClick 方法中，根据被点击控件的 ID 进行不同的处理。当点击返回图标时，结束当前活动；当点击添加文本时，跳转到 AddActivity。
//
//在 deleteRecord 方法中，使用 SQLite 数据库删除 intoTable 表中指定 id 的数据。
//
//在 onResume 方法中，调用 getData 方法获取数据。然后创建一个新的 MyAdapter 对象，并将其设置为 ListView 的适配器。
//
//在 MyAdapter 类中，重写了 getView 方法，用于为每个数据项创建视图并填充数据。
//
//在 getData 方法中，使用 SQLite 数据库查询 intoTable 表中的数据，并将查询结果存储在一个 Map 列表中
