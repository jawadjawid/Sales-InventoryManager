package com.Application.Model.validator;

import android.content.Context;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;

import java.math.BigDecimal;
import java.sql.SQLException;

public class UsersValidator {
    private static DatabaseDriverAndroidHelper mydb;

    public static boolean validateName(String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }

    public static boolean validateId(int id) {
            if (mydb.userIdExists(id)) {
                return true;
            }
            return false;
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.equals("")) {
            return false;
        }
        return true;
    }

    public static boolean validateAge(int age) {
        if (!(age >= 0)) {
            return false;
        }
        return true;
    }

    public static boolean validateAddress(String address) {
        if (address == null || address.length() > 100) {
            return false;
        }
        return true;
    }

    public static void setContext(DatabaseDriverAndroidHelper db) {
        mydb = db;
    }

    public static boolean validateBalance(BigDecimal balance) {
        if (!(balance.compareTo(BigDecimal.ZERO) >= 0) || (balance.scale() != 2)) {
            return false;
        }
        return true;
    }
}
