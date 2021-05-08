package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.db.DatabaseAdapter;
import com.example.notesapp.db.Note;

import java.io.Serializable;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etContent;
    private Button btnUpdate;
    private Button btnDelete;
    Note note;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        initView();
        databaseAdapter = new DatabaseAdapter(this);
        Intent intent = getIntent();

        note = (Note) intent.getSerializableExtra("note");
        etContent.setText(note.getContent());
    }

    private void initView() {
        etContent = (EditText) findViewById(R.id.etContent);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                submit();
                break;
            case R.id.btnDelete:
                databaseAdapter.deleteNote(note.get_id());
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String etContentString = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(etContentString)) {
            Toast.makeText(this, "Enter your note here", Toast.LENGTH_SHORT).show();
            return;
        }
        note.setContent(etContentString);
        databaseAdapter.updateNote(note);
        finish();
    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }
}
