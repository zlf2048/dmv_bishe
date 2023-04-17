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
    private TextView tv_money,tv_types,tv_dates,tv_time;
    private List<Map<String, String>> list;
    private MyAdapter adapter;
    private SharedPreferences.Editor editor;
    private ListView lv;
    private MySqlite mySqlite;
    private SQLiteDatabase db;
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

        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<>();
        getData();
        adapter = new MyAdapter(
                this,
                list,
                R.layout.item_into,
                new String[]{"types","money","dates","time"},
                new int[]{R.id.tv_types,R.id.tv_money,R.id.tv_dates,R.id.tv_time}
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
                new String[]{"types","money","dates","time"},
                new int[]{R.id.tv_types,R.id.tv_money,R.id.tv_dates,R.id.tv_time}
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
            tv_types = v.findViewById(R.id.tv_types);
            tv_money = v.findViewById(R.id.tv_money);
            tv_dates = v.findViewById(R.id.tv_dates);
            tv_time = v.findViewById(R.id.tv_time);

            tv_types.setText(list.get(position).get("types"));
            tv_money.setText("+"+list.get(position).get("money"));
            tv_dates.setText(list.get(position).get("dates"));
            tv_time.setText(list.get(position).get("time"));
            return v;
        }
    }

    public List<Map<String, String>> getData(){
        list.clear();
        MySqlite mySQLite = new MySqlite(this, 1);
        SQLiteDatabase database = mySQLite.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from intoTable order by dates desc,time desc", null);
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
