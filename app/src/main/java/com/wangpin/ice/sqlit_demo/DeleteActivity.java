package com.wangpin.ice.sqlit_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wangpin.ice.sqlit_demo.bean.EmployeeBean;
import com.wangpin.ice.sqlit_demo.layer.Employeelayer;
import com.wangpin.ice.sqlit_demo.layer.QueryArg;

/**
 * Created by wangpi on 7/27/2016.
 */
public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final int[] to = {R.id.empId, R.id.empName, R.id.empGender};
        final ListView itemsView = (ListView)findViewById(R.id.updatable_items);
        Employeelayer.retrieve(getBaseContext(),itemsView, R.layout.retrieve_row, to, new QueryArg());
        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EmployeeBean employeeBean = new EmployeeBean();
                int empId = Integer.parseInt(((TextView)view.findViewById(R.id.empId)).getText().toString());
                employeeBean.setId(empId);
                Employeelayer.delete(employeeBean);
                Employeelayer.retrieve(getBaseContext(),itemsView, R.layout.retrieve_row, to, new QueryArg());
            }
        });


    }
}

