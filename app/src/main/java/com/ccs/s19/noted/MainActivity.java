package com.ccs.s19.noted;

import android.app.AlertDialog;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private NoteAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerArea = findViewById(R.id.recycler_area);

        manager = new LinearLayoutManager(this);
        recyclerArea.setLayoutManager(manager);

        adapter = new NoteAdapter();
        recyclerArea.setAdapter(adapter);
    }

    public void addItem(View view){
//        Random rand = new Random();
        adapter.addItem("",0);

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
                Log.d("MENU_DIALOG","NEW NOTE");
                toast = Toast.makeText(getApplicationContext(),
                        "Implement NEW NOTE!!!",
                            Toast.LENGTH_SHORT);
                toast.show();

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
                Log.d("MENU_DIALOG","NEW NOTE");
                openAboutUsDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAboutUsDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(R.string.dialog_text);
        dialogBuilder.setTitle(R.string.dialog_title);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
