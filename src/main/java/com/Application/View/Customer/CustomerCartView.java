package com.Application.View.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.Application.Controller.Customer.CustomerCartController;
import com.example.Application.R;

public class CustomerCartView extends AppCompatActivity {

    private CustomerCartController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);
        controller = new CustomerCartController(this);
        Button checkOutBtn = findViewById(R.id.checkOutBtn);
        checkOutBtn.setOnClickListener(controller);
        Button saveCartBtn = findViewById(R.id.saveCartBtn);
        saveCartBtn.setOnClickListener(controller);
    }
}
