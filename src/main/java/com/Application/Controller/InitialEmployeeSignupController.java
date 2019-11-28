package com.Application.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.View.InitialEmployeeSignupView;
import com.Application.View.MainLoginView;

public class InitialEmployeeSignupController extends SignupController implements View.OnClickListener{
    InitialEmployeeSignupView view;
    Context appContext;

    public InitialEmployeeSignupController(Context context){
        appContext = context;
        view = (InitialEmployeeSignupView) appContext;
    }

    @Override
    public void onClick(View v) {
        // employee has clicked sign up
        try {
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(view);
            int userId = createUser(view, mydb,2);

            Toast.makeText(view,"User with id " + userId + " created.",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(appContext, MainLoginView.class);
            appContext.startActivity(intent);
        }catch(Exception e){
            Toast.makeText(view,"Please ensure that all fields are completed.",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
