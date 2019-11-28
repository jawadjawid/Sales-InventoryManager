package com.Application.Model.validator;

import android.content.Context;

import java.sql.SQLException;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.User;

public class AccountValidator {

    private static DatabaseDriverAndroidHelper mydb;

    public static boolean validateUserId(int id) {
        User user = mydb.getUserDetailsH(id);
        if (user == null) {
            return false;
        }
        return true;
    }


    public static void setContext(Context context) {
        mydb = new DatabaseDriverAndroidHelper(context);
    }
}
