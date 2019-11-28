package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.Application.Controller.LoginController;


public class AuthenticateNewEmployeeFragmentController extends LoginController implements View.OnClickListener {
    private View view;

    public AuthenticateNewEmployeeFragmentController(View view) {
        this.view = view;
    }

    @Override
    public void onClick(View v) {
        // check if login works
        // if it does, change the name in the header view


    }




}
