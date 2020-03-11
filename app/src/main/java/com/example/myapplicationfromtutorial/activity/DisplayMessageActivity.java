package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplicationfromtutorial.R;
import com.example.myapplicationfromtutorial.model.Character;
import com.example.myapplicationfromtutorial.model.Player;

import java.util.List;

import sqlite.database.DatabaseHelper;

public class DisplayMessageActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        int drawableId = getResources().getIdentifier(
                initializePlayerWithCharacter("Marion").getSelectedCharacter().getImgUrl(),
                "drawable",
                "com.example.myapplicationfromtutorial"
        );
        ImageView iv = findViewById(R.id.imageView2);
        iv.setImageDrawable(getResources().getDrawable(drawableId));

    }

    public Player initializePlayerWithCharacter(String playerName) {
        db = new DatabaseHelper(this);
        List<Character> characters = db.getAllCharacters();
        int randomNumber = (int) Math.round(Math.random() * characters.size());
        Character characterChosen = characters.get(randomNumber);
        return new Player(playerName, new Character(characterChosen.getId(), characterChosen.getImgUrl(), characterChosen.getCharacteristics()));
    }

    public void letsGo(View view) {
        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }
}
