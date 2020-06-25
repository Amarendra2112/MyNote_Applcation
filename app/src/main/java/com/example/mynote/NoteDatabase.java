package com.example.mynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    // Basic requirement for creating Database
    private static int DATABASE_VERSION = 2;
    private static String DATABASE_NAME = "NotesDatabase";
    private static String DATABASE_TABLE = "NoteTable";

    //Database constructor creation
    public NoteDatabase(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //Column name creation
    private static String KEY_ID = "id";
    private static String KEY_TITLE = "title";
    private static String KEY_CONTEXT = "context";
    private static String KEY_DATE = "date";
    private static String KEY_TIME = "time";


    //Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ID + " TEXT PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_CONTEXT + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion>=newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }

    public long addNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues n = new ContentValues();
        n.put(KEY_TITLE, note.getTitle());
        n.put(KEY_CONTEXT, note.getContent());
        n.put(KEY_DATE, note.getDate());
        n.put(KEY_TIME, note.getTime());
        // Putting into writable database
        long id = db.insert(DATABASE_TABLE, null , n);
        return id;
    }

    public List<Note> listOfNote()
    {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + KEY_ID + " DESC ";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {

                do {
                    Note note = new Note();
                    note.setId(cursor.getLong(0));
                    note.setTitle(cursor.getString(1));
                    note.setContent(cursor.getString(2));
                    note.setDate(cursor.getString(3));
                    note.setTime(cursor.getString(4));

                    notes.add(note);

                }while (cursor.moveToNext());

        }
        return notes;
    }

    public Note noteDetail(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[] {KEY_ID, KEY_TITLE,KEY_CONTEXT,KEY_DATE,KEY_TIME};
        Cursor cursor = db.query(DATABASE_TABLE, query ,KEY_TITLE + "=?", new String[] {id} ,null,null,null);
        Note note = new Note();

        if(cursor != null)
        {
            cursor.moveToFirst();
            note.setId(cursor.getLong(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setDate(cursor.getString(3));
            note.setTime(cursor.getString(4));

        }

        return note;
    }

    public void delete(String id)
    {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(DATABASE_TABLE, KEY_TITLE + " =? ", new String[] {id});
            db.close();
    }

    public void editSave(String id, Note note)
    {
        delete(id);
        addNote(note);
    }

}
