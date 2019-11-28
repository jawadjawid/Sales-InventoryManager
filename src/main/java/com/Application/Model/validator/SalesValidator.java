package com.Application.Model.validator;

import android.content.Context;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.User;

import java.math.BigDecimal;
import java.sql.SQLException;

public class SalesValidator {
    private static DatabaseDriverAndroidHelper mydb;

    public static boolean validateUserId(int id) {
            User user = mydb.getUserDetailsH(id);
            if (user == null) {
                return false;
            }
            return true;
    }

    public static boolean validateTotalPrice(BigDecimal price) {
        if (!(price.compareTo(BigDecimal.ZERO) > 0) || !(price.scale() == 2)) {
            return false;
        }
        return true;
    }

    public static void setContext(Context context) {
        mydb = new DatabaseDriverAndroidHelper(context);
    }
}
