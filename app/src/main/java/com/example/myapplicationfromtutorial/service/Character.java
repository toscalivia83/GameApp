package com.example.myapplicationfromtutorial.service;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    private Integer id;
    private String imgUrl;
    private ArrayList<String> characteristics;

    public Character(String imgUrl, ArrayList<String> characteristics) {
        this.imgUrl = imgUrl;
        this.characteristics = characteristics;
    }


    public Character(int id, String imgUrl, ArrayList<String> characteristics) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.characteristics = characteristics;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(ArrayList<String> characteristics) {
        this.characteristics = characteristics;
    }
}
