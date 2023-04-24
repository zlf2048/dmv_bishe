package com.dmn.healthassistant.model;

import com.minapp.android.sdk.database.Record;

import java.util.Date;

public class Healthinfo extends Record {
    public static final String NAME = "name";
    public static final String BIRTH_DATE = "birth_date";
    public static final String ID = "id";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String BLOOD_PRESSURE = "blood_pressure";
    public static final String BLOOD_GLUCOSE = "blood_glucose";



    public static Healthinfo EMPTY_PLACEHOLDER = new Healthinfo();

    public Healthinfo(Record record) {
        super(record._getTable(), record._getJson());
    }

    public Healthinfo() {
        super();
    }

    public String getName() {
        return getString(NAME);
    }

    public void setName(String value) {
        put(NAME, value);
    }

    public String getBirth_date() {
        String date = getString(BIRTH_DATE);
        date = date.substring(0, 10);
        return date;
    }

    public String getId() {
        return getString(ID);
    }

    public String getHeight() {
        return getString(HEIGHT);
    }

    public String getWeight() {
        return getString(WEIGHT);
    }

    public String getBloodPressure() {
        return getString(BLOOD_PRESSURE);
    }

    public String getBloodGlucose() {
        return getString(BLOOD_GLUCOSE);
    }
}
