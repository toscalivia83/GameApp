package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import com.example.myapplicationfromtutorial.CharactersBoardFragment;
import com.example.myapplicationfromtutorial.model.Character;
import com.example.myapplicationfromtutorial.model.CharacterOnBoard;
import com.example.myapplicationfromtutorial.R;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sqlite.database.DatabaseHelper;

public class BoardActivity extends AppCompatActivity {
    private DatabaseHelper db;

    CharactersBoardFragment player1CharactersBoardFragment, player2CharactersBoardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            player1CharactersBoardFragment = new CharactersBoardFragment();

            Bundle args = new Bundle();

            db = new DatabaseHelper(this);

            List<Character> characters = db.getAllCharacters();
            List<CharacterOnBoard> characterOnBoardList = characters.stream().map(c -> new CharacterOnBoard(
                    c.getId(),
                    c.getImgUrl(),
                    c.getCharacteristics()
            )).collect(Collectors.toList());

            args.putParcelableArrayList("characterOnBoardList", new ArrayList<>(characterOnBoardList));
            player1CharactersBoardFragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, player1CharactersBoardFragment)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

//    private void displayCurrentPlayerNameInTextView(String name) {
//        TextView tv = findViewById(R.id.textView);
//        tv.setText(name);
//    }

    public void switchBoard(View view) {
        Fragment exampleFragmentTest = getSupportFragmentManager()
                .getFragments()
                .get(0);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (exampleFragmentTest instanceof CharactersBoardFragment && exampleFragmentTest.isVisible()){
            player1CharactersBoardFragment = (CharactersBoardFragment) exampleFragmentTest;
            if (player2CharactersBoardFragment == null) {
                player2CharactersBoardFragment = new CharactersBoardFragment();

                Bundle args = new Bundle();

                db = new DatabaseHelper(this);
                List<Character> characters = db.getAllCharacters();
                List<CharacterOnBoard> characterOnBoardList = characters.stream().map(c -> new CharacterOnBoard(
                        c.getId(),
                        c.getImgUrl(),
                        c.getCharacteristics()
                )).collect(Collectors.toList());

                args.putParcelableArrayList("characterOnBoardList", new ArrayList<>(characterOnBoardList));
                player2CharactersBoardFragment.setArguments(args);

                transaction.add(R.id.fragment_container, player2CharactersBoardFragment);
            }

            transaction.hide(player1CharactersBoardFragment);
            transaction.show(player2CharactersBoardFragment);
        } else {
            player2CharactersBoardFragment = (CharactersBoardFragment) getSupportFragmentManager()
                    .getFragments()
                    .get(1);
            transaction.hide(player2CharactersBoardFragment);
            transaction.show(player1CharactersBoardFragment);
        }
        transaction.commit();
    }
}
