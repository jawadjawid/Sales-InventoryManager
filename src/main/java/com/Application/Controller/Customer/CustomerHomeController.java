package com.Application.Controller.Customer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.Application.Model.users.Employee;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Customer.CustomerShoppingView;
import com.Application.View.Employee.MainActivity;
import com.Application.View.LoginView;
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
            Intent intent = new Intent(appContext, CustomerShoppingView.class);
            view.startActivity(intent);
        }
        else if(v.getId() == R.id.cartBtn){

        }
        else if(v.getId() == R.id.LogOutBtn){
            Intent intent = new Intent(appContext, LoginView.class);
            view.startActivity(intent);
        }
    }
}
