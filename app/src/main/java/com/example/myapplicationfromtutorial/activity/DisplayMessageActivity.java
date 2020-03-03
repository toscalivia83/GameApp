package com.example.myapplicationfromtutorial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplicationfromtutorial.service.PlayerService;
import com.example.myapplicationfromtutorial.R;
import com.example.myapplicationfromtutorial.model.Player;

public class DisplayMessageActivity extends AppCompatActivity {
    PlayerService playerService;
    boolean mBound = false;
    Player player1;
    Player player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        Toast.makeText(DisplayMessageActivity.this, "Service Binded", Toast.LENGTH_LONG).show();
    }

    public void letsGo(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("Player1", player1);
        intent.putExtra("Player2", player2);

        startActivity(intent);
    }

    // TODO: extract this in a file!
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            PlayerService.PlayerServiceBinder binder = (PlayerService.PlayerServiceBinder) service;
            playerService = binder.getService();
            player1 = playerService.getPlayer();
            int drawableId = getResources().getIdentifier(
                    player1.getSelectedCharacter().getImgUrl(),
                    "drawable",
                    "com.example.myapplicationfromtutorial"
            );
            ImageView iv = findViewById(R.id.imageView2);
            iv.setImageDrawable(getResources().getDrawable(drawableId));
            //TODO" need to initialize player2 with a random character as well
//            Player player2 = playerService.initializePlayerWithCharacter("Robin");
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
        Toast.makeText(DisplayMessageActivity.this, "Service Un-Binded", Toast.LENGTH_LONG).show();
    };
}
