package com.dmn.healthassistant.ui.individuality.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.model.Healthinfo;
import com.dmn.healthassistant.util.LoginInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.minapp.android.sdk.database.Record;
import com.minapp.android.sdk.database.Table;
import com.minapp.android.sdk.database.query.Query;
import com.minapp.android.sdk.database.query.Where;
import com.minapp.android.sdk.util.BaseCallback;
import com.minapp.android.sdk.util.PagedList;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class HealthyInformationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextInputEditText name, birth_date,height, weight, blood_pressure, blood_glucose, BMI;
    private MaterialButton saveMaterialButton, newMaterialButton;

    @Override
    //日期选择完事件
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("%d-%d-%d",year,month+1,dayOfMonth);
        birth_date.setText(desc);//设置生日
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_information);

        name = findViewById(R.id.name);
        birth_date = findViewById(R.id.birth_date);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        blood_pressure = findViewById(R.id.blood_pressure);
        blood_glucose = findViewById(R.id.blood_glucose);
        BMI = findViewById(R.id.BMI);
        saveMaterialButton = findViewById(R.id.saveMaterialButton);
        newMaterialButton = findViewById(R.id.newMaterialButton);

        //获取登录用户的ID(ID不在页面中显示）
        LoginInfo loginInfo = new LoginInfo(HealthyInformationActivity.this);
        String id = loginInfo.getLoginInfo().getId();
        //定义每条健康信息数据的id
        String record_id[] = {null};

        //获取云端表中已有数据
        Table health_information = new Table("health_information");
        Where where = new Where();
        where.equalTo("user_id", id);
        Query query = new Query();
        query.put(where);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PagedList<Record> records = health_information.query(query);
                    System.out.println(records);
                    List<Record> list = records.getObjects();
                    Record record = list.get(0);
                    Healthinfo healthinfo = new Healthinfo(record);
                    System.out.println(healthinfo);

                    String nameText = healthinfo.getName();
                    String recordText = healthinfo.getId();
                    String birth_dateText = healthinfo.getBirth_date();
                    String heightText = healthinfo.getHeight();
                    String weightText = healthinfo.getWeight();
                    String blood_pressureText = healthinfo.getBloodPressure();
                    String blood_glucoseText = healthinfo.getBloodGlucose();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            name.setText(nameText);
                            record_id[0] = recordText;
                            birth_date.setText(birth_dateText);
                            height.setText(heightText);
                            weight.setText(weightText);
                            blood_pressure.setText(blood_pressureText);
                            blood_glucose.setText(blood_glucoseText);
                            Float wValue = Float.parseFloat(weightText);
                            Float hValue = Float.parseFloat(heightText);
                            BMI.setText(String.format("%.2f", wValue / Math.pow(hValue / 100, 2)));
                        }
                    });
                } catch (Exception e) {
                    System.out.println("出错了");
                    e.printStackTrace();
                }
            }
        }).start();

        //检测用户是否已有健康信息，根据结果设置不同的按钮
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    health_information.countInBackground(query, new BaseCallback<Integer>() {
                        @Override
                        public void onSuccess(Integer integer) {
                            if(integer == 0) {
                                saveMaterialButton.setVisibility(View.INVISIBLE);
                            } else {
                                newMaterialButton.setVisibility(View.INVISIBLE);
                            }
                        }
                        @Override
                        public void onFailure(Throwable e) {
                            System.out.println(e);
                        }
                    });
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();

        birth_date.setOnClickListener(v -> {//生日点击弹出日期选择框
            Calendar calendar = Calendar.getInstance();//获取Calendar实例
            //创建日期选择器
            DatePickerDialog dialog = new DatePickerDialog(this,this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MARCH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();//窗口弹出
        });

        //获取用户输入的信息并将其保存到云端表中
        newMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("New了");
                String nameInputText = name.getText().toString();
                String birth_dateInputText = birth_date.getText().toString();
                Float heightInputText = Float.parseFloat(height.getText().toString());
                Float weightInputText = Float.parseFloat(weight.getText().toString());
                Integer blood_pressureInputText = Integer.parseInt(blood_pressure.getText().toString());
                Float blood_glucoseInputText = Float.parseFloat(blood_glucose.getText().toString());

                Record record = health_information.createRecord();
                record.put("name", nameInputText);
                record.put("birth_date", birth_dateInputText);
                record.put("height", heightInputText);
                record.put("weight", weightInputText);
                record.put("blood_pressure", blood_pressureInputText);
                record.put("blood_glucose", blood_glucoseInputText);
                record.put("user_id", id);

                final Handler handler = new Handler(Looper.getMainLooper());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            record.save();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            finish();
                        } catch (Exception e) {
                            System.out.println(e);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "保存失败，请重试", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        saveMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("save了");
                String nameInputText = name.getText().toString();
                String birth_dateInputText = birth_date.getText().toString();
                Float heightInputText = Float.parseFloat(height.getText().toString());
                Float weightInputText = Float.parseFloat(weight.getText().toString());
                Integer blood_pressureInputText = Integer.parseInt(blood_pressure.getText().toString());
                Float blood_glucoseInputText = Float.parseFloat(blood_glucose.getText().toString());

                final Handler handler = new Handler(Looper.getMainLooper());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Record record = health_information.fetchRecord(record_id[0]);
                            record.put("name", nameInputText);
                            record.put("birth_date", birth_dateInputText);
                            record.put("height", heightInputText);
                            record.put("weight", weightInputText);
                            record.put("blood_pressure", blood_pressureInputText);
                            record.put("blood_glucose", blood_glucoseInputText);
                            record.save();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            finish();
                        } catch (Exception e) {
                            System.out.println(e);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "保存失败，请重试", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}