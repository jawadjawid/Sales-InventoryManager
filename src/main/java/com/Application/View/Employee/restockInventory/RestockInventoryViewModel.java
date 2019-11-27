package com.Application.View.Employee.restockInventory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RestockInventoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RestockInventoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}