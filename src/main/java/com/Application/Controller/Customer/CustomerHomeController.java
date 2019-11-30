package com.Application.Controller.Customer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerCartView;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Customer.CustomerShoppingView;
import com.Application.View.MainLoginView;
import com.example.Application.R;

public class CustomerHomeController implements View.OnClickListener {
    private CustomerHomeView view;
    private Context appContext;
    private User recievedUser;

    public CustomerHomeController(Context context) {
        appContext = context;
        view = (CustomerHomeView) appContext;

        setUserName();
        TextView usernameTextView = view.findViewById(R.id.customerName);
        usernameTextView.setText(recievedUser.getName());
        displayRestoreAlert();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shopBtn) {
            Intent intent = new Intent(appContext, CustomerShoppingView.class);
            intent.putExtra("user", recievedUser);
            view.startActivity(intent);
        } else if (v.getId() == R.id.cartBtn) {
            Intent intent = new Intent(appContext, CustomerCartView.class);
            view.startActivity(intent);
        } else if (v.getId() == R.id.LogOutBtn) {
            Intent intent = new Intent(appContext, MainLoginView.class);
            view.startActivity(intent);
        }
    }

    public void setUserName() {
        Intent intent = view.getIntent();
        User user = (User) intent.getSerializableExtra("user");
        this.recievedUser = user;
    }

    private void displayRestoreAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(appContext);

        final EditText edittext = new EditText(appContext);
        edittext.setHintTextColor(Color.BLACK);
        edittext.setHint("    Account ID");
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);

        alert.setMessage("\n\nPlease enter the ID of the account to restore from below:\n");
        alert.setTitle("Restore Shopping Cart ?");
        alert.setView(edittext);

        alert.setNegativeButton("Do not Restore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.setPositiveButton("Restore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable YouEditTextValue = edittext.getText();

            }
        });
        alert.show();
    }
}
