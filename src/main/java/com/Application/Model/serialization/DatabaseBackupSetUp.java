package com.Application.Model.serialization;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.InventoryImpl;
import com.Application.Model.inventory.Item;
import com.Application.Model.store.Account;
import com.Application.Model.store.AccountImpl;
import com.Application.Model.store.Sale;
import com.Application.Model.store.SalesLog;
import com.Application.Model.store.SalesLogImpl;
import com.Application.Model.users.User;

public class DatabaseBackupSetUp {

  private static DatabaseDriverAndroidHelper mydb;

  private static void SetUpUserPw(DatabaseBackup databasebackup) {
    LinkedHashMap<Integer, String> userpw = new LinkedHashMap<Integer, String>();
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      userpw.put(user.getId(), mydb.getPasswordH(user.getId()));
    }
    databasebackup.setUserPw(userpw);
  }

  private static void SetUpSales(DatabaseBackup databasebackup) {
    SalesLog salesLog = new SalesLogImpl();
    salesLog = mydb.getSalesH();
    ArrayList<Sale> sales = salesLog.getSales();
    databasebackup.setSales(sales);
  }

  private static void SetUpUsers(DatabaseBackup databasebackup) {
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    databasebackup.setUsers(usersdetails);
  }

  private static void SetUpUserRole(DatabaseBackup databasebackup) {
    LinkedHashMap<Integer, Integer> UserRole = new LinkedHashMap<Integer, Integer>();
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      UserRole.put(user.getId(), mydb.getUserRoleIdH(user.getId()));
    }
    databasebackup.setUserRole(UserRole);
  }

  private static void SetUpItemizedSales(DatabaseBackup databasebackup) {
    SalesLog ItemizedSales = new SalesLogImpl();
    ItemizedSales = mydb.getItemizedSalesH();
    databasebackup.setItimizedSales(ItemizedSales);
  }


  private static void SetUpItems(DatabaseBackup databasebackup) {
    List<Item> Items = new ArrayList<>();
    Items = mydb.getAllItemsH();
    databasebackup.setItems(Items);
  }

  private static void SetUpAccountSummary(DatabaseBackup databasebackup) {
    LinkedHashMap<Integer, List<Account>> AccountSummary =
        new LinkedHashMap<Integer, List<Account>>();
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      AccountSummary.put(user.getId(), mydb.getUserAccountsH(user.getId()));
    }
    databasebackup.setAccountSummary(AccountSummary);
  }

  public static void SetUpAccount(DatabaseBackup databasebackup) throws SQLException {
    Map<Integer, Account> accountTable = new LinkedHashMap<Integer, Account>();
    List<Account> allAccounts = new ArrayList<Account>();
    List<User> usersdetails = new ArrayList<User>();
    List<Account> activeAccounts = new ArrayList<Account>();
    List<Account> inactiveAccounts = new ArrayList<Account>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      activeAccounts = mydb.getUserActiveAccountsH(user.getId());
      for (Account activeAccount : activeAccounts) {
        Account account = new AccountImpl(user, true);
        accountTable.put(activeAccount.getId(), account);
      }
      inactiveAccounts = mydb.getUserInactiveAccountsH(user.getId());
      for (Account inactiveAccount : inactiveAccounts) {
        Account account = new AccountImpl(user, false);
        accountTable.put(inactiveAccount.getId(), account);
      }
    }
    TreeMap<Integer, Account> sorted = new TreeMap<>(accountTable);
    for (Map.Entry<Integer, Account> mapElement : sorted.entrySet()) {
      allAccounts.add(mapElement.getValue());
    }
    databasebackup.setAccount(allAccounts);
  }

  private static void SetUpRoles(DatabaseBackup databasebackup) {
    List<String> Roles = new ArrayList<String>();
    List<Integer> RoleIds = new ArrayList<Integer>();
    RoleIds = mydb.getRoleIdsH();
    for (Integer roleId : RoleIds) {
      Roles.add(mydb.getRoleNameH(roleId));
    }
    databasebackup.setRoles(Roles);
  }

  private static void SetUpInventory(DatabaseBackup databasebackup) {
    Inventory inventory = new InventoryImpl();
    inventory = mydb.getInventoryH();
    HashMap<Item, Integer> itemMap = inventory.getItemMap();
    LinkedHashMap<Integer, Integer> serializableItemMap = new LinkedHashMap<Integer, Integer>();
    for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
      serializableItemMap.put(entry.getKey().getId(), entry.getValue());
    }

    databasebackup.setInventory(serializableItemMap);
  }

  public static void SetUpEverything(DatabaseBackup databasebackup,
      DatabaseDriverAndroidHelper mydb1) throws SQLException {
    mydb = mydb1;
    SetUpUsers(databasebackup);
    SetUpUserPw(databasebackup);
    SetUpSales(databasebackup);
    SetUpUserRole(databasebackup);
    SetUpItemizedSales(databasebackup);
    SetUpItems(databasebackup);
    SetUpAccount(databasebackup);
    SetUpAccountSummary(databasebackup);
    SetUpRoles(databasebackup);
    SetUpInventory(databasebackup);

  }
}


