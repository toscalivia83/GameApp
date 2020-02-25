package com.example.myapplicationfromtutorial.service;

import java.util.List;

public class Board {
    private Player currentPlayer;
    private Player opponentPlayer;
    private List<CharacterOnBoard> charactersOnBoard;

    public Board(Player currentPlayer, Player opponentPlayer, List<CharacterOnBoard> charactersOnBoard) {
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

    public List<CharacterOnBoard> getCharactersOnBoard() {
        return charactersOnBoard;
    }

    public void setCharactersOnBoard(List<CharacterOnBoard> charactersOnBoard) {
        this.charactersOnBoard = charactersOnBoard;
    }

}
