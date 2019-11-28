package com.Application.Controller.Employee;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.Application.Controller.SignUpController;
import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.View.Employee.MakeNewUserFragmentView;
import com.Application.View.LoginView;
import com.example.Application.R;


public class MakeNewUserFragmentController extends SignUpController implements View.OnClickListener {
    private View view;
    private Context appContext;

    public MakeNewUserFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
    }

    @Override
    public void onClick(View v) {
        // employee has clicked sign up
        try {
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);

            int userId = createUser(view, mydb, getRoleId());

            Toast.makeText(appContext, "User with id " + userId + " created.", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(appContext, "Please ensure that all fields are completed.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private int getRoleId() {
        RadioGroup roleRadioGroup = view.findViewById(R.id.roleRadioGroup);
        int radioButtonId = roleRadioGroup.getCheckedRadioButtonId();

        if (radioButtonId == R.id.employeeRadioButton) {
            return 2;
        } else {
            return 3;
        }
    }


}
