package sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sqlite.database.model.Character;
import sqlite.database.model.Question;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "sample_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Character.COLUMN_ID, id);
        values.put(Character.COLUMN_IMG_URL, imgUrl);
        values.put(Character.COLUMN_CHARACTERISTICS, characteristics);

        long idCreated = db.insert(Character.TABLE_NAME, null, values);

        db.close();

        return idCreated;
    }

    public Character getCharacter(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Character.TABLE_NAME,
                new String[]{Character.COLUMN_ID, Character.COLUMN_IMG_URL, Character.COLUMN_CHARACTERISTICS},
                Character.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Character character = new Character(
                cursor.getInt(cursor.getColumnIndex(Character.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Character.COLUMN_IMG_URL)),
                cursor.getString(cursor.getColumnIndex(Character.COLUMN_CHARACTERISTICS)));

        cursor.close();

        return character;
    }


    public long insertQuestion(int id, String sentence, String characteristics) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Question.COLUMN_ID, id);
        values.put(Question.COLUMN_SENTENCE, sentence);
        values.put(Question.COLUMN_CHARACTERISTICS, characteristics);

        long idCreated = db.insert(Question.TABLE_NAME, null, values);

        db.close();

        return idCreated;
    }

    public Question getQuestion(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Question.TABLE_NAME,
                new String[]{Question.COLUMN_ID, Question.COLUMN_SENTENCE, Question.COLUMN_CHARACTERISTICS},
                Question.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Question question = new Question(
                cursor.getInt(cursor.getColumnIndex(Question.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_SENTENCE)),
                cursor.getString(cursor.getColumnIndex(Question.COLUMN_CHARACTERISTICS)));

        cursor.close();

        return question;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Question.TABLE_NAME + " ORDER BY " +
                Question.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(Question.COLUMN_ID)));
                question.setSentence(cursor.getString(cursor.getColumnIndex(Question.COLUMN_SENTENCE)));
                question.setCharacteristics(cursor.getString(cursor.getColumnIndex(Question.COLUMN_CHARACTERISTICS)));

                questions.add(question);
            } while (cursor.moveToNext());
        }

        db.close();

        return questions;
    }

    public List<com.example.myapplicationfromtutorial.service.Character> getAllCharacters() {
        List<Character> characters = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Character.TABLE_NAME + " ORDER BY " +
                Character.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<com.example.myapplicationfromtutorial.service.Character> charactersOther = new ArrayList<com.example.myapplicationfromtutorial.service.Character>();

        if (cursor.moveToFirst()) {
            do {
                charactersOther.add(
                        new com.example.myapplicationfromtutorial.service.Character(
                                cursor.getInt(cursor.getColumnIndex(Character.COLUMN_ID)),
                                cursor.getString(cursor.getColumnIndex(Character.COLUMN_IMG_URL)),
                                cursor.getString(cursor.getColumnIndex(Character.COLUMN_CHARACTERISTICS))
                        )
                );
            } while (cursor.moveToNext());
        }

        db.close();

        return charactersOther;
    }


    public List<String> getAllQuestionSentences() {
        return this.getAllQuestions().stream()
                .map(Question::getSentence)
                .collect(Collectors.toList());
    }

    // TODO: this patch should be in a specific file. maybe called 'CharacterQueryImplementation'?

    public void reinitialiseDbWithValues() {
        long characterId1 = this.insertCharacter(1, "@drawable/horse1","black,canter");
        long characterId2 = this.insertCharacter(2, "@drawable/horse2","brown,standing");
        long characterId3 = this.insertCharacter(3, "@drawable/horse3","white,canter");
        long characterId4 = this.insertCharacter(4, "@drawable/horse4","baie,standing");
        long characterId5 = this.insertCharacter(5, "@drawable/horse5","grey,canter");
        long characterId6 = this.insertCharacter(6, "@drawable/horse6","brown,headonly,");
        long characterId7 = this.insertCharacter(7, "@drawable/horse7","black,white,headonly");
        long characterId8 = this.insertCharacter(8, "@drawable/horse8","shadow,rearing");
        long characterId9 = this.insertCharacter(9, "@drawable/horse9","grey,canter,lounge");
        long characterId10 = this.insertCharacter(10, "@drawable/horse10","baie,headonly,worried");
        long characterId11 = this.insertCharacter(11, "@drawable/horse11","baie,tired");
        long characterId12 = this.insertCharacter(12, "@drawable/horse12","isabelle,headonly");
        long characterId13 = this.insertCharacter(13, "@drawable/horse13","brown,standing");
        long characterId14 = this.insertCharacter(14, "@drawable/horse14","grey,headonly,smart");
        long characterId15 = this.insertCharacter(15, "@drawable/horse15","brown,white,small,sitted");
//        long questionId = this.insertQuestion(1, "Is ugly?", "ugly");
//        long questionId2 = this.insertQuestion(2, "Is big?", "big");
//        long questionId3 = this.insertQuestion(3, "Is short?", "short");
    }

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

    public void deleteCharacter(int characterId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Character.TABLE_NAME, Character.COLUMN_ID + " = ?",
                new String[]{String.valueOf(characterId)});
        db.close();
    }
}