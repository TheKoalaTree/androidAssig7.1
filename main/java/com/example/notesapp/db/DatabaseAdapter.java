package com.example.notesapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public void addNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into note_table(content) values(?)";
        Object[] args = {note.getContent()};
        db.execSQL(sql, args);
        db.close();
    }

    public Note findNoteById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from note_table where _id =?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        Note note = null;
        if (cursor.moveToNext()) {
            note = new Note();
            note.set_id(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            note.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));
        }
        cursor.close();
        db.close();
        return note;
    }

    public ArrayList<Note> findAllData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from note_table";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Note> noteArrayList = new ArrayList<>();
        Note note = null;
        while (cursor.moveToNext()) {
            note = new Note();
            note.set_id(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            note.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));
            noteArrayList.add(note);
        }
        cursor.close();
        db.close();
        return noteArrayList;
    }

    public void updateNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", note.getContent());
        String whereClause = "_id" + "=?";
        String[] whereArgs = {String.valueOf(note.get_id())};
        //表名，ContentValues,条件，条件的值
        db.update("note_table", values, whereClause, whereArgs);
        db.close();
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereclause = "_id" + "=?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete("note_table", whereclause, whereArgs);
        db.close();
    }
}
