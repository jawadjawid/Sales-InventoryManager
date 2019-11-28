package com.Application.Controller.Customer;

import android.content.Context;
import android.view.View;

import com.Application.View.Customer.CustomerShoppingView;
import com.example.Application.R;

public class CustomerShoppingController implements View.OnClickListener {
    private CustomerShoppingView view;
    private Context appContext;

    public CustomerShoppingController(Context context) {
        appContext = context;
        view = (CustomerShoppingView) appContext;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addRodBtn) {


        } else if (v.getId() == R.id.addCartBtn) {

        } else if (v.getId() == R.id.addStickBtn) {

        } else if (v.getId() == R.id.addSkatesBtn) {

        } else if (v.getId() == R.id.addBarBtn) {

        } else if (v.getId() == R.id.addShoesBtn) {

        }
    }
}
