package com.example.myapplicationfromtutorial.service;

public class Question {
    private int id;
    private String sentence;
    private String characteristics;

    public Question(int id, String sentence, String characteristics) {
        this.id = id;
        this.sentence = sentence;
        this.characteristics = characteristics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
