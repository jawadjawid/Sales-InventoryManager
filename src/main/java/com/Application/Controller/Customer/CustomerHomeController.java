package com.Application.Controller.Customer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerCartView;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Customer.CustomerShoppingView;
import com.Application.View.MainLoginView;
import com.example.Application.R;

public class CustomerHomeController implements View.OnClickListener{
    private CustomerHomeView view;
    private Context appContext;

    public CustomerHomeController(Context context) {
        appContext = context;
        view = (CustomerHomeView) appContext;
        setUserName();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.shopBtn){
            Intent intent = new Intent(appContext, CustomerShoppingView.class);
            User user = (User) intent.getSerializableExtra("user");
            intent.putExtra("user", user);
            view.startActivity(intent);
        }
        else if(v.getId() == R.id.cartBtn){
            Intent intent = new Intent(appContext, CustomerCartView.class);
            view.startActivity(intent);
        }
        else if(v.getId() == R.id.LogOutBtn){
            Intent intent = new Intent(appContext, MainLoginView.class);
            view.startActivity(intent);
        }
    }

    public void setUserName(){
        Intent intent = view.getIntent();
        User user = (User) intent.getSerializableExtra("user");
        TextView usernameTextView = view.findViewById(R.id.customerName);
        usernameTextView.setText(user.getName());
    }
}
