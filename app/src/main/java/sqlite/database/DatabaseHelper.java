package sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sqlite.database.model.Character;
import sqlite.database.model.Question;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sample_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create character table
        db.execSQL(Character.CREATE_TABLE);
        db.execSQL(Question.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Character.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertCharacter(int id, String imgUrl, String characteristics) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Character.COLUMN_ID, id);
        values.put(Character.COLUMN_IMG_URL, imgUrl);
        values.put(Character.COLUMN_CHARACTERISTICS, characteristics);

        // insert row
        long idCreated = db.insert(Character.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return idCreated;
    }

    public Character getCharacter(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Character.TABLE_NAME,
                new String[]{Character.COLUMN_ID, Character.COLUMN_IMG_URL, Character.COLUMN_CHARACTERISTICS},
                Character.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare character object
        Character character = new Character(
                cursor.getInt(cursor.getColumnIndex(Character.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Character.COLUMN_IMG_URL)),
                cursor.getString(cursor.getColumnIndex(Character.COLUMN_CHARACTERISTICS)));

        // close the db connection
        cursor.close();

        return character;
    }


    public long insertQuestion(int id, String sentence, String characteristics) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Question.COLUMN_ID, id);
        values.put(Question.COLUMN_SENTENCE, sentence);
        values.put(Question.COLUMN_CHARACTERISTICS, characteristics);

        // insert row
        long idCreated = db.insert(Question.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Question getQuestion(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Question.TABLE_NAME,
                new String[]{Question.COLUMN_ID, Question.COLUMN_SENTENCE, Question.COLUMN_CHARACTERISTICS},
                Question.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare Question object
        Question question = new Question(
                cursor.getInt(cursor.getColumnIndex(Question.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_SENTENCE)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_CHARACTERISTICS)));

        // close the db connection
        cursor.close();

        return question;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Question.TABLE_NAME + " ORDER BY " +
                Question.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(Question.COLUMN_ID)));
                question.setSentence(cursor.getString(cursor.getColumnIndex(Question.COLUMN_SENTENCE)));
                question.setCharacteristics(cursor.getString(cursor.getColumnIndex(Question.COLUMN_CHARACTERISTICS)));

                questions.add(question);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return questions list
        return questions;
    }
    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Character.TABLE_NAME + " ORDER BY " +
                Character.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Character character = new Character();
                character.setId(cursor.getInt(cursor.getColumnIndex(Character.COLUMN_ID)));
                character.setImg_url(cursor.getString(cursor.getColumnIndex(Character.COLUMN_IMG_URL)));
                character.setCharacteristics(cursor.getString(cursor.getColumnIndex(Character.COLUMN_CHARACTERISTICS)));

                characters.add(character);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        return characters;
    }


    public List<String> getAllQuestionSentences() {
        return this.getAllQuestions().stream()
                .map(Question::getSentence)
                .collect(Collectors.toList());
    }

    public void reinitialiseDbWithValues() {
        long characterId = this.insertCharacter(001, "urltest","ugly");
        long questionId = this.insertQuestion(1, "Is ugly?", "ugly");
        long questionId2 = this.insertQuestion(2, "Is big?", "big");
        long questionId3 = this.insertQuestion(3, "Is short?", "short");
    }

//    public int getNotesCount() {
//        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//
//        // return count
//        return count;
//    }
//
//    public int updateNote(Note note) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(Note.COLUMN_NOTE, note.getNote());
//
//        // updating row
//        return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(note.getId())});
//    }
//
//    public void deleteNote(Note note) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(note.getId())});
//        db.close();
//    }
}