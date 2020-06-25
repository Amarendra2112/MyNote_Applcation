package com.example.mynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes;
    TextView noText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        NoteDatabase database = new NoteDatabase(this);
        notes = database.listOfNote();
        recyclerView = findViewById(R.id.ListOfNotes);
        noText =  findViewById(R.id.noText);



        if(notes.isEmpty())
        {
            noText.setVisibility(View.VISIBLE);
        }
        else
        {
            noText.setVisibility(View.GONE);
            setAdpterr();
        }

    }

    public void setAdpterr()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,notes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        {
            if(item.getItemId()==R.id.AddNote);
            {
                Toast.makeText(this,"Create new note",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,NewNote.class);
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}