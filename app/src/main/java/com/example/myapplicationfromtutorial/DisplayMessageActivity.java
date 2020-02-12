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
        Button goButton= findViewById(R.id.go);
//        String message = goButton.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);

        ArrayList<String> character1Characteristics = new ArrayList<>();
        character1Characteristics.add("ugly");
        Character character1 = new Character(1, "url", character1Characteristics);
        Player player = new Player("Marion", character1);

        intent.putExtra("Player", player);

        startActivity(intent);
    }
}
