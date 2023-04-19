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
    private Spinner sp_types;
    private EditText et_money,et_dates,et_time;
    private String types, dates, money,time,id;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        editor = sp.edit();
        id = sp.getString("id","");
        types = sp.getString("types","");
        dates = sp.getString("dates","");
        money = sp.getString("money","");
        time = sp.getString("time","");

        iv_back = findViewById(R.id.iv_back);
        bt_submit = findViewById(R.id.bt_submit);
        et_dates = findViewById(R.id.et_dates);
        sp_types = findViewById(R.id.sp_types);
        et_money = findViewById(R.id.et_money);
        et_time = findViewById(R.id.et_time);
        iv_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        final String[] string = getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, string);
        sp_types.setAdapter(adapter);
        for(int i = 0; i < string.length; i++){
            if(string[i].equals(types)){
                sp_types.setSelection(i,true);
                break;
            }
        }
        sp_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                types = string[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        et_dates.setText(dates);
        et_time.setText(time);
        et_money.setText(money);
    }

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
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_submit:
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
                db.update("intoTable",values,"id=?",new String[]{id});
                db.close();
                finish();
                Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}