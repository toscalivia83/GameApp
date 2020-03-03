package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplicationfromtutorial.R;

import sqlite.database.DatabaseHelper;

public class StartGame extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
    }

    public void startGameButtonAction(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }
}
