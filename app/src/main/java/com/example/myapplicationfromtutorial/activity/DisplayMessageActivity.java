package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplicationfromtutorial.R;

public class DisplayMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }

    public void letsGo(View view) {
        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }
}
