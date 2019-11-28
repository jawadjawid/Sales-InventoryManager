package com.Application.Model.validator;

import android.content.Context;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.Roles;

import java.sql.SQLException;

public class RolesValidator {
    private static DatabaseDriverAndroidHelper mydb;

    public static boolean validateName(String name) {
        if (name == null) {
            return false;
        }
        if (name.equals(Roles.EMPLOYEE.name()) || name.equals(Roles.CUSTOMER.name()) || name
                .equals(Roles.ADMIN.name())) {
            return true;
        }
        return false;
    }

    public static boolean validateId(int id) {
            if (mydb.getRoleIdsH().contains(id)) {
                return true;
            } else {
                return false;
            }
    }

    public static void setContext(Context context) {
        mydb = new DatabaseDriverAndroidHelper(context);
    }
}
