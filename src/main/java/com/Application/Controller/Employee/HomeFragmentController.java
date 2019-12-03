package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.Application.View.Employee.HomeFragmentView;
import com.Application.View.MainLoginView;
import com.example.Application.R;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;


public class HomeFragmentController implements View.OnClickListener {
    private static View view;
    private Context appContext;
    private static TextView welcomeTextView;

    public HomeFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
        welcomeTextView = view.findViewById(R.id.welcomeTextView);
        setUsername(EmployeeOptionsController.getUser().getName());
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.appContext, MainLoginView.class);
        appContext.startActivity(intent);
    }

    public static void setUsername(String username) {
        welcomeTextView.setText("Welcome " + username + "!");
    }




}
