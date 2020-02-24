package com.example.myapplicationfromtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplicationfromtutorial.service.Board;
import com.example.myapplicationfromtutorial.service.CharacterOnBoard;
import com.example.myapplicationfromtutorial.service.Player;

import java.util.List;

import sqlite.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;

    public static Board currentBoard;

    // TODO: add currentConstraintLayoutDisplayedId

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

//        // Initialize to the player1 at first
//        setCurrentPlayer(this.getPlayer1Board().getCurrentPlayer());
//        setCurrentBoard(this.getPlayer1Board());
//
//        this.displayPlayerNameInTextView(this.getCurrentPlayer().getName());
//        db = new DatabaseHelper(this);
//
//        List<CharacterOnBoard> characterOnBoardList = getCurrentBoard()
//                .getCharactersOnBoard();

//        createImageViewForEachCharacter(
//                characterOnBoardList,
//                getCurrentBoard().getCurrentPlayer().getName()
//        );
    }

//    public void onSwitchPlayerClick(View view) {
//        this.displayPlayerNameInTextView(this.getOpponentPlayer().getName());
//        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
//        for(int i = 0; i < getCurrentBoard().getCharactersOnBoard().size(); i++) {
//
//        }
//        constraintLayout.removeAllViews();
//    }
//
//    public void onSetOtherBoardClick(View view) {
//        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
//
//        if (getCurrentPlayer().getName() == this.getPlayer1Board().getCurrentPlayer().getName()) {
//            setCurrentBoard(this.getPlayer2Board());
//        } else {
//            setCurrentBoard(this.getPlayer1Board());
//        }
//
//        int imageIds[] = new int[getCurrentBoard().getCharactersOnBoard().size()];
//
//        for(int i = 0; i < getCurrentBoard().getCharactersOnBoard().size(); i++) {
//            int drawableId = getResources().getIdentifier(getCurrentBoard().getCharactersOnBoard().get(i).getImgUrl(), "drawable", "com.example.myapplicationfromtutorial");
//            ImageView imageView = createCharacterImageView(drawableId, i, getCurrentBoard().getCharactersOnBoard(), getCurrentBoard().getOpponentPlayer().getName());
//
//            imageIds[i] = imageView.getId();
//            constraintLayout.addView(imageView);
//        };
//
////        addConstraintsToImageViews(constraintLayout, imageIds, getCurrentBoard().getCharactersOnBoard().size());
////        Flow flow = (Flow)findViewById(
////                getCurrentBoard().getCurrentPlayer().getName() == getPlayer1Board().getCurrentPlayer().getName()
////                ? R.id.flow
////                : R.id.flow2);
////        flow.setReferencedIds(imageIds);
//
////        List<CharacterOnBoard> characterOnBoardList = getCurrentBoard()
////                .getCharactersOnBoard();
////        createImageViewForEachCharacter(characterOnBoardList, getCurrentBoard().getOpponentPlayer().getName());
//    }
//
//    private void createImageViewForEachCharacter(List<CharacterOnBoard> characterOnBoardList, String playerName) {
//        int imageIds[] = new int[characterOnBoardList.size()];
//
//        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
//
//        for(int i = 0; i < characterOnBoardList.size(); i++) {
//            int drawableId = getResources().getIdentifier(characterOnBoardList.get(i).getImgUrl(), "drawable", "com.example.myapplicationfromtutorial");
//            ImageView imageView = createCharacterImageView(drawableId, i, characterOnBoardList, playerName);
//
//            imageIds[i] = imageView.getId();
//            constraintLayout.addView(imageView);
//        };
//
////        addConstraintsToImageViews(constraintLayout, imageIds, characterOnBoardList.size());
////        Flow flow = (Flow)findViewById(R.id.flow);
////        flow.setReferencedIds(imageIds);
//    }
//
//    private ImageView createCharacterImageView(int characterDrawableImage, int index, List<CharacterOnBoard>  characterOnBoardList, String playerName) {
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);// layout constraint to set for each ImageView
//        ImageView imageView = new ImageView(this);
//        imageView.setId(View.generateViewId());
//        imageView.setImageDrawable(getResources().getDrawable(characterDrawableImage));
//        imageView.setLayoutParams(layoutParams);
//        imageView.setRight(50);
//        imageView.setLeft(60);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isCharacterImageShown = imageView.getDrawable().getConstantState().equals
//                        (imageView.getResources().getDrawable(characterDrawableImage).getConstantState());
//                imageView.setImageDrawable(getResources().getDrawable(
//                        isCharacterImageShown
//                                ? R.drawable.boursin // when card is hidden always show the same
//                                : characterDrawableImage
//                ));
//                characterOnBoardList.get(index).setHidden(isCharacterImageShown);
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

    private void displayPlayerNameInTextView(String playerName) {
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("The current player name is " + playerName);
        // TODO: should we call player 1 and player 2 ??
    }

    private Board getPlayer1Board() {
        return (Board)getIntent().getSerializableExtra("Player1Board");
    }

    private Board getPlayer2Board() {
        return (Board)getIntent().getSerializableExtra("Player2Board");
    }

    public static Board getCurrentBoard() {
        return currentBoard;
    }

    public static void setCurrentBoard(Board currentBoard) {
        MainActivity.currentBoard = currentBoard;
    }

    private Player getCurrentPlayer() {
        return this.getCurrentBoard().getCurrentPlayer();
    }

    private Player setCurrentPlayer(Player player) {
        return player;
    }

    private Player getOpponentPlayer() {
        return this.getCurrentBoard().getCurrentPlayer();
    }
}
