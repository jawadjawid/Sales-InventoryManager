package com.Application.View.Employee.makeNewEmployee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MakeNewEmployeeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MakeNewEmployeeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}