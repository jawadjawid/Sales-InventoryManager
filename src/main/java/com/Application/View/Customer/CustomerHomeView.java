package com.Application.View.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.Application.Controller.Customer.CustomerHomeController;
import com.example.Application.R;

public class CustomerHomeView extends AppCompatActivity {

    private CustomerHomeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        controller = new CustomerHomeController(this);
        Button shopButton = findViewById(R.id.shopBtn);
        Button cartButton =  findViewById(R.id.cartBtn);
        Button logoutButton = findViewById(R.id.LogOutBtn);
        shopButton.setOnClickListener(controller);
        cartButton.setOnClickListener(controller);
        logoutButton.setOnClickListener(controller);
    }
}
