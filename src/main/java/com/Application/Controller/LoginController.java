package com.Application.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.User;
import com.Application.View.Employee.MainActivity;
import com.Application.View.LoginView;
import com.example.Application.R;

public class LoginController implements View.OnClickListener {
    private LoginView view;
    private Context appContext;

    public LoginController(Context context) {
        appContext = context;
        view = (LoginView)appContext;
        Log.d("GAY","noeeeeeeee");
    }

    @Override
    public void onClick(View v) {
        Log.d("GAY","no");


        if(v.getId() == R.id.LogIn){
            try {
                Intent intent = new Intent(this.appContext, MainActivity.class);
                appContext.startActivity(intent);

              //  Button button1 = (Button) view.findViewById(R.id.LogIn);

             //   EditText textView1 = (EditText) view.findViewById(R.id.userId);
             //   int userId = Integer.parseInt(textView1.getText().toString());
            //    User user = DatabaseSelectHelper.getUserDetails(userId);

            //    EditText textView2 = (EditText) view.findViewById(R.id.Password);
             //   String password = textView2.getText().toString();

             //   if(user.authenticate(password)){
                    // login successful

             //   }

            }catch (Exception e){

            }
        }
    }


}
