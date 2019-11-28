package com.Application.Model.validator;

import android.content.Context;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ItemsValidator {
    private static DatabaseDriverAndroidHelper mydb;

    public static boolean validateName(String name) {
        if (name == null || !(name.length() < 64)) {
            return false;
        }
        return true;
    }

    public static boolean validatePrice(BigDecimal price) {
        if (!(price.compareTo(BigDecimal.ZERO) > 0) || (price.scale() != 2)) {
            return false;
        }
        return true;
    }

    public static boolean validateId(int id) {
            if (mydb.getItemH(id) == null) {
                return false;
            }
            return true;
    }

    public static void setContext(DatabaseDriverAndroidHelper db) {
        mydb = db;
    }
}
