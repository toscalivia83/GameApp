package com.example.myapplicationfromtutorial.model;

public class CharacterOnBoard {
    int id;
    String imgUrl;
    String characteristics;
    boolean isHidden;

    public CharacterOnBoard(int id, String imgUrl, String characteristics) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.characteristics = characteristics;
        this.isHidden = false;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
