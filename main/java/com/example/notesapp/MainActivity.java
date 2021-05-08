package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreateNote;
    private Button btnNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnCreateNote = (Button) findViewById(R.id.btnCreateNote);
        btnNoteList = (Button) findViewById(R.id.btnNoteList);

        btnCreateNote.setOnClickListener(this);
        btnNoteList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateNote:
                startActivity(new Intent(MainActivity.this, CreateNoteActivity.class));
                break;
            case R.id.btnNoteList:
                startActivity(new Intent(MainActivity.this, NoteListActivity.class));
                break;
        }
    }
}
