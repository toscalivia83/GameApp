package com.example.myapplicationfromtutorial;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

import com.example.myapplicationfromtutorial.service.Board;
import com.example.myapplicationfromtutorial.service.Character;
import com.example.myapplicationfromtutorial.service.CharacterOnBoard;
import com.example.myapplicationfromtutorial.service.Player;

import java.util.List;
import java.util.stream.Collectors;

import sqlite.database.DatabaseHelper;

/**
 * BoardService which sets all the useful fields so that the board is displayed correctly
 */
public class BoardService extends Service {

    private DatabaseHelper db;

    private Board currentBoard;
    private Board opponentBoard;
    private Player player1;
    private Player player2;

    // Binder given to clients
    private final IBinder binder = new BoardServiceBinder();

    public class BoardServiceBinder extends Binder {
        BoardService getService() {
            return BoardService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        setCurrentBoard(createPlayerBoard(getPlayer1FromIntent(intent), getPlayer2FromIntent(intent)));
        setOpponentBoard(createPlayerBoard(getPlayer2FromIntent(intent), getPlayer1FromIntent(intent)));
        setPlayer1(getPlayer1FromIntent(intent));
        setPlayer2(getPlayer2FromIntent(intent));
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

    public Board createPlayerBoard(Player currentPlayer, Player opponentPlayer) {
        return new Board(
                currentPlayer,
                opponentPlayer,
                createCharactersOnBoard()
        );
    }

    private List<CharacterOnBoard> createCharactersOnBoard() {
        db = new DatabaseHelper(this);
        List<Character> characters = db.getAllCharacters();
        return characters.stream().map(c -> new CharacterOnBoard(
                c.getId(),
                c.getImgUrl(),
                c.getCharacteristics()
        )).collect(Collectors.toList());
    }

    public Board switchBoard() {
        Board temp = getCurrentBoard();
        setCurrentBoard(getOpponentBoard());
        setOpponentBoard(temp);
        return getCurrentBoard();
    }

    public void displayCurrentPlayerNameInTextView(TextView textView) {
        textView.setText(getCurrentBoard().getCurrentPlayer().getName());
    }

    private Player getPlayer1FromIntent(Intent intent) {
        return (Player)intent.getSerializableExtra("Player1");
    }

    private Player getPlayer2FromIntent(Intent intent) {
        return (Player)intent.getSerializableExtra("Player2");
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
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
