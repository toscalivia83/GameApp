package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationfromtutorial.service.BoardService;
import com.example.myapplicationfromtutorial.R;
import com.example.myapplicationfromtutorial.model.Board;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    BoardService boardService;
    boolean mBound = false;

    Board player1Board;
    Board player2Board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        Intent previousIntent = getIntent();

        Intent intent = new Intent(this, BoardService.class);
        intent.putExtra("Player1", previousIntent.getSerializableExtra("Player1"));
        intent.putExtra("Player2", previousIntent.getSerializableExtra("Player2"));

        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        Toast.makeText(MainActivity.this, "Service Binded", Toast.LENGTH_LONG).show();
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoardService.BoardServiceBinder binder = (BoardService.BoardServiceBinder) service;
            boardService = binder.getService();
            displayCurrentPlayerNameInTextView(boardService.getCurrentBoard().getCurrentPlayer().getName());
            List<String> imageUrlCharacterList = boardService.getCurrentBoard().getCharactersOnBoard().stream().map(c -> c.getImgUrl()).collect(Collectors.toList());
            createImageViewForEachCharacter(imageUrlCharacterList, R.id.constraintLayout);
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
            constraintLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
            List<String> opponentBoardImageUrlCharacterList = boardService.getOpponentBoard().getCharactersOnBoard().stream().map(c -> c.getImgUrl()).collect(Collectors.toList());
            createImageViewForEachCharacter(opponentBoardImageUrlCharacterList, R.id.constraintLayout2);
            ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout2);
            constraintLayout2.setBackgroundColor(Color.parseColor("#808000"));
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        Toast.makeText(MainActivity.this, "Service Un-Binded", Toast.LENGTH_LONG).show();
    };

    private void displayCurrentPlayerNameInTextView(String name) {
        TextView tv = findViewById(R.id.textView);
        tv.setText(name);
    }

    public void switchBoard(View view) {
        boardService.switchBoard();
        displayCurrentPlayerNameInTextView(boardService.getCurrentBoard().getCurrentPlayer().getName());
        if (isCurrentBoardPlayer1()) {
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
            constraintLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
            ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout2);
            constraintLayout2.setBackgroundColor(Color.parseColor("#808000"));
        } else {
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
            constraintLayout.setBackgroundColor(Color.parseColor("#808000"));
            ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout2);
            constraintLayout2.setBackgroundColor(Color.parseColor("#FFFF00"));
        }
    }

    private void createImageViewForEachCharacter(List<String> imageUrlCharacterList, int constraintLayoutId) {
        ConstraintLayout constraintLayout = findViewById(constraintLayoutId);

        int imageIds[] = new int[imageUrlCharacterList.size()];

        for(int i = 0; i < imageUrlCharacterList.size(); i++) {
            int drawableId = getResources().getIdentifier(
                    imageUrlCharacterList.get(i),
                    "drawable",
                    "com.example.myapplicationfromtutorial"
            );

            ImageView imageView = createCharacterImageView(drawableId, i);

            imageIds[i] = imageView.getId();
            constraintLayout.addView(imageView);
        };

        addConstraintsToImageViews(constraintLayout, imageIds, imageUrlCharacterList.size());
    }

    private ImageView createCharacterImageView(int characterDrawableImage, int index) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
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

    public Board getPlayer1Board() {
        return player1Board;
    }

    public void setPlayer1Board(Board player1Board) {
        this.player1Board = player1Board;
    }

    public Board getPlayer2Board() {
        return player2Board;
    }

    public void setPlayer2Board(Board player2Board) {
        this.player2Board = player2Board;
    }

    private boolean isCurrentBoardPlayer1() {
        return boardService.getCurrentBoard().getCurrentPlayer().getName() == boardService.getPlayer1().getName();
    }
}
