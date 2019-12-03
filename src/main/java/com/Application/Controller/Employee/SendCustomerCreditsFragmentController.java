package com.Application.Controller.Employee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.ColorInt;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.Item;
import com.Application.Model.users.User;
import com.example.Application.R;

import java.math.BigDecimal;
import java.util.List;


public class SendCustomerCreditsFragmentController implements View.OnClickListener {

  private View view;
  private Context appContext;
  private String[] customerIds;

  public SendCustomerCreditsFragmentController(View view) {
    this.view = view;
    appContext = view.getContext();
    setNumberPickerValues();
  }

  @Override
  public void onClick(View v) {
    try {
      DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
      NumberPicker customerIdNumberPicker = view.findViewById(R.id.customerIdNumberPicker);
      int customerId = Integer.parseInt(customerIds[customerIdNumberPicker.getValue()]);

      EditText creditAmountEditText = view.findViewById(R.id.creditAmountEditText);
      String creditAmount = creditAmountEditText.getText().toString();

      BigDecimal newBalance = new BigDecimal(creditAmount);

      User user = mydb.getUserDetailsH(customerId);
      mydb.updateUserBalanceH(user.getBalance().add(newBalance), customerId);
      Toast.makeText(appContext, "Success.", Toast.LENGTH_SHORT).show();


    } catch (DatabaseInsertException e) {
      Toast.makeText(appContext, "Database Error. Account Not Created.", Toast.LENGTH_SHORT).show();

    }
  }

  private void setNumberPickerValues() {
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
    List<Integer> customerIdsInt = mydb.getUsersByRoleH(3);
    if (customerIdsInt.size() == 0) {
      customerIds = new String[1];
      customerIds[0] = "No customers exist.";
      Button selectCustomerIdButton = view.findViewById(R.id.selectCustomerIdButton);
      selectCustomerIdButton.setEnabled(false);
    } else {

      customerIds = new String[customerIdsInt.size()];
      for (int i = 0; i < customerIds.length; i++) {
        customerIds[i] = "" + customerIdsInt.get(i);
      }
    }

    NumberPicker customerIdNumberPicker = view.findViewById(R.id.customerIdNumberPicker);
    customerIdNumberPicker.setWrapSelectorWheel(true);
    customerIdNumberPicker.setMinValue(0);
    customerIdNumberPicker.setMaxValue(customerIds.length - 1);
    customerIdNumberPicker.setDisplayedValues(customerIds);

  }


}
