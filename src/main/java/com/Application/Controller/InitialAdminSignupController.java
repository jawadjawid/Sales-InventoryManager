package com.Application.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.View.InitialAdminSignupView;
import com.Application.View.InitialEmployeeSignupView;
import com.Application.View.MainLoginView;

import java.math.BigDecimal;

public class InitialAdminSignupController extends SignupController implements View.OnClickListener{
    InitialAdminSignupView view;
    Context appContext;

    public InitialAdminSignupController(Context context){
        appContext = context;
        view = (InitialAdminSignupView) appContext;

        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(view);

        // Uncomment below to go to admin sign up page
       appContext.deleteDatabase("inventorymgmt.db");


        if(mydb.getUserDetailsH(2) == null){
            // first run
            dbSetup(mydb);
        }else{
            Intent intent = new Intent(appContext, MainLoginView.class);
            appContext.startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        try {
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(view);

            int userId = createUser(view, mydb,1);

            Toast.makeText(view,"User with id " + userId + " created.",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(appContext, InitialEmployeeSignupView.class);
            appContext.startActivity(intent);

        }catch(DatabaseInsertException | NumberFormatException e){
            Toast.makeText(view,"Please ensure that all fields are completed.",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void dbSetup(DatabaseDriverAndroidHelper mydb) {
        try {
         //   appContext.deleteDatabase("inventorymgmt.db");

            Log.d("HAHA","dfsd");

            mydb.insertRoleH("ADMIN");
            mydb.insertRoleH("EMPLOYEE");
            mydb.insertRoleH("CUSTOMER");

            mydb.insertItemH("FISHING_ROD", new BigDecimal("12.00").setScale(2));
            mydb.insertItemH("HOCKEY_STICK", new BigDecimal("8.50").setScale(2));
            mydb.insertItemH("SKATES", new BigDecimal("10.00").setScale(2));
            mydb.insertItemH("RUNNING_SHOES", new BigDecimal("15.00").setScale(2));
            mydb.insertItemH("PROTEIN_BAR", new BigDecimal("3.00").setScale(2));

            mydb.insertInventoryH(1, 0);
            mydb.insertInventoryH(2, 0);
            mydb.insertInventoryH(3, 0);
            mydb.insertInventoryH(4, 0);
            mydb.insertInventoryH(5, 0);
        }catch (DatabaseInsertException e){

        }
    }


}
