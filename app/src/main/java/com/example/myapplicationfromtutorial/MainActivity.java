package com.example.myapplicationfromtutorial;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplicationfromtutorial.service.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import sqlite.database.DatabaseHelper;
import sqlite.database.model.Character;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get player name and display in textView
        Player player = (Player)getIntent().getSerializableExtra("Player");
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("The current player name is " +player.getName());

        db = new DatabaseHelper(this);

//        List<Character> characters = db.getAllCharacters();
        List<String> imageUrlCharacterList = Collections.nCopies(6, "@drawable/c86");//set temporary drawable image url in array

        createImageViewForEachCharacterImage(imageUrlCharacterList);
    }

    private void createImageViewForEachCharacterImage(List<String> imageUrlCharacterList) {
        int imageIds[] = new int[6];

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        for(int i = 0; i < imageUrlCharacterList.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);// layout constraint to set for each ImageViea

            ImageView iv = new ImageView(this);
            iv.setId(View.generateViewId());
            imageIds[i] = iv.getId();
            iv.setImageDrawable(getResources().getDrawable(R.drawable.c86));
            iv.setLayoutParams(layoutParams);
            iv.setRight(50);
            iv.setLeft(60);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isCurrentCharacterImageUrl = iv.getDrawable().getConstantState().equals
                            (iv.getResources().getDrawable(R.drawable.c86).getConstantState());
                    iv.setImageDrawable(getResources().getDrawable(
                            isCurrentCharacterImageUrl
                                    ? R.drawable.boursin
                                    : R.drawable.c86
                    ));
                }
            });
            constraintLayout.addView(iv);
        };

        for(int i = 0; i < imageUrlCharacterList.size() - 1; i++) {
            ConstraintSet constraints = new ConstraintSet();
            constraints.clone(constraintLayout);
            constraints.connect(imageIds[i], ConstraintSet.START, imageIds[i+1], ConstraintSet.END, 15);
            constraints.applyTo(constraintLayout);
        }
    }

}

//    List<String> questions = db.getAllQuestionSentences();
//        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLinear);
//        questions.forEach(question -> {
//            TextView tv1 = new TextView(this);
//            tv1.append(question);
//            tv1.setTextColor(Color.RED);
//            tv1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    tv1.setText("clicked");
//                }
//            });
//            layout.addView(tv1);
//        });
