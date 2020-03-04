package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationfromtutorial.R;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        TextView tv = findViewById(R.id.textView);
        tv.setText(getIntent().getSerializableExtra("Player1Name").toString());
    }

    public void switchAgain(View view) {
        Intent previousIntent = getIntent();
        Intent intent = new Intent(TransitionActivity.this, BoardActivity.class);
        intent.putExtra("Player2", previousIntent.getSerializableExtra("Player2"));
        startActivity(intent);
//        Intent intent = new Intent();
//        intent.putExtra("ITEM_ID", 2);
//        setResult(12 , intent);
//        finish();
    }
}
