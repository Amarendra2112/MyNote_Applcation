package com.example.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.Instant;
import java.util.Calendar;

public class NewNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText title, details;
    Calendar c;
    String date, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newnote);
        toolbar = findViewById(R.id.Toolbar);
        title = findViewById(R.id.NTitle);
        details = findViewById(R.id.Details);
        setSupportActionBar(toolbar);

        //Setting toolbarText
        getSupportActionBar().setTitle("New Note");
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(getTitle() != null)
                {
                    getSupportActionBar().setTitle(s);
                }
                if (getSupportActionBar().getTitle().length() == 0)
                {
                    getSupportActionBar().setTitle("New Note");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //setting back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Getting date and time
        c= Calendar.getInstance();
        date = c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH);
        time = c.get(Calendar.HOUR) + ": " +  c.get(Calendar.MINUTE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId() == R.id.Save)
        {
            Note note = new Note(title.getText().toString(), details.getText().toString(),date,time);
            NoteDatabase data = new NoteDatabase(this);
            Long id = data.addNote(note);
            Log.d("Inserted", "Id" + id );
            Toast.makeText(this, "New Note is Created",Toast.LENGTH_SHORT).show();
            onSave();
        }

        if(item.getItemId() == R.id.Delete)
        {
            Toast.makeText(this, "Note is reset" , Toast.LENGTH_SHORT);
            title.setText(null);
            details.setText(null);

        }
        return super.onOptionsItemSelected(item);
    }

    private void onSave() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }


}
