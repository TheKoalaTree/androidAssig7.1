package com.example.notesapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.db.DatabaseAdapter;
import com.example.notesapp.db.Note;

import java.util.ArrayList;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etContent;
    private Button btnSave;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        initView();

        databaseAdapter = new DatabaseAdapter(this);

    }

    private void initView() {
        etContent = (EditText) findViewById(R.id.etContent);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                submit();
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

        // TODO validate success, do something
        databaseAdapter.addNote(new Note(etContentString));
        Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();
        finish();

    }
}
