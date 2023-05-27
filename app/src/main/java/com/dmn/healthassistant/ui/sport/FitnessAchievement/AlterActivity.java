package com.dmn.healthassistant.ui.sport.FitnessAchievement;

import androidx.appcompat.app.AppCompatActivity;

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

import com.dmn.healthassistant.R;

public class AlterActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;
    private Button bt_submit;
    private EditText et_money,et_dates,et_time;
    private String types, dates, money,time,id;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String user_id;//1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        editor = sp.edit();
        this.user_id = sp.getString("account","");//2
        id = sp.getString("id","");
        types = sp.getString("types","");
        dates = sp.getString("dates","");
        money = sp.getString("money","");
        time = sp.getString("time","");

        iv_back = findViewById(R.id.iv_back);
        bt_submit = findViewById(R.id.bt_submit);
        et_dates = findViewById(R.id.et_dates);
        et_money = findViewById(R.id.et_money);
        et_time = findViewById(R.id.et_time);
        iv_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        final String[] string = getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, string);

        et_dates.setText(dates);
        et_time.setText(time);
        et_money.setText(money);
    }

    //从数据库中获取数据
    public String[] getData(){
        int pos = 0;
        MySqlite mySQLite = new MySqlite(AlterActivity.this, 1);
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
            case R.id.iv_back:  //当点击返回按钮（R.id.iv_back）时，调用finish()方法结束当前活动
                finish();
                break;
            case R.id.bt_submit:  //当点击提交按钮（R.id.bt_submit）时，它获取用户输入的数据并更新数据库中相应的记录。
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

                String whereClause = "id = ? AND user_id = ?";//3
                String[] whereArgs = new String[]{id, user_id };//4
                db.update("intoTable", values, whereClause, whereArgs);//5
//                db.update("intoTable",values,"id=?",new String[]{id});
                db.close();
                finish();
                Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

//这段代码是一个 Android 应用程序中的 AlterActivity 类。它继承自 AppCompatActivity 类并实现了 View.OnClickListener 接口，用于实现修改数据的功能。
//
//在 onCreate 方法中，首先调用了 setContentView 方法设置了该活动的布局文件为 R.layout.activity_alter。然后通过 getSharedPreferences 方法获取了 SharedPreferences 对象，并获取了 SharedPreferences.Editor 对象。接着从 SharedPreferences 对象中获取了 id、types、dates、money 和 time 五个值。然后通过 findViewById 方法获取了布局文件中的各个控件，包括返回图标、提交按钮、日期输入框、金额输入框和时间输入框等。最后为返回图标和提交按钮设置了点击监听器，并为日期输入框、金额输入框和时间输入框设置了文本。
//
//在 getData 方法中，使用 SQLite 数据库查询 intoTypes 表中的数据，并将查询结果存储在一个字符串数组中。
//
//在 onClick 方法中，根据被点击控件的 ID 进行不同的处理。当点击返回图标时，结束当前活动；当点击提交按钮时，获取日期输入框、金额输入框和时间输入框中的文本，并检查是否为空。如果有任意一个为空，则弹出一个 Toast 提示“信息不完整！”。否则，使用 SQLite 数据库更新 intoTable 表中指定 id 的数据，并弹出一个 Toast 提示“修改成功”。
