package com.abc.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;



public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";
    private static final String DATABASE_NAME = "ToDoList.db";
    private static final String CONTACTS_TABLE_NAME = "todo";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cmd = "Create Table " + CONTACTS_TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT , task STRING, priority INTEGER, isComplete BOOLEAN, completionDate DATETIME, addingDate DATETIME)";
        db.execSQL(cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String cmd = "DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME;
        db.execSQL(cmd);
        onCreate(db);
    }

    protected boolean insert(String task, int priority) {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("task", task);
        contentValues.put("priority", priority);
        contentValues.put("addingDate", currentDate);
        contentValues.put("isComplete", false);

        Log.d(TAG, "addData: Adding task = " + task + " and priority =" + priority + " to " + CONTACTS_TABLE_NAME);

        long result = db.insert(CONTACTS_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    protected void updatePriority(int id, int priority)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("priority", priority);

        db.update(CONTACTS_TABLE_NAME, contentValues, "id = " + id, null);
    }

    protected void updateCompletion(int id, boolean isComplete)
    {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("isComplete", isComplete);
        contentValues.put("completionDate", currentDate);

        db.update(CONTACTS_TABLE_NAME, contentValues, "id = " + id, null);
    }

    //Select query remaining


    protected Cursor selectAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rm =db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME +" ORDER BY priority, addingDate", null);
        return rm;
    }

    protected  Cursor  selectById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rm = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME + " WHERE id = " + id, null);
        return rm;
    }

    protected Cursor selectAllAccordingToPriority()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rm = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME + " ORDER BY priority", null);
        return rm;
    }
}
