package com.Application.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.Application.Controller.InitialEmployeeSignupController;
import com.example.Application.R;

public class InitialEmployeeSignupView extends AppCompatActivity {

  InitialEmployeeSignupController controller;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initial_employee_signup);
    controller = new InitialEmployeeSignupController(this);

    Log.d("hehe", "haha im heare");
    Button createNewEmployeeButton = findViewById(R.id.createNewEmployeeButton);
    createNewEmployeeButton.setOnClickListener(controller);
  }
}
