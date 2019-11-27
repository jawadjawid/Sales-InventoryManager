package com.Application.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.Application.Controller.LoginController;
import com.example.Application.R;

public class LoginView extends AppCompatActivity {

    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylogin);

        controller = new LoginController(this);
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        loginButton.setOnClickListener(controller);
        signUpButton.setOnClickListener(controller);
    }
}
