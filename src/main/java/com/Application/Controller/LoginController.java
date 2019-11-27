package com.Application.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.Employee;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Employee.EmployeeOptionsView;
import com.Application.View.Employee.MainActivity;
import com.Application.View.LoginView;
import com.example.Application.R;

import java.sql.SQLException;

public class LoginController implements View.OnClickListener {
    private LoginView view;
    private Context appContext;

    public LoginController(Context context) {
        appContext = context;
        view = (LoginView) appContext;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            try {
                EditText userIdEditText = view.findViewById(R.id.usernameEditText);
                int userId = Integer.parseInt(userIdEditText.getText().toString());
                User user = DatabaseSelectHelper.getUserDetails(userId);

                EditText passwordEditText = view.findViewById(R.id.passwordEditText);
                String password = passwordEditText.getText().toString();

                if (user.authenticate(password)) {
                    Intent intent = new Intent(this.appContext, MainActivity.class);
                    intent.putExtra("user", user);
                    appContext.startActivity(intent);
                }

            } catch (SQLException e) {
                Log.d("E","EHHEHEH");
                new AlertDialog.Builder(appContext).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Database Error!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            } catch (NullPointerException | NumberFormatException e) {
                Toast.makeText(appContext, "Please Enter appropriate login information!", Toast.LENGTH_SHORT).show();
                                User user = new Employee(1,"gang",35,"dfsdfsd");
                                Intent intent = new Intent(appContext, CustomerHomeView.class);
                                intent.putExtra("user", user);
                                view.startActivity(intent);
            }
        }
    }


}
