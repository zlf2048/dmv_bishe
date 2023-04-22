package com.dmn.healthassistant.ui.individuality.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.dmn.healthassistant.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class HealthyInformationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextInputEditText name, birth_data;

    @Override
    //日期选择完事件
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("%d-%d-%d",year,month+1,dayOfMonth);
        birth_data.setText(desc);//设置生日
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_information);

        birth_data = findViewById(R.id.birth_data);

        birth_data.setOnClickListener(v -> {//生日点击弹出日期选择框
            Calendar calendar = Calendar.getInstance();//获取Calendar实例
            //创建日期选择器
            DatePickerDialog dialog = new DatePickerDialog(this,this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MARCH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();//窗口弹出
        });
    }
}