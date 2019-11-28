package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.Application.Model.users.User;
import com.Application.View.Employee.EmployeeOptionsView;
import com.Application.View.Employee.HomeFragmentView;
import com.Application.View.LoginView;
import com.example.Application.R;
import com.google.android.material.navigation.NavigationView;


public class HomeFragmentController implements View.OnClickListener {
    private View view;
    private Context appContext;
    private HomeFragmentView homeFragmentView;

    public HomeFragmentController(View view, HomeFragmentView homeFragmentView) {
        this.view = view;
        appContext = view.getContext();
        this.homeFragmentView = homeFragmentView;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.appContext, LoginView.class);
        appContext.startActivity(intent);
    }




}
