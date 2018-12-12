package com.ccs.s19.noted;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class myDbAdapter {

    private myDbHelper myhelper;

    public myDbAdapter(Context context)
    {
        System.out.println("``` inside mydbAdapter");
        myhelper = new myDbHelper(context);
    }

    public long insertData(int type, String text, String imageID, String group, Boolean pinme, String hour, String minute, String second)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myhelper.TYPE,type);
        contentValues.put(myDbHelper.TEXT, text);
        contentValues.put(myDbHelper.IMAGEID, imageID);
        contentValues.put(myhelper.COLOR, group);
        contentValues.put(myhelper.ISPINNED, pinme);
        contentValues.put(myhelper.HOUR,hour);
        contentValues.put(myhelper.MINUTE,minute);
        contentValues.put(myhelper.SECOND,second);

        long id = db.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + myhelper.TABLE_NAME +
                " ORDER BY " +myDbHelper.ISPINNED + " DESC", null );
        return res;
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.TEXT,myDbHelper.IMAGEID,myDbHelper.COLOR,myDbHelper.ISPINNED,
                            myDbHelper.HOUR, myDbHelper.MINUTE, myDbHelper.SECOND,myDbHelper.TYPE};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String text =cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT));
            String imageid =cursor.getString(cursor.getColumnIndex(myDbHelper.IMAGEID));
            String color =cursor.getString(cursor.getColumnIndex(myDbHelper.COLOR));
            String ispinned =cursor.getString(cursor.getColumnIndex(myDbHelper.ISPINNED));
            String hour = cursor.getString(cursor.getColumnIndex(myDbHelper.HOUR));
            String minute = cursor.getString(cursor.getColumnIndex(myDbHelper.MINUTE));
            String second = cursor.getString(cursor.getColumnIndex(myDbHelper.SECOND));
            String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));

            buffer.append(cid +"  " +text+ "  " +imageid+ "  " +color+ "  " +ispinned+ "  " +hour+ "  " +minute+ "  " +second +"  " +type+ " \n");
        }
        return buffer.toString();
    }

    public int deleteByID(int id) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(id)};

        int count = db.delete(myDbHelper.TABLE_NAME ,myDbHelper.UID+" = ?",whereArgs);
        return count;
    }

    public Cursor getDataByID(int id) {
        SQLiteDatabase db = myhelper.getWritableDatabase();

        String selectQuery = "SELECT  * FROM " + myDbHelper.TABLE_NAME + " WHERE "
                + myDbHelper.UID + " = " + id;

        Log.d("DB ADAPTER", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public int updateText(int type, int id, String newText, String imageid, String group, boolean isPin, String hour, String minute, String second) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TEXT,newText);
        contentValues.put(myDbHelper.IMAGEID,imageid);
        contentValues.put(myDbHelper.COLOR,group); // group ???
        contentValues.put(myDbHelper.ISPINNED,isPin);
        contentValues.put(myDbHelper.HOUR, hour);
        contentValues.put(myDbHelper.MINUTE, minute);
        contentValues.put(myDbHelper.SECOND, second);
        contentValues.put(myDbHelper.TYPE, type);
        String[] whereArgs= {String.valueOf(id)};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.UID+" = ?",whereArgs );
        return count;
    }

    // WARNING: this delete all rows!!!
    public int deleteAll()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
//        db.execSQL("delete from "+ myDbHelper.TABLE_NAME);
//        db.execSQL("TRUNCATE table" + TABLE_NAME);
        int count = db.delete(myDbHelper.TABLE_NAME, null,null);
        Log.d("myDBAdapter", "rows affected: "+count);
        return count;
    }


}
