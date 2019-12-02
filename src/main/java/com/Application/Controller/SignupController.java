package com.Application.Controller;

import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.store.SalesApplication;
import com.example.Application.R;

import java.math.BigDecimal;

public class SignupController {

    protected int createUser(AppCompatActivity view, DatabaseDriverAndroidHelper mydb, int roleId) throws DatabaseInsertException {
        EditText usernameEditText = view.findViewById(R.id.usernameEditText);
        EditText userAgeEditText = view.findViewById(R.id.userAgeEditText);
        EditText userAddressEditText = view.findViewById(R.id.userAddressEditText);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);

        String name = usernameEditText.getText().toString();
        if(userAgeEditText.getText().toString().length() == 0){
            throw new DatabaseInsertException();
        }
        int age = Integer.parseInt(userAgeEditText.getText().toString());
        String address = userAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        BigDecimal balance = SalesApplication.INITIAL_BALANCE;

        if(roleId != SalesApplication.CUSTOMER_ID){
            balance = null;
        }

        int userId = Math.toIntExact(mydb.insertNewUserH(name, age, address, password,balance));

        mydb.insertUserRoleH(userId, roleId);
        return userId;
    }

    protected int createUser(View view, DatabaseDriverAndroidHelper mydb, int roleId) throws DatabaseInsertException {
        EditText usernameEditText = view.findViewById(R.id.usernameEditText);
        EditText userAgeEditText = view.findViewById(R.id.userAgeEditText);
        EditText userAddressEditText = view.findViewById(R.id.userAddressEditText);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);

        String name = usernameEditText.getText().toString();
        if(userAgeEditText.getText().toString().length() == 0){
            throw new DatabaseInsertException();
        }
        int age = Integer.parseInt(userAgeEditText.getText().toString());
        String address = userAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        BigDecimal balance = SalesApplication.INITIAL_BALANCE;

        if(roleId != SalesApplication.CUSTOMER_ID){
            balance = null;
        }

        int userId = Math.toIntExact(mydb.insertNewUserH(name, age, address, password,balance));

        mydb.insertUserRoleH(userId, roleId);
        return userId;
    }
}
