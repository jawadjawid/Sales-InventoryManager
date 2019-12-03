package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.Application.Model.users.User;
import com.Application.View.Employee.EmployeeOptionsView;
import com.Application.View.Employee.HomeFragmentView;
import com.example.Application.R;
import com.google.android.material.navigation.NavigationView;


public class EmployeeOptionsController implements View.OnClickListener {

  private static EmployeeOptionsView view;
  private Context appContext;
  private static User user;

  public EmployeeOptionsController(Context context) {
    appContext = context;
    view = (EmployeeOptionsView) appContext;
    Intent intent = view.getIntent();
    user = (User) intent.getSerializableExtra("user");

    setUsername(user.getName());
  }

  @Override
  public void onClick(View v) {
  }

  public static void setUsername(String username) {
    NavigationView navigationView = view.findViewById(R.id.nav_view);
    View headerView = navigationView.getHeaderView(0);

    TextView usernameTextView = headerView.findViewById(R.id.usernameTextView);
    usernameTextView.setText(username);
  }

  public static User getUser() {
    return user;
  }

  public static void setUser(User user1) {
    user = user1;

  }

}
