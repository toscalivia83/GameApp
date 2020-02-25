package com.example.myapplicationfromtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BoardService boardService;
    boolean mBound = false;

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


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoardService.BoardServiceBinder binder = (BoardService.BoardServiceBinder) service;
            boardService = binder.getService();
            displayCurrentPlayerNameInTextView();
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

    private void displayCurrentPlayerNameInTextView() {
        TextView tv = findViewById(R.id.textView);
        boardService.displayCurrentPlayerNameInTextView(tv);
    }

}
