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

        Button addFishingRod = findViewById(R.id.RodAddBtn);
        Button addHockeyStick = findViewById(R.id.StickAddBtn);
        Button addSkates = findViewById(R.id.SkatesAddBtn);
        Button addRunningShoes = findViewById(R.id.ShoesAddBtn);
        Button addBar = findViewById(R.id.BarAddBtn);

        Button removeFishingRod = findViewById(R.id.RodRemoveBtn);
        Button removeStick = findViewById(R.id.StickRemoveBtn);
        Button removeSkates = findViewById(R.id.SkatesRemoveBtn);
        Button removeShoes = findViewById(R.id.ShoesRemoveBtn);
        Button removeBar = findViewById(R.id.BarRemoveBtn);
        Button goToHomeBtn = findViewById(R.id.GoHomeBtn);
        Button resetAllBtn = findViewById(R.id.ResetAllBtn);

        addFishingRod.setOnClickListener(controller);
        addHockeyStick.setOnClickListener(controller);
        addSkates.setOnClickListener(controller);
        addRunningShoes.setOnClickListener(controller);
        addBar.setOnClickListener(controller);
        removeFishingRod.setOnClickListener(controller);
        removeStick.setOnClickListener(controller);
        removeSkates.setOnClickListener(controller);
        removeShoes.setOnClickListener(controller);
        removeBar.setOnClickListener(controller);
        goToHomeBtn.setOnClickListener(controller);
        resetAllBtn.setOnClickListener(controller);
    }
}
