package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import com.example.myapplicationfromtutorial.ExampleFragment;
import com.example.myapplicationfromtutorial.OtherThingFragment;
import com.example.myapplicationfromtutorial.model.Character;
import com.example.myapplicationfromtutorial.model.CharacterOnBoard;
import com.example.myapplicationfromtutorial.service.BoardService;
import com.example.myapplicationfromtutorial.R;
import com.example.myapplicationfromtutorial.model.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sqlite.database.DatabaseHelper;

public class BoardActivity extends AppCompatActivity {
    private DatabaseHelper db;

    OtherThingFragment otherThingFragment;
    ExampleFragment exampleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            exampleFragment = new ExampleFragment();

            Bundle args = new Bundle();

            db = new DatabaseHelper(this);

            List<Character> characters = db.getAllCharacters();
            List<CharacterOnBoard> characterOnBoardList = characters.stream().map(c -> new CharacterOnBoard(
                    c.getId(),
                    c.getImgUrl(),
                    c.getCharacteristics()
            )).collect(Collectors.toList());

            args.putParcelableArrayList("characterOnBoardList", new ArrayList<>(characterOnBoardList));
            exampleFragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, exampleFragment)
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
        if (exampleFragmentTest instanceof ExampleFragment && exampleFragmentTest.isVisible()){
            exampleFragment = (ExampleFragment) exampleFragmentTest;
            if (otherThingFragment == null) {
                otherThingFragment = new OtherThingFragment();

                Bundle args = new Bundle();

                db = new DatabaseHelper(this);
                List<Character> characters = db.getAllCharacters();
                List<CharacterOnBoard> characterOnBoardList = characters.stream().map(c -> new CharacterOnBoard(
                        c.getId(),
                        c.getImgUrl(),
                        c.getCharacteristics()
                )).collect(Collectors.toList());

                args.putParcelableArrayList("characterOnBoardList", new ArrayList<>(characterOnBoardList));
                otherThingFragment.setArguments(args);

                transaction.add(R.id.fragment_container, otherThingFragment);
            }

            transaction.hide(exampleFragment);
            transaction.show(otherThingFragment);
        } else {
            otherThingFragment = (OtherThingFragment) getSupportFragmentManager()
                    .getFragments()
                    .get(1);
            transaction.hide(otherThingFragment);
            transaction.show(exampleFragment);
        }
        transaction.commit();
    }
}
