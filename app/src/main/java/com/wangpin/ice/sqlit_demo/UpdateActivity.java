package com.wangpin.ice.sqlit_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wangpin.ice.sqlit_demo.bean.EmployeeBean;
import com.wangpin.ice.sqlit_demo.constant.GenderEnum;
import com.wangpin.ice.sqlit_demo.layer.Employeelayer;
import com.wangpin.ice.sqlit_demo.layer.QueryArg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpi on 7/27/2016.
 */
public class UpdateActivity extends AppCompatActivity {

    private EditText nameInput;
    private Spinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final EmployeeBean employee = new EmployeeBean();

        nameInput = (EditText)findViewById(R.id.nameInputBox);
        genderSpinner = (Spinner)findViewById(R.id.genderSpinner);
        List<String> spinnerItems = new ArrayList<String>();
        spinnerItems.add(GenderEnum.MALE.toString());
        spinnerItems.add(GenderEnum.FEMALE.toString());
        ArrayAdapter arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        arr_adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(arr_adapter);

        final ListView updatableItems = (ListView)this.findViewById(R.id.updatable_items);
        final LinearLayout updateForm = (LinearLayout)this.findViewById(R.id.updateForm);

        final int[] to = {R.id.empId, R.id.empName, R.id.empGender};
        final ListView itemsView = (ListView)findViewById(R.id.updatable_items);
        Employeelayer.retrieve(getBaseContext(),itemsView, R.layout.retrieve_row, to, new QueryArg());
        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updatableItems.setVisibility(ListView.GONE);
                updateForm.setVisibility(LinearLayout.VISIBLE);
                TextView idBox = (TextView)view.findViewById(R.id.empId);
                TextView nameBox = (TextView)view.findViewById(R.id.empName);
                TextView genderBox = (TextView)view.findViewById(R.id.empGender);
                employee.setId(new Integer(idBox.getText().toString()));
                employee.setName(nameBox.getText().toString());
                employee.setGender(genderBox.getText().toString());
                nameInput.setText(nameBox.getText());
                if(genderBox.getText().equals(GenderEnum.MALE.toString())){
                    genderSpinner.setSelection(0);
                }else{
                    genderSpinner.setSelection(1);
                }
            }
        });


        Button submitBtn = (Button)findViewById(R.id.submitBtn);
        Button cancelBtn = (Button)findViewById(R.id.cancelBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatableItems.setVisibility(ListView.VISIBLE);
                updateForm.setVisibility(LinearLayout.GONE);
                String name = nameInput.getText().toString();
                String gender = genderSpinner.getSelectedItem().toString();
                employee.setName(name);
                employee.setGender(gender);
                System.out.println("Update employee profile to \n name:" + name + "\n gender:" + gender);
                Employeelayer.update(employee);
                Employeelayer.retrieve(getBaseContext(),itemsView, R.layout.retrieve_row, to, new QueryArg());
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatableItems.setVisibility(ListView.VISIBLE);
                updateForm.setVisibility(LinearLayout.GONE);
            }
        });
    }
}

