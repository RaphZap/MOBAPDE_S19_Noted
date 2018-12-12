package com.ccs.s19.noted;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    // UI elements
    private EditText editTextData;
    private Button btnEdit;
    private Button btnCancel;
    private Button btnDelete;
    private CheckBox checkBoxPinned;
    private EditText editTextGroup;
    private EditText inputtedHour;
    private EditText inputtedMinute;
    private EditText inputtedSecond;

    // Miscellaneous
    private myDbAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTextData = findViewById(R.id.editTextData);
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        checkBoxPinned = findViewById(R.id.checkBoxPinned);
        editTextGroup = findViewById(R.id.editTextGroup);
        inputtedHour = findViewById(R.id.inputHour);
        inputtedMinute = findViewById(R.id.inputMinute);
        inputtedSecond = findViewById(R.id.inputSeconds);
//        editTextColor.setVisibility(View.INVISIBLE);

        db = new myDbAdapter(getApplicationContext());

        int id = getIntent().getIntExtra("IDKEY", -1);
        String text = getIntent().getStringExtra("TEXTKEY");
        int imageId = getIntent().getIntExtra("IMAGEKEY", -1);
        String group = getIntent().getStringExtra("GROUPKEY");
        Boolean isPinned = getIntent().getBooleanExtra("PINKEY", false);
        String hour = getIntent().getStringExtra("HOUR");
        String minute = getIntent().getStringExtra("MINUTE");
        String second = getIntent().getStringExtra("SECOND");

        Cursor cursor = db.getDataByID(id);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
//                id = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                text = cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT));
                imageId = cursor.getInt(cursor.getColumnIndex(myDbHelper.IMAGEID));
                group = cursor.getString(cursor.getColumnIndex(myDbHelper.COLOR));
                if (cursor.getString(cursor.getColumnIndex(myDbHelper.ISPINNED)) != null &&
                        cursor.getString(cursor.getColumnIndex(myDbHelper.ISPINNED)).equalsIgnoreCase("1") )
                    isPinned = true; else isPinned = false;
                hour = cursor.getString(cursor.getColumnIndex(myDbHelper.HOUR));
                minute = cursor.getString(cursor.getColumnIndex(myDbHelper.MINUTE));
                second = cursor.getString(cursor.getColumnIndex(myDbHelper.SECOND));

                cursor.moveToNext();
            }
        }
        editTextData.setText(text);
        String item = id +" - " +text +" - " +imageId +" - "
                +group +" - " +isPinned+" - "+hour+" - " +minute+ " - " +second;
        Toast.makeText(this, item, Toast.LENGTH_LONG).show();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = getIntent().getIntExtra("IDKEY", -1);
                String addedData = "";
                addedData = editTextData.getText().toString();
                int imageid = -1;
                String group = editTextGroup.getText().toString();
                boolean pin = checkBoxPinned.isChecked();
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

                int c = db.updateText(id, addedData, imageid, group, pin, hour, minute, second);

                Toast.makeText(getApplicationContext(),"Editted! "+c, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(R.string.alert_delete_title);
                builder.setMessage(R.string.alert_delete_text);
                builder.setPositiveButton(R.string.alert_delete_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int uid = getIntent().getIntExtra("IDKEY", -1);
                        int c = db.deleteByID(uid);
                        Log.d("btnDelete NOTIFICATION","Rows affected: " +c);
                        Toast.makeText(getApplicationContext(),"Note Deleted! " +c, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.alert_delete_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("btnDelete NOTIFICATION","Bad Rating");
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
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
