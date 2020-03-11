package com.example.myapplicationfromtutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CharacterOnBoard implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imgUrl);
        dest.writeString(this.characteristics);
//        dest.writeBoolean(this.isHidden);
    }
}
