package com.example.myapplicationfromtutorial.service;

import java.util.ArrayList;

public class CharacterOnBoard extends Character {
    private boolean isHidden;

    public CharacterOnBoard(String imgUrl, String characteristics, boolean isHidden) {
        super(imgUrl, characteristics);
        this.isHidden = isHidden;
    }

    public CharacterOnBoard(int id, String imgUrl, String characteristics, boolean isHidden) {
        super(id, imgUrl, characteristics);
        this.isHidden = isHidden;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
