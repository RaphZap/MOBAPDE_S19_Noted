package com.ccs.s19.noted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class InputNewActivity extends AppCompatActivity {

    private EditText editTextData;
    private Button btnCreate;
    private Button btnCancel;
    private CheckBox checkBoxPinned;
    private EditText editTextGroup;
    private EditText inputtedHour;
    private EditText inputtedMinute;
    private EditText inputtedSecond;

//    private NoteAdapter adapter = new NoteAdapter(this.getApplicationContext());
    private myDbAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_new);

        editTextData = findViewById(R.id.editTextData);
        btnCreate = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnCancel);
        checkBoxPinned = findViewById(R.id.checkBoxPinned);
        editTextGroup = findViewById(R.id.editTextGroup);
        inputtedHour = findViewById(R.id.inputHour);
        inputtedMinute = findViewById(R.id.inputMinute);
        inputtedSecond = findViewById(R.id.inputSeconds);


        db = new myDbAdapter(getApplicationContext());

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String addedData = editTextData.getText().toString();
                String group = editTextGroup.getText().toString();
                Boolean isPin = checkBoxPinned.isChecked();
                String hour = inputtedHour.getText().toString();
                String minute = inputtedMinute.getText().toString();
                String second = inputtedSecond.getText().toString();

                if(hour.isEmpty()){
                    hour = "0";
                }
                if(minute.isEmpty()){
                    minute = "0";
                }
                if(second.isEmpty()){
                    second = "0";
                }

                if (addedData.equalsIgnoreCase("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Note is empty! ",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // add data into the DB. id returns -1 if there's a problem inserting
                    System.out.println(hour + "" + minute + "" + second + " ```");
                    long id = db.insertData(addedData,0, group, isPin, hour, minute, second);
                    if (id > 0) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Note added!",
                                Toast.LENGTH_SHORT);
                        toast.show();

                        // Close activity
                        finish();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "ERROR! not addded!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cancelled!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
