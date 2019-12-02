package com.Application.Controller.Employee;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.Application.Controller.LoginController;
import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.users.User;


public class AuthenticateNewEmployeeFragmentController extends LoginController implements View.OnClickListener {
    private View view;
    private Context appContext;

    public AuthenticateNewEmployeeFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
    }

    @Override
    public void onClick(View v) {
        // check if login works
        // if it does, change the name in the header view
        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);

        User user = loginUser(view, mydb);
        if (user != null && user.getRoleId() == SalesApplication.EMPLOYEE_ID) {
            // if employee login is successful
            EmployeeOptionsController.setUser(user);
            EmployeeOptionsController.setUsername(user.getName());
           HomeFragmentController.setUsername(user.getName());
            Toast.makeText(appContext, "Account Successfully Authenticated.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(appContext, INVALID_LOGIN_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }
}
