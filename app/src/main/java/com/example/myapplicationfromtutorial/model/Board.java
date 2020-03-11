package com.example.myapplicationfromtutorial.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Player currentPlayer;
    private Player opponentPlayer;
    private ArrayList<CharacterOnBoard> charactersOnBoard;

    public Board(Player currentPlayer, Player opponentPlayer, ArrayList<CharacterOnBoard> charactersOnBoard) {
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
        this.charactersOnBoard = charactersOnBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public ArrayList<CharacterOnBoard> getCharactersOnBoard() {
        return charactersOnBoard;
    }

    public void setCharactersOnBoard(ArrayList<CharacterOnBoard> charactersOnBoard) {
        this.charactersOnBoard = charactersOnBoard;
    }

}
