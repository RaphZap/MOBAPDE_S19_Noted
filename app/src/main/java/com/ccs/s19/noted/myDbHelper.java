package com.ccs.s19.noted;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class myDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    protected static final String TABLE_NAME = "NoteTable";   // Table Name
    protected static final String TABLE_CHECKLIST = "Checklist";   // Table Name
    private static final int DATABASE_Version = 1;    // Database Version

    protected static final String UID="_id";     // Column I (Primary Key)
    protected static final String TEXT = "Text";    //Column II
    protected static final String IMAGEID= "ImageID";    // Column III
    protected static final String COLOR= "COLOR";    // Column IV
    protected static final String ISPINNED= "IsPinned";    // Column V

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TEXT+" VARCHAR(255) ,"+
            IMAGEID +" ," +COLOR+" ," +ISPINNED+" );";

//    private static final String CREATE_CHECK_TABLE = "CREATE TABLE "+TABLE_CHECKLIST+
//            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225));";

    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public myDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast toast = Toast.makeText(context,
                    "Create Table Executed!!!!!",
                    Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(context,
                    "onCreate e: "+e,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast toast = Toast.makeText(context,
                    "onUpgrade",
                    Toast.LENGTH_SHORT);
            toast.show();

            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            Toast toast = Toast.makeText(context,
                    "onUpgrade e: "+e,
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
