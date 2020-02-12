package com.example.myapplicationfromtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Collections;
import java.util.List;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        List<String> list = Collections.nCopies(5, "foo");
    }
}
