package com.Application.View.Employee.authenticateNewEmployee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthenticateNewEmployeeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AuthenticateNewEmployeeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}