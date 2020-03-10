package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplicationfromtutorial.ExampleFragment;
import com.example.myapplicationfromtutorial.OtherThingFragment;
import com.example.myapplicationfromtutorial.service.BoardService;
import com.example.myapplicationfromtutorial.R;
import com.example.myapplicationfromtutorial.model.Board;

import java.util.List;

public class BoardActivity extends AppCompatActivity {
    BoardService boardService;

    Board player1Board;
    Board player2Board;

    OtherThingFragment otherThingFragment;
    ExampleFragment exampleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        Intent previousIntent = getIntent();

        Intent intent = new Intent(this, BoardService.class);
        intent.putExtra("Player1", previousIntent.getSerializableExtra("Player1"));
        intent.putExtra("Player2", previousIntent.getSerializableExtra("Player2"));

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            exampleFragment = new ExampleFragment();

            //firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, exampleFragment)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        System.out.println("Hey!!");
        super.onActivityResult(requestCode, resultCode, intent);

    }

    private void displayCurrentPlayerNameInTextView(String name) {
        TextView tv = findViewById(R.id.textView);
        tv.setText(name);
    }

    public void switchBoard(View view) {
        Fragment exampleFragmentTest = getSupportFragmentManager()
                .getFragments()
                .get(0);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (exampleFragmentTest instanceof ExampleFragment){
            exampleFragment = (ExampleFragment) exampleFragmentTest;
            if (otherThingFragment == null) {
                otherThingFragment = new OtherThingFragment();
            }
            transaction.replace(R.id.fragment_container, otherThingFragment);
//            transaction.addToBackStack(null);
        } else {
            otherThingFragment = (OtherThingFragment) exampleFragmentTest;
            transaction.replace(R.id.fragment_container, exampleFragment);
        }
        transaction.commit();
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
