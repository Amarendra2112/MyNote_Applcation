package com.example.mynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class Edit extends AppCompatActivity {
    Toolbar toolbar;
    EditText editTitle, editContext;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        editTitle = findViewById(R.id.EditTitle);
        editContext = findViewById(R.id.EditContent);

        Intent intent = getIntent();
        id = intent.getStringExtra("TITLE");

        NoteDatabase db = new NoteDatabase(this);
        Note note = db.noteDetail(id);
        getSupportActionBar().setTitle(note.getTitle());
        editTitle.setText(note.getTitle());
        editContext.setText(note.getContent());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editsave,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.EditSave)
        {
            NoteDatabase db = new NoteDatabase(this);
            Note note = db.noteDetail(id);
            db.delete(id);
            note.setTitle(editTitle.getText().toString());
            note.setContent(editContext.getText().toString());
            db.addNote(note);
            Intent intent =  new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}