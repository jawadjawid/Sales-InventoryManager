package com.Application.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.View.Employee.MainActivity;
import com.Application.View.InitialAdminSignupView;
import com.Application.View.InitialEmployeeSignupView;
import com.Application.View.LoginView;
import com.example.Application.R;

public class InitialEmployeeSignupController implements View.OnClickListener{
    InitialEmployeeSignupView view;
    Context appContext;

    public InitialEmployeeSignupController(Context context){
        appContext = context;
        view = (InitialEmployeeSignupView) appContext;
        Log.d("hehe","ACACACFIRSTTnim at employeeeeeeeeo wa" +
                "y");
    }

    @Override
    public void onClick(View v) {
        // employee has clicked sign up
        try {
            Log.d("hehe","nim at employeeeeeeeeo wa" +
                    "y");
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(view);
         //   appContext.deleteDatabase("inventorymgmt.db");

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

           int userId = Math.toIntExact(mydb.insertNewUserH(name, age, address, password));
         mydb.insertUserRoleH(userId, 2);

            Log.d("hehe","wat the hell");

            Toast.makeText(view,"success!!!  " + userId,Toast.LENGTH_SHORT).show();

         //  User users = mydb.getUserDetailsH(1);
           Log.d("hehehe","dfsf");

            Intent intent = new Intent(appContext, LoginView.class);
            appContext.startActivity(intent);

        }catch(Exception e){
            Toast.makeText(view,"oh hehe i messed up",Toast.LENGTH_SHORT).show();
            Log.d("hehe","wat the hell");
            e.printStackTrace();
        }
    }


}
