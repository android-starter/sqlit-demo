package com.wangpin.ice.sqlit_demo.layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wangpin.ice.sqlit_demo.bean.EmployeeBean;
import com.wangpin.ice.sqlit_demo.entry.EmployeeEntry;

/**
 * Created by wangpi on 7/6/2016.
 */
public class Persistence extends SQLiteOpenHelper {

    private static Persistence p;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "sqlit-demo.db";

    private Persistence(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
    }

    //This method should be called in the beginning
    public static void init(Context context){
        if( p == null){
            p = new Persistence(context);
        }
    }

    public static Persistence getInstance(){
        return p;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //In here , create database objects like tables, views, etc
        System.out.println("Creating database objects start");
        db.execSQL(EmployeeEntry.CREATE_TABLE);
        System.out.println("Creating database objects end");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void executeSQL(String sql){
        getWritableDatabase().execSQL(sql);
    }


    public long insert(String tableName, ContentValues values){
        return getWritableDatabase().insert(tableName, null, values);
    }

    public Cursor retrieve(QueryArg args ){
        return getWritableDatabase().query(args.getTableName(),args.getColumns(),args.getSelection(),args.getSelectionArgs(),args.getGroupBy(),args.getHaving(),args.getOrderBy());
    }

}
