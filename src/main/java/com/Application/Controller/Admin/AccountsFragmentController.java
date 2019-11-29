package com.Application.Controller.Admin;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountsFragmentController implements View.OnClickListener {

    private View view;
    private Context appContext;

    public AccountsFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
    }

    @Override
    public void onClick(View view) {

    }
}