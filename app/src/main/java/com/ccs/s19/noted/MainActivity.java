package com.ccs.s19.noted;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private NoteAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private Button addNote;
    private Button btnCamera;

    private myDbAdapter mydb;

    private static final int CAMERA_PIC_REQUEST = 1337;

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
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == CAMERA_PIC_REQUEST) {
                Bitmap img = (Bitmap) data.getExtras().get("data");
                String image;
                image = BitMapToString(img);
                System.out.println("in onActivityResult");

                myDbAdapter db = new myDbAdapter(getApplicationContext());
                long id = db.insertData(NoteModel.IMG_TYPE, "", image, "", false, "0", "0", "0");
                if (id > 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Note added!",
                            Toast.LENGTH_SHORT);
                    toast.show();

                    // Close activity
//                finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "ERROR! not addded!",
                            Toast.LENGTH_SHORT);
                    toast.show();

                }
            }
        }catch (Exception e) {
            Log.d("onActivityResult", "e: "+e);
        }

    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
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
                openNewNoteActivity();
                return true;
            case R.id.menu_deleteNote:
                Log.d("MENU_DIALOG","DELETE NOTE");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.alert_delete_title);
                builder.setMessage(R.string.alert_delete_all);
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
//            case R.id.menu_sort:
//                Log.d("MENU_DIALOG","SORT NOTE");
//                toast = Toast.makeText(getApplicationContext(),
//                        "Implement SORT NOTE!!!",
//                        Toast.LENGTH_SHORT);
//                toast.show();
//                return true;
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
        adapter.clearItems();
        Cursor cursor = mydb.getAllData();
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                int id = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String txt = cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT));
                String img = cursor.getString(cursor.getColumnIndex(myDbHelper.IMAGEID));
                String grp = cursor.getString(cursor.getColumnIndex(myDbHelper.COLOR));
                Boolean pin;
                if (cursor.getString(cursor.getColumnIndex(myDbHelper.ISPINNED)) != null &&
                        cursor.getString(cursor.getColumnIndex(myDbHelper.ISPINNED)).equalsIgnoreCase("1") )
                    pin = true; else pin = false;
                String hour = cursor.getString(cursor.getColumnIndex(myDbHelper.HOUR));
                String minute = cursor.getString(cursor.getColumnIndex(myDbHelper.MINUTE));
                String second = cursor.getString(cursor.getColumnIndex(myDbHelper.SECOND));
                int type = cursor.getInt(cursor.getColumnIndex(myDbHelper.TYPE));

                System.out.println(hour + "" + minute + "" + second);

                adapter.addItem(type, id, txt, img, grp, pin, hour, minute, second);
                cursor.moveToNext();
            }
        }

    }

}
