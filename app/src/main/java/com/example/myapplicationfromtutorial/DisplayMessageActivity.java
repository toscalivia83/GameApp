package com.example.myapplicationfromtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplicationfromtutorial.service.Character;
import com.example.myapplicationfromtutorial.service.Player;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myapplicationfromtutorial.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }

    public void letsGo(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        ArrayList<String> character1Characteristics = new ArrayList<>();
        character1Characteristics.add("ugly");
        ArrayList<String> character2Characteristics = new ArrayList<>();
        character1Characteristics.add("tall");
        Character character1 = new Character(1, "url", character1Characteristics);
        Character character2 = new Character(2, "url", character1Characteristics);
        Player player1 = new Player("Marion", character1);
        Player player2 = new Player("Robin", character1);

        intent.putExtra("Player1", player1);
        intent.putExtra("Player2", player2);

        startActivity(intent);
    }
}
