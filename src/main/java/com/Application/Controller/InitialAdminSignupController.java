package com.Application.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Application.Model.database.DatabaseDriverAndroid;
import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseInsertHelper;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.users.Admin;
import com.Application.Model.users.User;
import com.Application.View.InitialAdminSignupView;
import com.example.Application.R;

public class InitialAdminSignupController implements View.OnClickListener{
    InitialAdminSignupView view;
    Context appContext;

    public InitialAdminSignupController(Context context){
        appContext = context;
        view = (InitialAdminSignupView) appContext;
    }

    @Override
    public void onClick(View v) {
        // admin has clicked sign up
        try {
            Log.d("hehe","no way");
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(view);
            mydb.insertRoleV("ADMIN");
            mydb.insertRoleV("EMPLOYEE");
            mydb.insertRoleV("CUSTOMER");

           // SalesApplication.insertIntoRolesTable();
           // SalesApplication.initializeRolesToId();
          //  SalesApplication.insertIntoItemsTable(); // inserts items into items table with random prices
            Log.d("hehe","no i made ittt");
            EditText usernameEditText = view.findViewById(R.id.usernameEditText);
            EditText userAgeEditText = view.findViewById(R.id.userAgeEditText);
            EditText userAddressEditText = view.findViewById(R.id.userAddressEditText);
            EditText passwordEditText = view.findViewById(R.id.passwordEditText);

            String name = usernameEditText.getText().toString();
            int age = Integer.parseInt(userAgeEditText.getText().toString());
            String address = userAddressEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            Log.d("hehe","dsds");

            int userId = Math.toIntExact(mydb.insertNewUserV(name, age, address, password));
            mydb.insertUserRoleV(userId, 1);

            Log.d("hehe","wat the hell");

            Toast.makeText(view,"success!!!" + userId,Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(view,"oh hehe i messed up",Toast.LENGTH_SHORT).show();
            Log.d("hehe","wat the hell");
            e.printStackTrace();
        }
    }


}
