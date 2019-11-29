package com.Application.View.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.Application.Controller.Customer.CustomerShoppingController;
import com.example.Application.R;

public class CustomerShoppingView extends AppCompatActivity {

    private CustomerShoppingController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_shopping);
        controller = new CustomerShoppingController(this);

        Button addFishingRod = findViewById(R.id.addRodBtn);
        Button addHockeyStick =  findViewById(R.id.addStickBtn);
        Button addSkates = findViewById(R.id.addSkatesBtn);
        Button addRunningShoes = findViewById(R.id.addShoesBtn);
        Button addBar =  findViewById(R.id.addBarBtn);
        Button goToCartBtn = findViewById(R.id.addCartBtn);

        addFishingRod.setOnClickListener(controller);
        addHockeyStick.setOnClickListener(controller);
        addSkates.setOnClickListener(controller);
        addRunningShoes.setOnClickListener(controller);
        addBar.setOnClickListener(controller);
        goToCartBtn.setOnClickListener(controller);
    }
}
