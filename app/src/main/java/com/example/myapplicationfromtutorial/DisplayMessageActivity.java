package com.example.myapplicationfromtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplicationfromtutorial.service.Board;
import com.example.myapplicationfromtutorial.service.Character;
import com.example.myapplicationfromtutorial.service.CharacterOnBoard;
import com.example.myapplicationfromtutorial.service.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sqlite.database.DatabaseHelper;

public class DisplayMessageActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }

    public void letsGo(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        db = new DatabaseHelper(this);

        List<Character> characters = db.getAllCharacters();
        Player player1 = new Player("Marion", characters.get(1));
        Player player2 = new Player("Robin", characters.get(2));
        ArrayList<CharacterOnBoard> charactersOnBoard = new ArrayList<CharacterOnBoard>();
        characters.forEach(character -> {
            CharacterOnBoard characterOnBoard = new CharacterOnBoard(
                    character.getImgUrl(),
                    character.getCharacteristics(),
                    false
            );
            charactersOnBoard.add(characterOnBoard);
        });

        Board player1Board = new Board(1, player1, player2, charactersOnBoard);
        Board player2Board = new Board(2, player2, player1, charactersOnBoard);

        intent.putExtra("Player1Board", player1Board);
        intent.putExtra("Player2Board", player2Board);

        startActivity(intent);
    }
}
