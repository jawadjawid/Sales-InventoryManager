package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Application.Model.users.User;
import com.Application.View.Employee.EmployeeOptionsView;
import com.Application.View.Employee.HomeFragmentView;
import com.example.Application.R;
import com.google.android.material.navigation.NavigationView;


public class EmployeeOptionsController implements View.OnClickListener {
    private EmployeeOptionsView view;
    private static Context appContext;

    public EmployeeOptionsController(Context context) {
        appContext = context;
        view = (EmployeeOptionsView) appContext;
        setUsername();
    }

    @Override
    public void onClick(View v) {
    }

    private void setUsername() {
        NavigationView navigationView = view.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        Intent intent = view.getIntent();
        User user = (User) intent.getSerializableExtra("user");
        TextView usernameTextView = headerView.findViewById(R.id.usernameTextView);
        usernameTextView.setText(user.getName());
    }

    public static Context getContext(){
        return appContext;
    }


}
