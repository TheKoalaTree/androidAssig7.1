package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.db.DatabaseAdapter;
import com.example.notesapp.db.Note;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    private RecyclerView rvNoteList;
    DatabaseAdapter databaseAdapter;
    NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        initView();
    }

    private void initView() {
        rvNoteList = (RecyclerView) findViewById(R.id.rvNoteList);

        databaseAdapter = new DatabaseAdapter(this);
        ArrayList<Note> allData = databaseAdapter.findAllData();

        noteListAdapter = new NoteListAdapter(this, allData);
        rvNoteList.setLayoutManager(new LinearLayoutManager(this));
        rvNoteList.setAdapter(noteListAdapter);
        noteListAdapter.setOnItemClickListener(new NoteListAdapter.OnItemClickListener() {
            @Override
            public void onClick(Note note) {
                Intent intent = new Intent(NoteListActivity.this, EditNoteActivity.class);
                intent.putExtra("note", note);
                startActivityForResult(intent,101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.e("onActivityResult","onActivityResult");
            ArrayList<Note> allData = databaseAdapter.findAllData();
            noteListAdapter.setNoteList(allData);
        }
    }
}
