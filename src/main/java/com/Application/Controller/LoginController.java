package com.Application.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
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
                DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(view);

                EditText userIdEditText = view.findViewById(R.id.usernameEditText);
                int userId = Integer.parseInt(userIdEditText.getText().toString());
                User user = mydb.getUserDetailsH(userId);

                EditText passwordEditText = view.findViewById(R.id.passwordEditText);
                String password = passwordEditText.getText().toString();
                String dbPass = mydb.getPasswordH(userId);

                Log.d("hehe", "abt to authentiate pass");
                if (user.authenticate(password, dbPass)) {
                    Log.d("hehe", "apparentyl it worked!!!");
                    Log.d("id", "after" + user.getRoleId());
                    if(user.getRoleId() == 2){
                        Intent intent = new Intent(this.appContext, EmployeeOptionsView.class);
                        intent.putExtra("user", user);
                        appContext.startActivity(intent);
                    } else if(user.getRoleId() == 3){
                        Intent intent = new Intent(this.appContext, CustomerHomeView.class);
                        intent.putExtra("user", user);
                        appContext.startActivity(intent);
                    }

                }

            } catch (NullPointerException | NumberFormatException e) {
                Toast.makeText(appContext, "Please Enter appropriate login information!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void justInitSth() {

    }


}
