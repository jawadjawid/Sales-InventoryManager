package com.Application.Controller.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.Application.Model.users.User;
import com.Application.View.Admin.AdminOptionsView;
import com.Application.View.MainLoginView;

public class AdminOptionsController implements View.OnClickListener{

    private static AdminOptionsView view;
    private Context appContext;
    private static User user;

    public AdminOptionsController(Context context) {
        appContext = context;
        view = (AdminOptionsView) appContext;
        Intent intent = view.getIntent();
        user = (User) intent.getSerializableExtra("user");

      //  Bundle bundle = new Bundle();
     //   bundle.putSerializable("user", user);
        // set MyFragment Arguments
       // AccountsFragmentView myObj = new AccountsFragmentView();
      //  myObj.setArguments(bundle);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.appContext, MainLoginView.class);
        appContext.startActivity(intent);
    }
}
