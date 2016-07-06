package com.wangpin.ice.sqlit_demo.layer;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import com.wangpin.ice.sqlit_demo.bean.EmployeeBean;
import com.wangpin.ice.sqlit_demo.entry.EmployeeEntry;

/**
 * Created by wangpi on 7/6/2016.
 */
public class Employeelayer  {

    public static void add(EmployeeBean employeeBean){
        new Add().execute(employeeBean);
    }

    public static class Add extends AsyncTask<EmployeeBean, Integer, Long> {
        @Override
        protected Long doInBackground(EmployeeBean... employeBean) {
            ContentValues values = new ContentValues();
            values.put(EmployeeEntry.COLUMN_NAME_NAME, employeBean[0].getName());
            values.put(EmployeeEntry.COLUMN_NAME_GENDER, employeBean[0].getGender());
            return Persistence.getInstance().insert(EmployeeEntry.TABLE_NAME, values);
        }
    }



}
