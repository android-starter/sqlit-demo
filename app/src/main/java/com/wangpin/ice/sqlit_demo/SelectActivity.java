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

import com.wangpin.ice.sqlit_demo.constant.GenderEnum;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {

    private EditText idInput;
    private EditText nameInput;
    private Spinner genderSpinner;
    private Button retrieveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        idInput = (EditText)findViewById(R.id.idInputBox);
        nameInput = (EditText)findViewById(R.id.nameInputBox);
        genderSpinner = (Spinner)findViewById(R.id.genderSpinner);
        retrieveBtn = (Button)findViewById(R.id.retrieveBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);

        List<String> spinnerItems = new ArrayList<String>();
        spinnerItems.add(GenderEnum.MALE.toString());
        spinnerItems.add(GenderEnum.FEMALE.toString());
        ArrayAdapter arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        arr_adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(arr_adapter);

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Retrieve employees successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectActivity.this.back();
            }
        });

    }

    private void back(){
        Intent intent = new Intent();
        intent.setClass(SelectActivity.this, MainActivity.class);
        startActivity(intent);
        //Finsh current activity
        SelectActivity.this.finish();
    }

}
