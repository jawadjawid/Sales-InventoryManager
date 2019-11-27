package com.Application.Controller.Customer;

import android.content.Context;
import android.view.View;

import com.Application.View.Customer.CustomerCartView;
import com.example.Application.R;


public class CustomerCartController implements View.OnClickListener {

    private CustomerCartView view;
    private Context appContext;

    public CustomerCartController(Context context) {
        appContext = context;
        view = (CustomerCartView) appContext;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.checkOutBtn){

        }
    }
}
