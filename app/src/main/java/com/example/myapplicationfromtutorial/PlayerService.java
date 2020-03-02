package com.example.myapplicationfromtutorial;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.myapplicationfromtutorial.service.Character;
import com.example.myapplicationfromtutorial.service.Player;

import java.util.List;

import sqlite.database.DatabaseHelper;

public class PlayerService extends Service {

    private DatabaseHelper db;
    Player player;

    // Binder given to clients
    private final IBinder binder = new PlayerService.PlayerServiceBinder();

    public class PlayerServiceBinder extends Binder {
        PlayerService getService() {
            return PlayerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        db = new DatabaseHelper(this);
        initializePlayerWithCharacter("Marion");
        // set the player name automatically for now
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public Player initializePlayerWithCharacter(String playerName) {
        List<Character> characters = db.getAllCharacters();
        int randomNumber = (int) Math.round(Math.random() * characters.size());
        Character characterChosen = characters.get(randomNumber);
        player = new Player(playerName, new Character(characterChosen.getId(), characterChosen.getImgUrl(), characterChosen.getCharacteristics()));
        return player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
