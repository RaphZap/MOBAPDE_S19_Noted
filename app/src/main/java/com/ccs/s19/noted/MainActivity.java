package com.ccs.s19.noted;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private NoteAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private Button addNote;
    private Button btnCamera;

    private myDbAdapter mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote = findViewById(R.id.addNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openNewNoteActivity();
            }
        });

        btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Your pic has been taken! JK",
                        Toast.LENGTH_SHORT).show();

                // TODO: Add Camera function here.

            }
        });

        mydb = new myDbAdapter(this);
        recyclerArea = findViewById(R.id.recycler_area);

        adapter = new NoteAdapter(this, recyclerArea);

        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);

        recyclerArea.setAdapter(adapter);

        // Load data from database and into the recycler view.
        loadData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast toast;
        switch (item.getItemId()) {
            case R.id.menu_newNote:
//                Log.d("MENU_DIALOG","NEW NOTE");
//                toast = Toast.makeText(getApplicationContext(),
//                        "Implement NEW NOTE!!!",
//                            Toast.LENGTH_SHORT);
//                toast.show();

                openNewNoteActivity();
                return true;
//            case R.id.menu_editNote:
//                Log.d("MENU_DIALOG","EDIT NOTE");
//                toast = Toast.makeText(getApplicationContext(),
//                        "Implement EDIT NOTE!!!",
//                        Toast.LENGTH_SHORT);
//                toast.show();
//                return true;
            case R.id.menu_deleteNote:
                Log.d("MENU_DIALOG","DELETE NOTE");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.alert_delete_title);
                builder.setMessage(R.string.alert_delete_text);
                builder.setPositiveButton(R.string.alert_delete_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int c = mydb.deleteAll();
                        Log.d("menu_deleteNote","Rows affected: " +c);
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
                return true;
            case R.id.menu_sort:
                Log.d("MENU_DIALOG","SORT NOTE");
                toast = Toast.makeText(getApplicationContext(),
                        "Implement SORT NOTE!!!",
                        Toast.LENGTH_SHORT);
                toast.show();
                return true;
            case R.id.menu_settings:
                Log.d("MENU_DIALOG","SETTINGS");

                String data = mydb.getData();
                toast = Toast.makeText(getApplicationContext(),
                        data,
                        Toast.LENGTH_LONG);
                toast.show();
                Log.d("MENU_DIALOG",data);

//                toast = Toast.makeText(getApplicationContext(),
//                        "Implement SETTINGS!!!",
//                        Toast.LENGTH_SHORT);
//                toast.show();
                return true;
            case R.id.menu_about:
                Log.d("MENU_DIALOG","ABOUT US");
                openAboutUsDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openNewNoteActivity() {
        Intent intent = new Intent(getApplicationContext(), InputNewActivity.class);
        MainActivity.this.startActivity(intent);
    }

    private void openAboutUsDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(R.string.dialog_text);
        dialogBuilder.setTitle(R.string.dialog_title);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData();
    }

    // this always calls the db.
    private void loadData() {
//        dataList.clear();
        adapter.clearItems();
        Cursor cursor = mydb.getAllData();
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                int id = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String txt = cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT));
                int img = cursor.getInt(cursor.getColumnIndex(myDbHelper.IMAGEID));
                String grp = cursor.getString(cursor.getColumnIndex(myDbHelper.COLOR));
                Boolean pin;
                if (cursor.getString(cursor.getColumnIndex(myDbHelper.ISPINNED)) != null &&
                        cursor.getString(cursor.getColumnIndex(myDbHelper.ISPINNED)).equalsIgnoreCase("1") )
                    pin = true; else pin = false;

                adapter.addItem(id, txt, img, grp, pin);
                cursor.moveToNext();
            }
        }

//        @Override
//        public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
//            return onLongListItemClick(v,pos,id);
//        }
//
//        protected boolean onLongListItemClick(View v, final int pos, long id) {
//
//            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
//            alertDialog.setTitle("Delete...");
//            alertDialog.setMessage("Are you sure?");
//            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    String a=dataList.get(+pos).get(INPUT_COLUMN_ID);
//                    mydb.deleteSingleContact(a);
//                    loadData();
//                }
//            });
//            alertDialog.show();
//            return true;
//        }});
    }
}
