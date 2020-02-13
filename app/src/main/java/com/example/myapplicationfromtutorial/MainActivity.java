package com.example.myapplicationfromtutorial;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        this.displayPlayerNameInTextView();
        db = new DatabaseHelper(this);

//        List<Character> characters = db.getAllCharacters();
        List<String> imageUrlCharacterList = Collections.nCopies(18, "@drawable/c86");//set temporary drawable image url in array

        createImageViewForEachCharacter(imageUrlCharacterList);
    }

    private void createImageViewForEachCharacter(List<String> imageUrlCharacterList) {
        int imageIds[] = new int[imageUrlCharacterList.size()];

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        for(int i = 0; i < imageUrlCharacterList.size(); i++) {
//            ImageView imageView = createCharacterImageView(imageUrlCharacterList.get(i))
            ImageView imageView = createCharacterImageView(R.drawable.c86);

            imageIds[i] = imageView.getId();
            constraintLayout.addView(imageView);
        };

        addConstraintsToImageViews(constraintLayout, imageIds, imageUrlCharacterList.size());
        Flow flow = (Flow)findViewById(R.id.flow);
        flow.setReferencedIds(imageIds);
    }

    private ImageView createCharacterImageView(int characterDrawableImage) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);// layout constraint to set for each ImageViea
        ImageView imageView = new ImageView(this);
        imageView.setId(View.generateViewId());
        imageView.setImageDrawable(getResources().getDrawable(characterDrawableImage));
        imageView.setLayoutParams(layoutParams);
        imageView.setRight(50);
        imageView.setLeft(60);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCharacterImageShown = imageView.getDrawable().getConstantState().equals
                        (imageView.getResources().getDrawable(characterDrawableImage).getConstantState());
                imageView.setImageDrawable(getResources().getDrawable(
                        isCharacterImageShown
                                ? R.drawable.boursin // when card is hidden always show the same
                                : characterDrawableImage
                ));
            }
        });
        return imageView;
    }

    private void addConstraintsToImageViews(ConstraintLayout constraintLayout, int[] imageIds, int imageUrlCharacterListSize) {
        for(int i = 0; i < imageUrlCharacterListSize - 1; i++) {
            ConstraintSet constraints = new ConstraintSet();
            constraints.clone(constraintLayout);
            constraints.connect(imageIds[i], ConstraintSet.START, imageIds[i+1], ConstraintSet.END, 15);
            constraints.applyTo(constraintLayout);
        }
    }

    private void displayPlayerNameInTextView() {
        Player player = this.getPlayer();
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("The current player name is " +player.getName());
    }

    private Player getPlayer() {
        return (Player)getIntent().getSerializableExtra("Player");
    }

}
