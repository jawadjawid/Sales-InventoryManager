package com.Application.Model.database.helper;

import android.content.Context;

import com.Application.Model.database.DatabaseDriverAndroid;
import com.Application.Model.database.DatabaseInserter;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.validator.AccountSummaryValidator;
import com.Application.Model.validator.AccountValidator;
import com.Application.Model.validator.InventoryValidator;
import com.Application.Model.validator.ItemizedSalesValidator;
import com.Application.Model.validator.ItemsValidator;
import com.Application.Model.validator.RolesValidator;
import com.Application.Model.validator.SalesValidator;
import com.Application.Model.validator.UserRoleValidator;
import com.Application.Model.validator.UsersValidator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseDriverAndroidHelper extends DatabaseDriverAndroid {
    public DatabaseDriverAndroidHelper(Context context) {
        super(context);
    }

    public long insertRoleV(String name) throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && RolesValidator.validateName(name);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long roleId = insertRole(name);
        return roleId;
    }

    public long insertNewUserV(String name, int age, String address, String password)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && UsersValidator.validateName(name);
        val_success = val_success && UsersValidator.validateAge(age);
        val_success = val_success && UsersValidator.validateAddress(address);
        val_success = val_success && UsersValidator.validatePassword(password);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long userId = insertNewUser(name, age, address, password);
        return userId;
    }

    public long insertUserRoleV(int userId, int roleId)
            throws DatabaseInsertException, SQLException {

        boolean val_success = true;
        val_success = val_success && UserRoleValidator.validateRoleId(roleId);
        val_success = val_success && UserRoleValidator.validateUserId(userId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long userRoleId = insertUserRole(userId, roleId);
        return userRoleId;
    }

    public long insertItemV(String name, BigDecimal price)
            throws DatabaseInsertException, SQLException {

        boolean val_success = true;
        val_success = val_success && ItemsValidator.validateName(name);
        val_success = val_success && ItemsValidator.validatePrice(price);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long itemId = insertItem(name, price);
        return itemId;
    }

    public long insertInventoryV(int itemId, int quantity)
            throws DatabaseInsertException, SQLException {

        boolean val_success = true;
        val_success = val_success && InventoryValidator.validateItemId(itemId);
        val_success = val_success && InventoryValidator.validateQuantity(quantity);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long inventoryId = insertInventory(itemId, quantity);
        return inventoryId;
    }

    public long insertSaleV(int userId, BigDecimal totalPrice)
            throws DatabaseInsertException, SQLException {

        boolean val_success = true;
        val_success = val_success && SalesValidator.validateUserId(userId);
        val_success = val_success && SalesValidator.validateTotalPrice(totalPrice);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long saleId = insertSale(userId, totalPrice);
        return saleId;
    }

    public long insertItemizedSaleV(int saleId, int itemId, int quantity)
            throws DatabaseInsertException, SQLException {

        // Note: sale been inserted into sales already

        boolean val_success = true;
        val_success = val_success && ItemizedSalesValidator.validateSaleId(saleId);
        val_success = val_success && ItemizedSalesValidator.validateItemId(itemId);
        val_success = val_success && ItemizedSalesValidator.validateQuantity(quantity);
        val_success = val_success && ItemizedSalesValidator.validateUniqueComb(saleId, itemId);
        val_success = val_success && ItemizedSalesValidator.validateTotalPrice(saleId, itemId, quantity);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long itemizedId = insertItemizedSale(saleId, itemId, quantity);
        return itemizedId;
    }

    public long insertAccountV(int userId)
            throws DatabaseInsertException, SQLException {

        boolean val_success = true;
        val_success = val_success && AccountValidator.validateUserId(userId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long accountId = insertAccount(userId,true);
        return accountId;
    }

    public long insertAccountSummaryV(int acctId, int itemId, int quantity)
            throws DatabaseInsertException, SQLException {

        boolean val_success = true;
        val_success = val_success && AccountSummaryValidator.validateAccountId(acctId);
        val_success = val_success && AccountSummaryValidator.validateItemId(itemId);
        val_success = val_success && AccountSummaryValidator.validateQuantity(quantity);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long accountSummaryId = insertAccountLine(acctId, itemId, quantity);
        return accountSummaryId;
    }


}
