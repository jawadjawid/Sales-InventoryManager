package com.Application.Controller.Employee;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.example.Application.R;

import java.util.ArrayList;
import java.util.List;


public class MakeNewAccountFragmentController implements View.OnClickListener {
    private View view;
    private Context appContext;

    public MakeNewAccountFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
        setNumberPickerValues(); // THIS WORKS NOW JUST MAKE IT LOOK COOL
    }

    @Override
    public void onClick(View v) {
        try {
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
            NumberPicker customerIdNumberPicker = view.findViewById(R.id.customerIdNumberPicker);
            int customerId = customerIdNumberPicker.getValue();

            int accountId = Math.toIntExact(mydb.insertAccountH(customerId));
            Toast.makeText(appContext, "Account successfully created with id " + accountId, Toast.LENGTH_SHORT).show();

        } catch (DatabaseInsertException e) {
    e.printStackTrace();
        }
    }

    private void setNumberPickerValues() {

        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
        List<Integer> customerIdsInt = mydb.getUsersByRoleH(3);

        String[] customerIds = new String[customerIdsInt.size()];

        for (int i = 0; i < customerIds.length; i++) {
            customerIds[i] = "" + customerIdsInt.get(i);
        }

        NumberPicker customerIdNumberPicker = view.findViewById(R.id.customerIdNumberPicker);
        customerIdNumberPicker.setWrapSelectorWheel(true);
        customerIdNumberPicker.setMinValue(0);
        customerIdNumberPicker.setMaxValue(customerIds.length - 1);
        customerIdNumberPicker.setDisplayedValues(customerIds);
    }


}
