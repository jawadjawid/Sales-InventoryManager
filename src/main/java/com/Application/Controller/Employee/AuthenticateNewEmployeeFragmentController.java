package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.Controller.LoginController;
import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Employee.EmployeeOptionsView;
import com.example.Application.R;
import com.google.android.material.navigation.NavigationView;


public class AuthenticateNewEmployeeFragmentController extends LoginController implements View.OnClickListener {
    private View view;
    private Context appContext;

    public AuthenticateNewEmployeeFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
    }

    @Override
    public void onClick(View v) {
        // check if login works
        // if it does, change the name in the header view
        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);

        User user = loginUser(view, mydb);
        if(user != null && user.getRoleId() == 2){
            // if employee login is successful

            EmployeeOptionsController.setUser(user);
            EmployeeOptionsController.setUsername(user.getName());
            HomeFragmentController.setUsername(user.getName());
            Toast.makeText(appContext, "Account successfully authenticated", Toast.LENGTH_SHORT).show();
        }

    }




}
