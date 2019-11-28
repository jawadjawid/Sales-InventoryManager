package com.Application.Model.validator;

import android.content.Context;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;

import java.sql.SQLException;

public class UserRoleValidator {
    private static DatabaseDriverAndroidHelper mydb;

    public static boolean validateUserId(int userId) {
            return mydb.userIdExists(userId);
    }

    public static boolean validateRoleId(int roleId) {
            if (!mydb.getRoleIdsH().contains(roleId)) {
                return false;
            }
            return true;
    }

    public static void setContext(DatabaseDriverAndroidHelper db) {
        mydb = db;
    }
}
