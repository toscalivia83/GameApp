package com.example.myapplicationfromtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartGame extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myapplicationfromtutorial.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
    }

    public void startGameAction(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Button startGameButton = findViewById(R.id.startGameButton );
        String message = startGameButton .getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
