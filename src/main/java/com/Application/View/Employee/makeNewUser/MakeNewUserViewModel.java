package com.Application.View.Employee.makeNewUser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MakeNewUserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MakeNewUserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}