package com.wangpin.ice.sqlit_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.wangpin.ice.sqlit_demo.bean.EmployeeBean;
import com.wangpin.ice.sqlit_demo.constant.GenderEnum;
import com.wangpin.ice.sqlit_demo.layer.Employeelayer;
import com.wangpin.ice.sqlit_demo.layer.QueryArg;
import com.wangpin.ice.sqlit_demo.layer.SelectionHelper;

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
        spinnerItems.add("");
        spinnerItems.add(GenderEnum.MALE.toString());
        spinnerItems.add(GenderEnum.FEMALE.toString());
        ArrayAdapter arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        arr_adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(arr_adapter);

        final int[] to = {R.id.empId, R.id.empName, R.id.empGender};

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = idInput.getText().toString();
                String name = nameInput.getText().toString();
                String gender = genderSpinner.getSelectedItem().toString();

                SelectionHelper<String> selections = new SelectionHelper();
                List<String> selectionArgs = new ArrayList<String>();
                if(id!=null && id.length()>0){
                    selections.add("id = ?");
                    selectionArgs.add(id);
                }
                if(name!=null && name.length()>0){
                    selections.add("name like ?");
                    name = "%" + name + "%";
                    selectionArgs.add(name);
                }
                if(gender!=null && gender.length()>0){
                    selections.add("gender = ?");
                    selectionArgs.add(gender);
                }
                QueryArg queryArg = new QueryArg();
                queryArg.setSelection(selections.join());
                queryArg.setSelectionArgs(selectionArgs.toArray(new String[]{}));
                Employeelayer.retrieve(getBaseContext(),(ListView)findViewById(R.id.empRows), R.layout.retrieve_row, to, queryArg);

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

    public void showRetrieveResults(EmployeeBean[] employeeBeans){
        System.out.println("retrieved employee total:"+employeeBeans.length);
    }

}
