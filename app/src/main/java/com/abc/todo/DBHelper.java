package com.abc.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ToDOHelper.db";
    private static final String CONTACTS_TABLE_NAME = "todo";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table " + CONTACTS_TABLE_NAME + "(id INTEGER PRIMARY KEY, task STRING, priority INTEGER, isComplete BOOLEAN, completionDate DATETIME, addingDate DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    protected void insert(String task, int priority, boolean isComplete) {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("task", task);
        contentValues.put("priority", priority);
        contentValues.put("isComplete", isComplete);
        contentValues.put("addingDate", currentDate);
        contentValues.put("isComplete", false);

        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
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
}
