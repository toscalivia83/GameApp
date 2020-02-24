package com.example.myapplicationfromtutorial.service;

import java.io.Serializable;
import java.util.List;

/**
 * Board class with details of what the user see
 *  * @param  id   board id to differentiate them
 *  * @param  currentPlayer
 *  * @param  opponentPlayer
 *  * @param  charactersOnBoard
 */
public class Board implements Serializable {
    private Integer id;
    private Player currentPlayer;
    private Player opponentPlayer;// needed? maybe to keep track of it!
    private List<CharacterOnBoard> charactersOnBoard;// should it be a type CharactersOnBoard with initialized charactersOnBoard states?

    public Board(Integer id, Player currentPlayer, Player opponentPlayer, List<CharacterOnBoard> charactersOnBoard) {
        this.id = id;
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = opponentPlayer;
        this.charactersOnBoard = charactersOnBoard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
