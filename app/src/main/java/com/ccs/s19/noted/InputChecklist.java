package com.ccs.s19.noted;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;

public class InputChecklist extends AppCompatActivity {

    private Button btnAddCl;
    private Button btnCancel;
    private Button btnAdd;
    private TableLayout main_table;
    private EditText editTextGroup;
    private CheckBox checkBoxPinned;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_checklist);

        btnAddCl = findViewById(R.id.btnAddCl);

    }


}
