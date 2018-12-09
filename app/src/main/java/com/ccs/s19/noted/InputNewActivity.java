package com.ccs.s19.noted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputNewActivity extends AppCompatActivity {

    private EditText editTextData;
    private Button btnCreate;
    private Button btnCancel;
    private NoteAdapter adapter = new NoteAdapter();

    private myDbAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_new);

        editTextData = findViewById(R.id.editTextData);
        btnCreate = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnCancel);

        db = new myDbAdapter(getApplicationContext());

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String addedData = "";
                addedData = editTextData.getText().toString();

                if (addedData.equalsIgnoreCase("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Note is empty!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    long id = db.insertData(addedData);
                    if (id > 0) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Note added!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "ERROR! not addded!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    finish();
                }


            }
        });
    }
}
