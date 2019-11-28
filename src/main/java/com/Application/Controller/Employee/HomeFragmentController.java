package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.Application.View.Employee.HomeFragmentView;
import com.Application.View.MainLoginView;


public class HomeFragmentController implements View.OnClickListener {
    private View view;
    private Context appContext;
    private HomeFragmentView homeFragmentView;

    public HomeFragmentController(View view, HomeFragmentView homeFragmentView) {
        this.view = view;
        appContext = view.getContext();
        this.homeFragmentView = homeFragmentView;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.appContext, MainLoginView.class);
        appContext.startActivity(intent);
    }




}
