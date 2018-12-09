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
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private NoteAdapter adapter = new NoteAdapter();
    private RecyclerView.LayoutManager manager;

    private myDbAdapter mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new myDbAdapter(this);
        recyclerArea = findViewById(R.id.recycler_area);

        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);

//        adapter = new NoteAdapter();
        recyclerArea.setAdapter(adapter);
        loadData();
    }

//    public void addItem(View view){
//        adapter.addItem("hmm",0);
//    }
//
//    public void addUser(View view)
//    {
//        String t1 = Name.getText().toString();
//        if(t1.isEmpty())
//        {
//            Message.message(getApplicationContext(),"Enter Both Name and Password");
//        }
//        else
//        {
//            long id = helper.insertData(t1,t2);
//            if(id<=0)
//            {
//                Message.message(getApplicationContext(),"Insertion Unsuccessful");
//                Name.setText("");
//            } else
//            {
//                Message.message(getApplicationContext(),"Insertion Successful");
//                Name.setText("");
//            }
//        }
//    }

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
                Log.d("MENU_DIALOG","NEW NOTE");
                toast = Toast.makeText(getApplicationContext(),
                        "Implement NEW NOTE!!!",
                            Toast.LENGTH_SHORT);
                toast.show();

                openNewNoteActivity();
                return true;
            case R.id.menu_editNote:
                Log.d("MENU_DIALOG","EDIT NOTE");
                toast = Toast.makeText(getApplicationContext(),
                        "Implement EDIT NOTE!!!",
                        Toast.LENGTH_SHORT);
                toast.show();
                return true;
            case R.id.menu_deleteNote:
                Log.d("MENU_DIALOG","DELETE NOTE");
                toast = Toast.makeText(getApplicationContext(),
                        "Implement DELETE NOTE!!!",
                        Toast.LENGTH_SHORT);
                toast.show();
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
                toast = Toast.makeText(getApplicationContext(),
                        "Implement SETTINGS!!!",
                        Toast.LENGTH_SHORT);
                toast.show();
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

//        String text = getIntent().getStringExtra("NOTEDATA");
//        Log.d("MAIN","text: " +text);
//        adapter.addItem(text,0);

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

    private void loadData() {
//        dataList.clear();
        Cursor cursor = mydb.getAllData();
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put(myDbHelper.UID, cursor.getString(cursor.getColumnIndex(myDbHelper.UID)));
//                map.put(myDbHelper.TEXT, cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT)));
//                dataList.add(map);
                int id = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String txt = cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT));
                int img = cursor.getInt(cursor.getColumnIndex(myDbHelper.IMAGEID));
                String grp = cursor.getString(cursor.getColumnIndex(myDbHelper.COLOR));

                adapter.addItem(id, txt, img, grp);
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
