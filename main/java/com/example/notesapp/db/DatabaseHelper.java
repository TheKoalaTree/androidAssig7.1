package com.example.notesapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

////SQLite 数据库初始化
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "note.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_NOTE = "Create Table IF NOT EXISTS note_table("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "content TEXT)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
