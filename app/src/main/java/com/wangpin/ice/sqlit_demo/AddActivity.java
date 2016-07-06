package com.wangpin.ice.sqlit_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wangpin.ice.sqlit_demo.bean.EmployeeBean;
import com.wangpin.ice.sqlit_demo.constant.GenderEnum;
import com.wangpin.ice.sqlit_demo.layer.Employeelayer;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private Button submitBtn;
    private Button cancelBtn;
    private Spinner genderSpinner;
    private EditText nameInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameInput = (EditText)findViewById(R.id.nameInputBox);
        genderSpinner = (Spinner)findViewById(R.id.genderSpinner);
        submitBtn = (Button)findViewById(R.id.submitAddBtn);
        cancelBtn = (Button)findViewById(R.id.cancelAddBtn);

        List<String> spinnerItems = new ArrayList<String>();
        spinnerItems.add(GenderEnum.MALE.toString());
        spinnerItems.add(GenderEnum.FEMALE.toString());
        ArrayAdapter arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        arr_adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(arr_adapter);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeBean employee = new EmployeeBean(nameInput.getText().toString(), genderSpinner.getSelectedItem().toString());
                Employeelayer.add(employee);
                Toast.makeText(getApplicationContext(), "Employee data has been inserted successfully",
                        Toast.LENGTH_SHORT).show();

                AddActivity.this.back();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.this.back();
            }
        });
    }

    private void back(){
        Intent intent = new Intent();
        intent.setClass(AddActivity.this, MainActivity.class);
        startActivity(intent);
        //Finsh current activity
        AddActivity.this.finish();
    }

}
