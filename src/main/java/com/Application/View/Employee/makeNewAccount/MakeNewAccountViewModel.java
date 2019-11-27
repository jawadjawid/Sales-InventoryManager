package com.Application.View.Employee.makeNewAccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MakeNewAccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MakeNewAccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}