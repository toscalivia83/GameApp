package com.example.myapplicationfromtutorial;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.myapplicationfromtutorial.service.Board;
import com.example.myapplicationfromtutorial.service.Character;
import com.example.myapplicationfromtutorial.service.CharacterOnBoard;
import com.example.myapplicationfromtutorial.service.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import sqlite.database.DatabaseHelper;

/**
 * BoardService which sets all the useful fields so that the board is displayed correctly
 */
public class BoardService extends Service {

    private DatabaseHelper db;

    private Intent intent;
    private Resources resources;

    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private ArrayList<CharacterOnBoard> characters;

    // Binder given to clients
    private final IBinder binder = new BoardServiceBinder();

    public class BoardServiceBinder extends Binder {
        BoardService getService() {
            return BoardService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        setCurrentPlayer(getPlayer1FromIntent(intent));
        setPlayer1(getPlayer1FromIntent(intent));
        setPlayer2(getPlayer2FromIntent(intent));
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        initializeBoard(intent);
        return Service.START_STICKY; // what does that mean?
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    public Board initializeBoard(
//            Intent intent,
//            ConstraintLayout constraintLayout,
//            TextView textView,
//            Resources resources) {
    public void initializeBoard(
            Intent intent) {
        this.resources = resources;

        db = new DatabaseHelper(this);

        List<Character> characters = db.getAllCharacters();

//        createImageViewForEachCharacter(
//                characters.stream()
//                        .map(c -> c.getImgUrl())
//                        .collect(Collectors.toList()),
//                constraintLayout
//        );
//        return new Board(getPlayer1(intent), getPlayer2(intent), characters, characters);
    }

    public Player switchPlayer() {
        if (getCurrentPlayer().getName() == getPlayer1().getName()) {
            setCurrentPlayer(getPlayer2());
        } else {
            setCurrentPlayer(getPlayer1());
        }
        return currentPlayer;
    }

//    private void createImageViewForEachCharacter(List<String> imageUrlCharacterList, ConstraintLayout constraintLayout) {
//        int imageIds[] = new int[imageUrlCharacterList.size()];
//
//        for(int i = 0; i < imageUrlCharacterList.size(); i++) {
////            ImageView imageView = createCharacterImageView(imageUrlCharacterList.get(i))
//            int drawableId = resources.getIdentifier(
//                    imageUrlCharacterList.get(i),
//                    "drawable",
//                    "com.example.myapplicationfromtutorial"
//            );
//
//            ImageView imageView = createCharacterImageView(
//                    drawableId,
//                    i,
//                    resources
//            );
//
//            imageIds[i] = imageView.getId();
//            constraintLayout.addView(imageView);
//        };
//
//        addConstraintsToImageViews(constraintLayout, imageIds, imageUrlCharacterList.size());
//    }
//
//    private ImageView createCharacterImageView(int characterDrawableImage, int index, Resources resources) {
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);// layout constraint to set for each ImageViea
//        ImageView imageView = new ImageView(this);
//        imageView.setId(index);
//        imageView.setImageDrawable(resources.getDrawable(characterDrawableImage));
//        imageView.setLayoutParams(layoutParams);
//        imageView.setRight(50);
//        imageView.setLeft(60);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isCharacterImageShown = imageView.getDrawable().getConstantState().equals
//                        (imageView.getResources().getDrawable(characterDrawableImage).getConstantState());
//                imageView.setImageDrawable(resources.getDrawable(
//                        isCharacterImageShown
//                                ? R.drawable.boursin // when card is hidden always show the same
//                                : characterDrawableImage
//                ));
//            }
//        });
//        return imageView;
//    }
//
//    private void addConstraintsToImageViews(ConstraintLayout constraintLayout, int[] imageIds, int imageUrlCharacterListSize) {
//        for(int i = 0; i < imageUrlCharacterListSize - 1; i++) {
//            ConstraintSet constraints = new ConstraintSet();
//            constraints.clone(constraintLayout);
//            constraints.connect(imageIds[i], ConstraintSet.START, imageIds[i+1], ConstraintSet.END, 15);
//            constraints.applyTo(constraintLayout);
//        }
//    }

    public void displayCurrentPlayerNameInTextView(TextView textView) {
        textView.setText(getCurrentPlayer().getName());
    }

    private Player getPlayer1FromIntent(Intent intent) {
        return (Player)intent.getSerializableExtra("Player1");
    }

    private Player getPlayer2FromIntent(Intent intent) {
        return (Player)intent.getSerializableExtra("Player2");
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
