package com.wangpin.ice.sqlit_demo.entry;

import android.provider.BaseColumns;

/**
 * Created by wangpi on 7/6/2016.
 */
public abstract class EmployeeEntry implements BaseColumns {
    public static final String TABLE_NAME = "t_employee";
    public static final String COLUMN_NAME_NAME  = "name";
    public static final String COLUMN_NAME_GENDER = "gender";

    public static final String CREATE_TABLE =  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_NAME_NAME + " TEXT ," +
            COLUMN_NAME_GENDER + " TEXT " +
            ")";

}
