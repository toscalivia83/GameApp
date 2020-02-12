package com.example.myapplicationfromtutorial.service;

import java.util.List;

public class Board {
    private String currentPlayerName;
    private String opponentPlayerName;
    private List<Character> characters;
    private List<Character> displayedCharacters; // or hiddenCharacters?

    public Board(String currentPlayerName, String opponentPlayerName, List<Character> characters, List<Character> displayedCharacters) {
        this.currentPlayerName = currentPlayerName;
        this.opponentPlayerName = opponentPlayerName;
        this.characters = characters;
        this.displayedCharacters = displayedCharacters;
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
    }

    public String getOpponentPlayerName() {
        return opponentPlayerName;
    }

    public void setOpponentPlayerName(String opponentPlayerName) {
        this.opponentPlayerName = opponentPlayerName;
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
