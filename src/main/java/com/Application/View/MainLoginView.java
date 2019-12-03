package com.Application.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.Application.Controller.MainLoginController;
import com.example.Application.R;

public class MainLoginView extends AppCompatActivity {

    private MainLoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        controller = new MainLoginController(this);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(controller);
    }
}
