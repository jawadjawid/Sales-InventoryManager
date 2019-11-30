package com.Application.Controller.Admin;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.users.Employee;
import com.Application.Model.users.EmployeeInterface;
import com.example.Application.R;

import java.util.List;


public class EmployeesFragmentController implements View.OnClickListener {
    private View view;
    private Context appContext;
    private String[] employeeIds;

    public EmployeesFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
        setNumberPickerValues(); // THIS WORKS NOW JUST MAKE IT LOOK COOL
    }

    @Override
    public void onClick(View v) {
        try {
            Log.d("hehe","picked employee 2222i");
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
            NumberPicker employeeIdNumberPicker = view.findViewById(R.id.employeeIdNumberPicker);

            Log.d("hehe","picked employee i");
            int employeeId = Integer.parseInt(employeeIds[employeeIdNumberPicker.getValue()]);
            boolean success = mydb.updateUserRoleH(1, employeeId);
            if (success) {
                Toast.makeText(appContext, "Successfully promoted employee with id  " + employeeId, Toast.LENGTH_SHORT).show();
            }
        } catch (DatabaseInsertException e) {
            e.printStackTrace();
        }
    }

    private void setNumberPickerValues() {
        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
        List<Integer> employeeIdsInt = mydb.getUsersByRoleH(2);
        if (employeeIdsInt.size() == 0) {
            employeeIds = new String[1];
            employeeIds[0] = "No employees exist.";
            Button selectEmployeeIdButton = view.findViewById(R.id.selectEmployeeIdButton);
            selectEmployeeIdButton.setEnabled(false);
        } else {

            employeeIds = new String[employeeIdsInt.size()];
            for (int i = 0; i < employeeIds.length; i++) {
                employeeIds[i] = "" + employeeIdsInt.get(i);
            }
        }

        NumberPicker employeeIdNumberPicker = view.findViewById(R.id.employeeIdNumberPicker);
        employeeIdNumberPicker.setWrapSelectorWheel(true);
        employeeIdNumberPicker.setMinValue(0);
        employeeIdNumberPicker.setMaxValue(employeeIds.length - 1);
        employeeIdNumberPicker.setDisplayedValues(employeeIds);
    }


}
