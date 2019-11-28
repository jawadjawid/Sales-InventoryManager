package com.Application.Controller;

import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.users.User;
import com.example.Application.R;

public class LoginController {

    protected User loginUser(AppCompatActivity view, DatabaseDriverAndroidHelper mydb) {
        EditText userIdEditText = view.findViewById(R.id.usernameEditText);
        int userId = Integer.parseInt(userIdEditText.getText().toString());
        User user = mydb.getUserDetailsH(userId);

        EditText passwordEditText = view.findViewById(R.id.passwordEditText);
        String password = passwordEditText.getText().toString();

        String dbPass = mydb.getPasswordH(userId);

        if (user.authenticate(password, dbPass)) {
            return user;
        } else {
            return null;
        }
    }
}
