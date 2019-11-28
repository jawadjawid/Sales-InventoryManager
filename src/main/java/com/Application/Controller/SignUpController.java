package com.Application.Controller;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.example.Application.R;

public class SignUpController {

    protected int createUser(AppCompatActivity view, DatabaseDriverAndroidHelper mydb, int roleId) throws DatabaseInsertException {
        EditText usernameEditText = view.findViewById(R.id.usernameEditText);
        EditText userAgeEditText = view.findViewById(R.id.userAgeEditText);
        EditText userAddressEditText = view.findViewById(R.id.userAddressEditText);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);

        String name = usernameEditText.getText().toString();
        int age = Integer.parseInt(userAgeEditText.getText().toString());
        String address = userAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        int userId = Math.toIntExact(mydb.insertNewUserH(name, age, address, password));
        mydb.insertUserRoleH(userId, roleId);
        return userId;
    }
}
