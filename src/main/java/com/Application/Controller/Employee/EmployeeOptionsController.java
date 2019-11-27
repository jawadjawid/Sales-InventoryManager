package com.Application.Controller.Employee;

import android.content.Context;
import android.view.View;

import com.Application.View.Employee.EmployeeOptionsView;


public class EmployeeOptionsController implements View.OnClickListener {
    private EmployeeOptionsView view;
    private Context appContext;

    public EmployeeOptionsController(Context context) {
        appContext = context;
        view = (EmployeeOptionsView) appContext;
    }

    @Override
    public void onClick(View v) {

    }


}
