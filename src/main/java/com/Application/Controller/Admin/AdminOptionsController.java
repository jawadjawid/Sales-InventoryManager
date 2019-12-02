package com.Application.Controller.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.serialization.DatabaseSerializer;
import com.Application.Model.users.User;
import com.Application.View.Admin.AdminOptionsView;
import com.Application.View.MainLoginView;
import com.example.Application.R;

import java.sql.SQLException;

public class AdminOptionsController implements View.OnClickListener {

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

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_serialize:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(appContext);
                builder1.setTitle("Enter file location to serialize to:");

                final EditText input1 = new EditText(appContext);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input1.setInputType(InputType.TYPE_CLASS_TEXT);
                builder1.setView(input1);

                // Set up the buttons
                builder1.setPositiveButton("serialize", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // serialize
                        try {
                            DatabaseSerializer.serializeDatabase(appContext, input1.getText().toString());
                        }catch (DatabaseInsertException | SQLException e){

                        }

                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder1.show();
                return true;
            case R.id.action_deserialize:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(appContext);
                builder2.setTitle("Enter the location of the .ser file:");

                final EditText input2 = new EditText(appContext);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input2.setInputType(InputType.TYPE_CLASS_TEXT);
                builder2.setView(input2);

                // Set up the buttons
                builder2.setPositiveButton("Deserialize", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // deserialize
                        try {
                            DatabaseSerializer.deserializeDatabase(appContext, user, input2.getText().toString());
                        }catch (Exception e){

                        }
                    }
                });
                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder2.show();
                return true;
            case R.id.action_logout:
                Intent intent = new Intent(appContext, MainLoginView.class);
                appContext.startActivity(intent);
                return true;
            default:
                return true;
        }
    }
}
