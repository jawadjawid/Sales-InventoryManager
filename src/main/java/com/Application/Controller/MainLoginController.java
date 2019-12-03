package com.Application.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.users.User;
import com.Application.View.Admin.AdminOptionsView;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Employee.EmployeeOptionsView;
import com.Application.View.MainLoginView;
import com.example.Application.R;

public class MainLoginController extends LoginController implements View.OnClickListener {
    private MainLoginView view;
    private Context appContext;

    public MainLoginController(Context context) {
        appContext = context;
        view = (MainLoginView) appContext;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(view);

            User user = loginUser(view, mydb);
            int roleId = (user == null) ? 0 : user.getRoleId();
            Intent intent;
            switch (roleId) {
                case 1:
                    // admin login
                    intent = new Intent(this.appContext, AdminOptionsView.class);
                    intent.putExtra("user", user);
                    appContext.startActivity(intent);
                    break;
                case 2:
                    // employee login
                    intent = new Intent(this.appContext, EmployeeOptionsView.class);
                    intent.putExtra("user", user);
                    appContext.startActivity(intent);
                    break;
                case 3:
                    // customer login
                    intent = new Intent(this.appContext, CustomerHomeView.class);
                    intent.putExtra("user", user);
                    appContext.startActivity(intent);
                    break;
                default:
                    // login unsuccessful
                    Toast.makeText(appContext, INVALID_LOGIN_MESSAGE, Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    }


}
