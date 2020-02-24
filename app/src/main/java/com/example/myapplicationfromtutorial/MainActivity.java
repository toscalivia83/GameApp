package com.example.myapplicationfromtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplicationfromtutorial.service.Player;

import java.util.Collections;
import java.util.List;

import sqlite.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Player currentPlayer;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        setCurrentPlayer(getPlayer1());

        this.displayCurrentPlayerNameInTextView();
        db = new DatabaseHelper(this);

//        List<Character> characters = db.getAllCharacters();
        List<String> imageUrlCharacterList = Collections.nCopies(10, "@drawable/c86");//set temporary drawable image url in array

        createImageViewForEachCharacter(imageUrlCharacterList);
    }

    private void createImageViewForEachCharacter(List<String> imageUrlCharacterList) {
        int imageIds[] = new int[imageUrlCharacterList.size()];

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        for(int i = 0; i < imageUrlCharacterList.size(); i++) {
//            ImageView imageView = createCharacterImageView(imageUrlCharacterList.get(i))
            ImageView imageView = createCharacterImageView(R.drawable.c86, i);

            imageIds[i] = imageView.getId();
            constraintLayout.addView(imageView);
        };

        addConstraintsToImageViews(constraintLayout, imageIds, imageUrlCharacterList.size());
    }

    private ImageView createCharacterImageView(int characterDrawableImage, int index) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);// layout constraint to set for each ImageViea
        ImageView imageView = new ImageView(this);
        imageView.setId(index);
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

    private void displayCurrentPlayerNameInTextView() {
        Player player = this.getCurrentPlayer();
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("The current player name is " + player.getName());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private Player getPlayer1() {
        return (Player)getIntent().getSerializableExtra("Player1");
    }

    private Player getPlayer2() {
        return (Player)getIntent().getSerializableExtra("Player2");
    }

}
