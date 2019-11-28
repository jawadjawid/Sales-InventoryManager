package com.Application.Model.validator;

import android.content.Context;

import java.sql.SQLException;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.inventory.Item;

public class AccountSummaryValidator {

    private static DatabaseDriverAndroidHelper mydb;

    public static boolean validateItemId(int itemId) {
        Item item = mydb.getItemH(itemId);
        if (item == null) {
            return false;
        }
        return true;
    }

    public static boolean validateAccountId(int accountId) {
        return true;
    }

    public static boolean validateQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        }
        return true;
    }

    public static void setContext(DatabaseDriverAndroidHelper db) {
        mydb = db;
    }
}
