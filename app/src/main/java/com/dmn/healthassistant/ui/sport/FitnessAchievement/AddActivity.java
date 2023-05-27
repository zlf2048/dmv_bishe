package com.dmn.healthassistant.ui.sport.FitnessAchievement;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dmn.healthassistant.R;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private Button bt_submit;
    private EditText et_money,et_dates,et_time;
    private String types, dates, money,time;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String account;//1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        editor = sp.edit();
        this.account = sp.getString("account","");//2
        iv_back = findViewById(R.id.iv_back);
        bt_submit = findViewById(R.id.bt_submit);
        et_dates = findViewById(R.id.et_dates);
        et_money = findViewById(R.id.et_money);
        et_time = findViewById(R.id.et_time);
        iv_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        final String[] string = getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, string);

    }

    //从数据库中获取数据
    public String[] getData(){
        int pos = 0;
        MySqlite mySQLite = new MySqlite(AddActivity.this, 1);
        SQLiteDatabase database = mySQLite.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from intoTypes", null);
        System.out.println(cursor.getCount());
        String[] strings = new String[cursor.getCount()];
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            strings[pos] = name;
            pos++;
        }
        return strings;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:  //当点击返回按钮（R.id.iv_back）时，调用finish()结束当前活动
                finish();
                break;
            case R.id.bt_submit:   //当点击提交按钮（R.id.bt_submit）时，获取用户输入的数据并将其插入到数据库中
                money = et_money.getText().toString().trim();
                dates = et_dates.getText().toString().trim();
                time = et_time.getText().toString().trim();

                if (money.equals("") || dates.equals("") || time.equals("")){
                    Toast.makeText(this, "信息不完整！", Toast.LENGTH_SHORT).show();
                    return;
                }
                MySqlite mySQLite = new MySqlite(this, 1);
                SQLiteDatabase db= mySQLite.getWritableDatabase();
                //使用ContentValues添加数据
                ContentValues values=new ContentValues();
                values.put("types",types);
                values.put("dates",dates);
                values.put("money",money);
                values.put("time",time);
                values.put("user_id",account);//3
                db.insert("intoTable", null, values);
                db.close();
                Toast.makeText(this,"添加成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

//这段代码是一个 Android 应用程序中的 AddActivity 类。它继承自 AppCompatActivity 类并实现了 View.OnClickListener 接口，用于实现添加数据的功能。
//
//在 onCreate 方法中，首先调用了 setContentView 方法设置了该活动的布局文件为 R.layout.activity_add。然后通过 getSharedPreferences 方法获取了 SharedPreferences 对象，并获取了 SharedPreferences.Editor 对象。接着通过 findViewById 方法获取了布局文件中的各个控件，包括返回图标、提交按钮、日期输入框、金额输入框和时间输入框等。最后为返回图标和提交按钮设置了点击监听器。
//
//在 getData 方法中，使用 SQLite 数据库查询 intoTypes 表中的数据，并将查询结果存储在一个字符串数组中。
//
//在 onClick 方法中，根据被点击控件的 ID 进行不同的处理。当点击返回图标时，结束当前活动；当点击提交按钮时，获取日期输入框、金额输入框和时间输入框中的文本，并检查是否为空。如果有任意一个为空，则弹出一个 Toast 提示“信息不完整！”。否则，使用 SQLite 数据库向 intoTable 表中插入一条数据，并弹出一个 Toast 提示“添加成功”

