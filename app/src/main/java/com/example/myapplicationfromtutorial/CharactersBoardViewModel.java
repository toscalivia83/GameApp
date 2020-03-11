package com.example.myapplicationfromtutorial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationfromtutorial.model.CharacterOnBoard;

import java.util.List;

public class CharactersBoardViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<CharacterOnBoard>> characterListOnBoard;

    public LiveData<List<CharacterOnBoard>> getCharacterListOnBoard() {
//        if (users == null) {
//            users = new MutableLiveData<List<User>>();
//            loadUsers();
//        }
        return characterListOnBoard;
    }

    public void setCharacterListOnBoard(List<CharacterOnBoard> characterListOnBoard) {
        this.characterListOnBoard.setValue(characterListOnBoard);
    }
}
