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
import com.Application.Model.store.SalesLog;
import com.Application.Model.store.SalesLogImpl;
import com.Application.Model.users.User;

public class DatabaseBackupSetUp {

  private static DatabaseDriverAndroidHelper mydb;

  public static void SetUpUserPw(DatabaseBackup databasebackup) throws SQLException {
    LinkedHashMap<Integer, String> userpw = new LinkedHashMap<Integer, String>();
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      userpw.put(user.getId(), mydb.getPasswordH(user.getId()));
    }
    databasebackup.setUserPw(userpw);
  }

  public static void SetUpSales(DatabaseBackup databasebackup) throws SQLException {
    SalesLog Sales = new SalesLogImpl();
    Sales = mydb.getSalesH();
    databasebackup.setSales(Sales);
  }

  public static void SetUpUsers(DatabaseBackup databasebackup) throws SQLException {
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    databasebackup.setUsers(usersdetails);
  }

  public static void SetUpUserRole(DatabaseBackup databasebackup) throws SQLException {
    LinkedHashMap<Integer, Integer> UserRole = new LinkedHashMap<Integer, Integer>();
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      UserRole.put(user.getId(), mydb.getUserRoleIdH(user.getId()));
    }
    databasebackup.setUserRole(UserRole);
  }

  public static void SetUpItimizedSales(DatabaseBackup databasebackup) throws SQLException {
    SalesLog ItimizedSales = new SalesLogImpl();
    ItimizedSales = DatabaseSelectHelper.getItemizedSales();
    databasebackup.setSales(ItimizedSales);
  }


  public static void SetUpItems(DatabaseBackup databasebackup) throws SQLException {
    List<Item> Items = new ArrayList<>();
    Items = mydb.getAllItemsH();
    databasebackup.setItems(Items);
  }

  public static void SetUpAccountSummary(DatabaseBackup databasebackup) throws SQLException {
    LinkedHashMap<Integer, List<Account>> AccountSummary =
        new LinkedHashMap<Integer, List<Account>>();
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = DatabaseSelectHelper.getUsersDetails();
    for (User user : usersdetails) {
      AccountSummary.put(user.getId(), DatabaseSelectHelper.getUserAccounts(user.getId()));
    }
    databasebackup.setAccountSummary(AccountSummary);
  }

  public static void SetUpAccount(DatabaseBackup databasebackup) throws SQLException {
    Map<String, Integer> accountTable = new LinkedHashMap<String, Integer>();
    List<User> usersdetails = new ArrayList<User>();
    List<Integer> userIds = new ArrayList<Integer>();
    usersdetails = DatabaseSelectHelper.getUsersDetails();
    for (User user : usersdetails) {
      for (Account account : DatabaseSelectHelper.getUserAccounts(user.getId())) {
        accountTable.put(Integer.toString(account.getId()), user.getId());
      }
    }
    TreeMap<String, Integer> sorted = new TreeMap<>();
    sorted.putAll(accountTable);
    for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
      userIds.add(entry.getValue());
    }
    databasebackup.setAccount(userIds);
  }

  public static void SetUpRoles(DatabaseBackup databasebackup) throws SQLException {
    List<String> Roles = new ArrayList<String>();
    List<Integer> RoleIds = new ArrayList<Integer>();
    RoleIds = mydb.getRoleIdsH();
    for (Integer roleId : RoleIds) {
      Roles.add(mydb.getRoleNameH(roleId));
    }
    databasebackup.setRoles(Roles);
  }

  public static void SetUpInventory(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
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
      DatabaseDriverAndroidHelper mydb1) throws SQLException, DatabaseInsertException {
    mydb = mydb1;
    SetUpUsers(databasebackup);
    SetUpUserPw(databasebackup);
    SetUpSales(databasebackup);
    SetUpUserRole(databasebackup);
    SetUpItimizedSales(databasebackup);
    SetUpItems(databasebackup);
    SetUpAccount(databasebackup);
    SetUpAccountSummary(databasebackup);
    SetUpRoles(databasebackup);
    SetUpInventory(databasebackup);
  }
}


