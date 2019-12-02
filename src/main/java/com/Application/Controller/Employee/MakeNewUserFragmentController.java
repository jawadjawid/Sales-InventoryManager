package com.Application.Controller.Employee;

import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.Application.Controller.SignupController;
import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.store.SalesApplication;
import com.example.Application.R;


public class MakeNewUserFragmentController extends SignupController implements View.OnClickListener {
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

        } catch (DatabaseInsertException e) {
            Toast.makeText(appContext, "Please ensure that all fields are completed & length of address is less than 100 characters.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private int getRoleId() {
        RadioGroup roleRadioGroup = view.findViewById(R.id.roleRadioGroup);
        int radioButtonId = roleRadioGroup.getCheckedRadioButtonId();

        if (radioButtonId == R.id.employeeRadioButton) {
            return SalesApplication.EMPLOYEE_ID;
        } else {
            return SalesApplication.CUSTOMER_ID;
        }
    }


}
