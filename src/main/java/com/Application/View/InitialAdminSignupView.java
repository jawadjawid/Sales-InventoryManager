package com.Application.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.Application.Controller.InitialAdminSignupController;
import com.example.Application.R;

public class InitialAdminSignupView extends AppCompatActivity {

    InitialAdminSignupController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_admin_signup);
        controller = new InitialAdminSignupController(this);

        Log.d("hehe","haha im heare");
        Button createNewAdminButton = findViewById(R.id.createNewAdminButton);
        createNewAdminButton.setOnClickListener(controller);
    }

    @Override
    public void onBackPressed() { }
}
