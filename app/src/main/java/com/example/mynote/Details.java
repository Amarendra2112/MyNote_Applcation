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
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
    Toolbar toolbar;
    TextView details;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        id = i.getStringExtra("ID");
        Log.d("IDUsed", id);

        NoteDatabase database = new NoteDatabase(this);
        Note note = database.noteDetail(id);

        details = findViewById(R.id.DetailText);
        details.setText(note.getContent());
        getSupportActionBar().setTitle(note.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.DetailDelete)
        {
            NoteDatabase db = new NoteDatabase(this);
            db.delete(id);
            Toast.makeText(this,"Note is deleted", Toast.LENGTH_SHORT);
            backToMain();
        }
        else if(item.getItemId() == R.id.DetailEdit)
        {
            Intent intent = new Intent(this,Edit.class);
            intent.putExtra("TITLE",id);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void backToMain()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}