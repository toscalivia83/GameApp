package com.example.myapplicationfromtutorial.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable {
    private Integer id;
    private String name;
    private Character selectedCharacter;

    public Player(String name, Character selectedCharacter) {
        this.name = name;
        this.selectedCharacter = selectedCharacter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}
