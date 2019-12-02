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

  private static void SetUpUserPw(DatabaseBackup databasebackup)  {
    LinkedHashMap<Integer, String> userpw = new LinkedHashMap<Integer, String>();
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      userpw.put(user.getId(), mydb.getPasswordH(user.getId()));
    }
    databasebackup.setUserPw(userpw);
  }

  private static void SetUpSales(DatabaseBackup databasebackup)  {
    SalesLog Sales = new SalesLogImpl();
    Sales = mydb.getSalesH();
    databasebackup.setSales(Sales);
  }

  private static void SetUpUsers(DatabaseBackup databasebackup) {
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = mydb.getUsersDetailsH();
    databasebackup.setUsers(usersdetails);
  }

  private static void SetUpUserRole(DatabaseBackup databasebackup)  {
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
    databasebackup.setSales(ItemizedSales);
  }


  private static void SetUpItems(DatabaseBackup databasebackup)  {
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

  private static void SetUpAccount(DatabaseBackup databasebackup)  {
    Map<String, Integer> accountTable = new LinkedHashMap<String, Integer>();
    List<User> usersdetails = new ArrayList<User>();
    List<Integer> userIds = new ArrayList<Integer>();
    usersdetails = mydb.getUsersDetailsH();
    for (User user : usersdetails) {
      for (Account account : mydb.getUserAccountsH(user.getId())) {
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

  private static void SetUpRoles(DatabaseBackup databasebackup)  {
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
      DatabaseDriverAndroidHelper mydb1) {
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


