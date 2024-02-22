package com.example.laba30.utils;

import static com.example.laba30.data.Constants.APP_SPREFERENCES_KEY_FILE;
import static com.example.laba30.data.Constants.APP_SPREFERENCES_KEY_PERSONAL_DATA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba30.data.Date;
import com.example.laba30.data.PersonalDataFormat;
import com.google.gson.Gson;

import java.util.Calendar;

public class Utils {
    public static void moveToActivity(AppCompatActivity currActivity, Intent intent) {
        currActivity.startActivity(intent);
        currActivity.finish();
    }

    public static Date getDateThroughOffset(int pos) {
        Calendar mCalendar = Calendar.getInstance();
        if (pos != 0) {
            mCalendar.add(Calendar.DATE, pos);
        }
        return new Date(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH)+1, mCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public static void setMinDate(DatePicker datePicker, int minPos) {
        Calendar minCalendar = Calendar.getInstance();
        Date minDate = getDateThroughOffset(minPos);
        minCalendar.set(minDate.year, minDate.month-1, minDate.day);
        datePicker.setMinDate(minCalendar.getTimeInMillis());
    }

    public static void setMaxDate(DatePicker datePicker, int maxPos) {
        Calendar maxCalendar = Calendar.getInstance();
        Date maxDate = getDateThroughOffset(maxPos);
        maxCalendar.set(maxDate.year, maxDate.month-1, maxDate.day);
        datePicker.setMaxDate(maxCalendar.getTimeInMillis());
    }

    public static int getCalendarPos(Date date, int minPos, int maxPos) {
        for (int i = minPos; i <= maxPos; i++) {
            Date comparisonDate = getDateThroughOffset(i);
            if (comparisonDate.year == date.year && comparisonDate.month == date.month && comparisonDate.day == date.day) {
                return i;
            }
        }
        throw (new RuntimeException("Utils: Given date wasn't found in the supplied range."));
    }

    public static String getPersonalDataKey(String initials) {
        return APP_SPREFERENCES_KEY_PERSONAL_DATA.replace("%", initials);
    }

    public static PersonalDataFormat getPersonalDataByKey(Context context, String pKey) {
        SharedPreferences sPreferences = context.getSharedPreferences(APP_SPREFERENCES_KEY_FILE, Context.MODE_PRIVATE);
        String pData = sPreferences.getString(pKey, "");
        return new Gson().fromJson(pData, PersonalDataFormat.class);
    }

    public static void savePersonalDataByKey(Context context, String pKey, String pData) {
        SharedPreferences sPreferences = context.getSharedPreferences(APP_SPREFERENCES_KEY_FILE, Context.MODE_PRIVATE);
        sPreferences.edit().putString(pKey, pData).apply();
    }
}
