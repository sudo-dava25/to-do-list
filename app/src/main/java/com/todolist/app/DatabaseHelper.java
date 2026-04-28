package com.todolist.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE = "tasks";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT," +
            "completed INTEGER," +
            "priority TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addTask(String title, String priority) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("completed", 0);
        values.put("priority", priority);
        db.insert(TABLE, null, values);
        db.close();
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateCompleted(int id, boolean completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", completed ? 1 : 0);
        db.update(TABLE, values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " ORDER BY completed ASC", null);
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2) == 1,
                    cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }
}
