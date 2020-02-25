package com.example.myapplicationfromtutorial.service;

import java.util.List;

public class Board {
    private Player currentPlayer;
    private Player opponentPlayer;
    private List<Character> characters;
    private List<Character> displayedCharacters; // or hiddenCharacters?

    public Board(Player currentPlayer, Player opponentPlayer, List<Character> characters, List<Character> displayedCharacters) {
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
        this.characters = characters;
        this.displayedCharacters = displayedCharacters;
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

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Character> getDisplayedCharacters() {
        return displayedCharacters;
    }

    public void setDisplayedCharacters(List<Character> displayedCharacters) {
        this.displayedCharacters = displayedCharacters;
    }
}
