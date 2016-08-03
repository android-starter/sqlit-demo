package com.wangpin.ice.sqlit_demo.layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.CursorAdapter;
import android.widget.ListView;

import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.wangpin.ice.sqlit_demo.bean.EmployeeBean;
import com.wangpin.ice.sqlit_demo.entry.EmployeeEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpi on 7/6/2016.
 */
public class Employeelayer  {

    public static void add(EmployeeBean employeeBean){
        new Add().execute(employeeBean);
    }

    public static void retrieve(Context context,ListView listView, int layout, int[] to, QueryArg queryArg){
        queryArg.setTableName(EmployeeEntry.TABLE_NAME);
        queryArg.setColumns(null);
        new Retrieve(context, listView, layout, to).execute(queryArg);
    }

    public static void update(EmployeeBean employeeBean){
        new Update().execute(employeeBean);
    }



    private static class Add extends AsyncTask<EmployeeBean, Integer, Long> {
        @Override
        protected Long doInBackground(EmployeeBean... employeBeans) {
            long insertedRows = 0;
            for(EmployeeBean eb : employeBeans) {
                ContentValues values = new ContentValues();
                values.put(EmployeeEntry.COLUMN_NAME_NAME, eb.getName());
                values.put(EmployeeEntry.COLUMN_NAME_GENDER, eb.getGender());
                insertedRows += Persistence.getInstance().insert(EmployeeEntry.TABLE_NAME, values);
            }
            return insertedRows;
        }
    }

    private static class Update extends AsyncTask<EmployeeBean, Integer, Long> {

        @Override
        protected Long doInBackground(EmployeeBean... employeBeans) {
            long effectRows = 0;
            for(EmployeeBean eb : employeBeans){
                QueryArg arg = new QueryArg();
                arg.setTableName(EmployeeEntry.TABLE_NAME);
                arg.setSelection("_id = ?");
                arg.setSelectionArgs(new String[]{eb.getId() + ""});
                ContentValues updateVal = new ContentValues();
                updateVal.put(EmployeeEntry.COLUMN_NAME_NAME, eb.getName());
                updateVal.put(EmployeeEntry.COLUMN_NAME_GENDER, eb.getGender());
                arg.setUpdateVal(updateVal);
                effectRows += Persistence.getInstance().update(arg);
            }
            return effectRows;
        }

    }


    private static class Retrieve extends AsyncTask<QueryArg, Integer, Cursor> {
        private Context ctx;
        private ListView listView;
        private int layout;
        private String[] from;
        private int[] to;
        public Retrieve(Context ctx, ListView listView, int layout, int[] to){
            this.ctx = ctx;
            this.listView = listView;
            this.layout = layout;
            this.to = to;
        }

        @Override
        protected void onPreExecute() {
            Toast toast = Toast.makeText(ctx, "Retrieving employees ...",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        @Override
        protected Cursor doInBackground(QueryArg... queryArgs){
            return Persistence.getInstance().retrieve(queryArgs[0]);
        }

        @Override
        protected void onPostExecute(Cursor cursor){
            int count = cursor.getColumnCount();
            List<String> names = new ArrayList<String>();
            for(int i = 0; i < count; i++){
                names.add(cursor.getColumnName(i));
            }
            from = names.toArray(new String[]{});
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(ctx, layout, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            listView.setAdapter(adapter);
        }


    }


}
