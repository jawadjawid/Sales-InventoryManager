package com.Application.Model.database.helper;

import android.content.Context;
import android.database.Cursor;

import com.Application.Model.database.DatabaseDriverAndroid;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.InventoryImpl;
import com.Application.Model.inventory.Item;
import com.Application.Model.inventory.ItemImpl;
import com.Application.Model.store.Account;
import com.Application.Model.store.AccountImpl;
import com.Application.Model.store.ItemizedSaleImpl;
import com.Application.Model.store.Sale;
import com.Application.Model.store.SaleImpl;
import com.Application.Model.store.SalesLog;
import com.Application.Model.store.SalesLogImpl;
import com.Application.Model.users.Admin;
import com.Application.Model.users.Customer;
import com.Application.Model.users.Employee;
import com.Application.Model.users.Roles;
import com.Application.Model.users.User;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseDriverAndroidHelper extends DatabaseDriverAndroid {

    public DatabaseDriverAndroidHelper(Context context) {
        super(context);
        AccountSummaryValidator.setContext(this);
        AccountValidator.setContext(this);
        InventoryValidator.setContext(this);
        ItemizedSalesValidator.setContext(this);
        ItemsValidator.setContext(this);
        RolesValidator.setContext(this);
        SalesValidator.setContext(this);
        UserRoleValidator.setContext(this);
        UsersValidator.setContext(this);
    }

    public long insertRoleH(String name) throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && RolesValidator.validateName(name);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long roleId = insertRole(name);
        return roleId;
    }
    
    public void insertPasswordH(String password, int userId) throws SQLException {
    	insertPasswordUnhashed(password, userId);
            
          }
    
    public void insertNewUserNoPasswordH(String name, Integer age, String address){
    	insertNewUserNoPassword(name, age, address);
    	
    }


    public long insertNewUserH(String name, int age, String address, String password)
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

    public long insertUserRoleH(int userId, int roleId)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && UserRoleValidator.validateRoleId(roleId);
        val_success = val_success && UserRoleValidator.validateUserId(userId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long userRoleId = insertUserRole(userId, roleId);
        return userRoleId;
    }

    public long insertItemH(String name, BigDecimal price)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && ItemsValidator.validateName(name);
        val_success = val_success && ItemsValidator.validatePrice(price);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long itemId = insertItem(name, price);
        return itemId;
    }

    public long insertInventoryH(int itemId, int quantity)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && InventoryValidator.validateItemId(itemId);
        val_success = val_success && InventoryValidator.validateQuantity(quantity);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long inventoryId = insertInventory(itemId, quantity);
        return inventoryId;
    }

    public long insertSaleH(int userId, BigDecimal totalPrice)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && SalesValidator.validateUserId(userId);
        val_success = val_success && SalesValidator.validateTotalPrice(totalPrice);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long saleId = insertSale(userId, totalPrice);
        return saleId;
    }

    public long insertItemizedSaleH(int saleId, int itemId, int quantity)
            throws DatabaseInsertException {

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

    public long insertAccountH(int userId)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && AccountValidator.validateUserId(userId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        long accountId = insertAccount(userId,true);
        return accountId;
    }

    public long insertAccountSummaryH(int acctId, int itemId, int quantity)
            throws DatabaseInsertException {

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

    public List<Integer> getRoleIdsH() {
        Cursor results = getRoles();
        List<Integer> ids = new ArrayList<>();
        while (results.moveToNext()) {
            ids.add(results.getInt(results.getColumnIndex("ID")));
        }
        results.close();
        return ids;
    }

    public String getRoleNameH(int roleId) {
        String role = getRole(roleId);
        return role;
    }

    public int getUserRoleIdH(int userId) {
        int roleId = getUserRole(userId);
        return roleId;
    }

    public List<Integer> getUsersByRoleH(int roleId)  {
        Cursor results = getUsersByRole(roleId);
        List<Integer> userIds = new ArrayList<>();
        while (results.moveToNext()) {
            userIds.add(results.getInt(results.getColumnIndex("USERID")));
        }
        results.close();
        return userIds;
    }

    public List<User> getUsersDetailsH() {
        Cursor results = getUsersDetails();
        int roleId;
        String role;

        List<User> users = new ArrayList<>();
        User user;
        while (results.moveToNext()) {
            roleId = getUserRole(results.getInt(results.getColumnIndex("ID")));
            role = getRole(roleId);
            user = createUser(results.getInt(results.getColumnIndex("ID")), results.getString(results.getColumnIndex("NAME")), results.getInt(results.getColumnIndex("AGE")),
                    results.getString(results.getColumnIndex("ADDRESS")), role, roleId);
            users.add(user);
        }
        results.close();
        return users;
    }

    public User getUserDetailsH(int userId)  {
        Cursor results = getUserDetails(userId);
        int roleId;
        String role;

        User user = null;
        while (results.moveToNext()) {
            roleId = getUserRole(results.getInt(results.getColumnIndex("ID")));
            role = getRole(roleId);
            user = createUser(results.getInt(results.getColumnIndex("ID")), results.getString(results.getColumnIndex("NAME")), results.getInt(results.getColumnIndex("AGE")),
                    results.getString(results.getColumnIndex("ADDRESS")), role, roleId);
        }
        results.close();
        return user;
    }

    private User createUser(int id, String name, int age, String address, String role, int roleId) {
        if ("ADMIN".equals(Roles.valueOf(role))) {
            return new Admin(id, name, age, address, roleId);
        } else if ("EMPLOYEE".equals(Roles.valueOf(role))) {
            return new Employee(id, name, age, address, roleId);
        } else {
            return new Customer(id, name, age, address, roleId);
        }
    }

    public String getPasswordH(int userId) {
        String password = getPassword(userId);
        return password;
    }

    public List<Item> getAllItemsH()  {
        Cursor results = getAllItems();
        List<Item> items = new ArrayList<>();
        Item item;
        while (results.moveToNext()) {
            item = new ItemImpl(results.getInt(results.getColumnIndex("ID")), results.getString(results.getColumnIndex("NAME")),
                    new BigDecimal(results.getString(results.getColumnIndex("PRICE"))));
            items.add(item);
        }
        results.close();
        return items;
    }

    public Item getItemH(int itemId)  {
        Cursor results = getItem(itemId);

        Item item = null;
        while (results.moveToNext()) {
            item = new ItemImpl(results.getInt(results.getColumnIndex("ID")), results.getString(results.getColumnIndex("NAME")),
                    new BigDecimal(results.getString(results.getColumnIndex("PRICE"))));
        }
        results.close();
        return item;
    }

    public  Inventory getInventoryH()  {
        Cursor results = getInventory();
        Item item;
        Inventory inventory = new InventoryImpl();
        while (results.moveToNext()) {
            item = getItemH(results.getInt(results.getColumnIndex("ITEMID")));
            inventory.updateMap(item, results.getInt(results.getColumnIndex("QUANTITY")));
        }
        results.close();
        return inventory;
    }

    public int getInventoryQuantityH(int itemId) {
        int quantity = getInventoryQuantity(itemId);
        return quantity;
    }

    public SalesLog getSalesH()  {
        Cursor results = getSales();

        SalesLog salesLog = new SalesLogImpl();
        while (results.moveToNext()) {
            salesLog.addSale(new SaleImpl(results.getInt(results.getColumnIndex("ID")),
                    getUserDetailsH(results.getInt(results.getColumnIndex("USERID"))),
                    new BigDecimal(results.getString(results.getColumnIndex("TOTALPRICE")))));
        }
        results.close();
        return salesLog;
    }

    public Sale getSaleByIdH(int saleId)  {
        Cursor results = getSaleById(saleId);
        Sale sale = null;
        while (results.moveToNext()) {
            User user = getUserDetailsH(results.getInt(results.getColumnIndex("USERID")));
            sale = new SaleImpl(results.getInt(results.getColumnIndex("ID")), user, new BigDecimal(results.getString(results.getColumnIndex("TOTALPRICE"))));
        }
        results.close();
        return sale;
    }

    public List<Sale> getSalesToUserH(int userId)  {
        Cursor results = getSalesToUser(userId);
        List<Sale> sales = new ArrayList<>();
        while (results.moveToNext()) {
            User user = getUserDetailsH(results.getInt(results.getColumnIndex("USERID")));
            sales.add(new SaleImpl(results.getInt(results.getColumnIndex("ID")), user, new BigDecimal(results.getString(results.getColumnIndex("TOTALPRICE")))));
        }
        results.close();
        return sales;
    }

    public Sale getItemizedSaleByIdH(int saleId) {
        Cursor results = getItemizedSaleById(saleId);

        Sale sale = new ItemizedSaleImpl(results.getInt(results.getColumnIndex("SALEID")));
        while (results.moveToNext()) {
            Item item = getItemH(results.getInt(results.getColumnIndex("ITEMID")));
            sale.getItemMap().put(item, results.getInt(results.getColumnIndex("QUANTITY")));
        }
        results.close();
        return sale;
    }

    public SalesLog getItemizedSalesH() {
        Cursor results = getItemizedSales();
        SalesLog salesLog = new SalesLogImpl();


        while (results.moveToNext()) {
            Sale sale = salesLog.getSale(results.getInt(results.getColumnIndex("SALEID")));
            if (sale == null) {
                HashMap<Item, Integer> itemMap = new HashMap<>();
                itemMap.put(getItemH(results.getInt(results.getColumnIndex("ITEMID"))), results.getInt(results.getColumnIndex("QUANTITY")));
                salesLog.addSale(new ItemizedSaleImpl(results.getInt(results.getColumnIndex("SALEID")), itemMap));
            } else {
                sale.getItemMap().put(getItemH(results.getInt(results.getColumnIndex("ITEMID"))),
                        results.getInt(results.getColumnIndex("QUANTITY")));
            }
        }
        results.close();
        return salesLog;
    }

    public  boolean userIdExists(int userId) {
        Cursor results = getUserDetails(userId);
        boolean exists = false;
        while (results.moveToNext()) {
            exists = true;
        }
        results.close();
        return exists;
    }

    public ArrayList<Account> getUserAccountsH(int userId) {
        Cursor results = getUserAccounts(userId);
        Cursor accountDetailsResult = null;
        User user = getUserDetailsH(userId);
        Account currentAccount = null;
        ArrayList<Account> accounts = new ArrayList<>();
        while (results.moveToNext()) {
            accountDetailsResult = getAccountDetails(results.getInt(results.getColumnIndex("ID")));
            currentAccount = new AccountImpl(results.getInt(results.getColumnIndex("ID")), user);
            while (accountDetailsResult.moveToNext()) {
                currentAccount.addItem(getItemH(accountDetailsResult.getInt(accountDetailsResult.getColumnIndex("ITEMID"))),
                        accountDetailsResult.getInt(accountDetailsResult.getColumnIndex("QUANTITY")));
            }
            accounts.add(currentAccount);
        }
        results.close();
        return accounts;
    }

    public ArrayList<Account> getUserActiveAccountsH(int userId) {
        Cursor results = getUserActiveAccounts(userId);
        Cursor accountDetailsResult = null;
        User user = getUserDetailsH(userId);
        Account currentAccount = null;
        ArrayList<Account> accounts = new ArrayList<>();
        while (results.moveToNext()) {
            accountDetailsResult = getAccountDetails(results.getInt(results.getColumnIndex("ID")));
            currentAccount = new AccountImpl(results.getInt(results.getColumnIndex("ID")), user);
            while (accountDetailsResult.moveToNext()) {
                currentAccount.addItem(getItemH(accountDetailsResult.getInt(accountDetailsResult.getColumnIndex("ITEMID"))),
                        accountDetailsResult.getInt(accountDetailsResult.getColumnIndex("QUANTITY")));
            }
            accounts.add(currentAccount);
        }
        results.close();
        return accounts;
    }

    public ArrayList<Account> getUserInactiveAccountsH(int userId) {
        Cursor results = getUserInactiveAccounts(userId);
        Cursor accountDetailsResult = null;
        User user = getUserDetailsH(userId);
        Account currentAccount = null;
        ArrayList<Account> accounts = new ArrayList<>();
        while (results.moveToNext()) {
            accountDetailsResult = getAccountDetails(results.getInt(results.getColumnIndex("ID")));
            currentAccount = new AccountImpl(results.getInt(results.getColumnIndex("ID")), user);
            while (accountDetailsResult.moveToNext()) {
                currentAccount.addItem(getItemH(accountDetailsResult.getInt(accountDetailsResult.getColumnIndex("ITEMID"))),
                        accountDetailsResult.getInt(accountDetailsResult.getColumnIndex("QUANTITY")));
            }
            accounts.add(currentAccount);
        }
        results.close();
        return accounts;
    }

    public boolean updateRoleNameH(String name, int id)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && RolesValidator.validateName(name);
        val_success = val_success && RolesValidator.validateId(id);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateRoleName(name, id);
        return complete;
    }

    public boolean updateUserNameH(String name, int userId)
            throws  DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && UsersValidator.validateName(name);
        val_success = val_success && UsersValidator.validateId(userId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateUserName(name, userId);
        return complete;
    }

    public boolean updateUserAgeH(int age, int userId)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && UsersValidator.validateId(userId);
        val_success = val_success && UsersValidator.validateAge(age);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateUserAge(age, userId);
        return complete;
    }

    public  boolean updateUserAddressH(String address, int userId)
            throws  DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && UsersValidator.validateId(userId);
        val_success = val_success && UsersValidator.validateAddress(address);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateUserAddress(address, userId);
        return complete;
    }

    public boolean updateUserRoleH(int roleId, int userId)
            throws DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && UserRoleValidator.validateUserId(userId);
        val_success = val_success && UserRoleValidator.validateRoleId(roleId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateUserRole(roleId, userId);
        return complete;
    }

    public  boolean updateItemNameH(String name, int itemId)
            throws  DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && ItemsValidator.validateName(name);
        val_success = val_success && ItemsValidator.validateId(itemId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateItemName(name, itemId);
        return complete;

    }

    public boolean updateItemPriceH(BigDecimal price, int itemId)
            throws  DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && ItemsValidator.validatePrice(price);
        val_success = val_success && ItemsValidator.validateId(itemId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateItemPrice(price, itemId);
        return complete;
    }

    public boolean updateInventoryQuantityH(int quantity, int itemId)
            throws  DatabaseInsertException {

        boolean val_success = true;
        val_success = val_success && InventoryValidator.validateQuantity(quantity);
        val_success = val_success && InventoryValidator.validateItemId(itemId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateInventoryQuantity(quantity, itemId);
        return complete;
    }

    public boolean updateAccountStatusH(int accountId, boolean active)
            throws  DatabaseInsertException {

        boolean val_success = true;
      val_success = val_success && AccountSummaryValidator.validateAccountId(accountId);

        if (!val_success) {
            throw new DatabaseInsertException();
        }

        boolean complete = updateAccountStatus(accountId, active);
        return complete;
    }





}
