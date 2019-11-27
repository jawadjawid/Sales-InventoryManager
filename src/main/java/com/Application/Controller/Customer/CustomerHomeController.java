package com.Application.Controller.Customer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Employee.MainActivity;
import com.example.Application.R;

public class CustomerHomeController implements View.OnClickListener{
    private CustomerHomeView view;
    private Context appContext;

    public CustomerHomeController(Context context) {
        appContext = context;
        view = (CustomerHomeView) appContext;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.shopBtn){

        }
    }
}
