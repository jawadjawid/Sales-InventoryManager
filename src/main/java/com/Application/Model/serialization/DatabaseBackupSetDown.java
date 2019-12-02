package com.Application.Model.serialization;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.Application.Model.database.helper.DatabaseInsertHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Item;
import com.Application.Model.inventory.ItemTypes;
import com.Application.Model.store.Account;
import com.Application.Model.store.Sale;
import com.Application.Model.store.SalesLog;
import com.Application.Model.store.SalesLogImpl;
import com.Application.Model.users.Roles;
import com.Application.Model.users.User;
import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.InventoryImpl;


public class DatabaseBackupSetDown {
   
  private static DatabaseDriverAndroidHelper mydb;

  public static void SetDownUserPw(DatabaseBackup databasebackup) throws SQLException {
    LinkedHashMap<Integer, String> userpw = new LinkedHashMap<Integer, String>();
    userpw = databasebackup.getUserPw();
    for (Map.Entry<Integer, String> entry : userpw.entrySet()) {
    	mydb.insertPasswordH(entry.getValue(), entry.getKey());
    }
  }

  public static void SetDownSales(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    SalesLog salesLogImpl = new SalesLogImpl();
    salesLogImpl = databasebackup.getSales();
    ArrayList<Sale> Sales = salesLogImpl.getSales();
    for (Sale sale : Sales) {
    	mydb.insertSaleH((sale.getUser()).getId(), sale.getTotalPrice());
    }
  }

  public static void SetDownUsers(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    List<User> usersdetails = new ArrayList<User>();
    usersdetails = databasebackup.getUsers();
    for (User user : usersdetails) {
    	mydb.insertNewUserNoPasswordH(user.getName(), user.getAge(),
          user.getAddress());
    }
  }

  public static void SetDownUserRole(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    LinkedHashMap<Integer, Integer> UserRole = new LinkedHashMap<Integer, Integer>();
    UserRole = databasebackup.getUserRole();
    for (Map.Entry<Integer, Integer> entry : UserRole.entrySet()) {
    	mydb.insertUserRoleH(entry.getKey(), entry.getValue());
    }
  }

  public static void SetDownItimizedSales(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    SalesLog ItimizedSales = new SalesLogImpl();
    ItimizedSales = databasebackup.getItimizedSales();
    ArrayList<Sale> Sales = new ArrayList<Sale>();
    Sales = ItimizedSales.getSales();
    for (Sale sale : Sales) {
      for (Map.Entry<Item, Integer> entry : (sale.getItemMap()).entrySet()) {
    	  mydb.insertItemizedSaleH(sale.getId(), (entry.getKey()).getId(),
            entry.getValue());
      }
    }
  }

  public static void SetDownItems(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    List<Item> Items = new ArrayList<>();
    Items = databasebackup.getItems();
    for (Item item : Items) {
      boolean allowed = false;
      for (ItemTypes item1 : ItemTypes.values()) {
        if (item1.name().equals(item.getName())) {
          allowed = true;
        }
      }
      if (allowed) {
    	  mydb.insertItemH(item.getName(), item.getPrice());
      } else {
        throw new DatabaseInsertException();
      }
    }

  }


  public static void SetDownAccountSummary(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    LinkedHashMap<Integer, List<Account>> AccountSummary =
        new LinkedHashMap<Integer, List<Account>>();
    AccountSummary = databasebackup.getAccountSummary();
    for (Map.Entry<Integer, List<Account>> entry : AccountSummary.entrySet()) {
      for (Account account : entry.getValue()) {
        for (Map.Entry<Item, Integer> item1 : account.getItemMap().entrySet()) {
          DatabaseInsertHelper.insertAccountSummary(account.getId(), item1.getKey().getId(),
              item1.getValue());
        }
      }
    }
  }

  public static void SetDownAccount(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    List<Integer> userIds = databasebackup.getAccount();
    for (Integer userId : userIds) {
      DatabaseInsertHelper.insertAccount(userId);
    }
  }

  public static void SetDownRoles(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    List<String> roles = new ArrayList<String>();
    roles = databasebackup.getRoles();
    for (String roleName : roles) {
      boolean allowed = false;
      for (Roles role : Roles.values()) {
        if (role.name().equals(roleName)) {
          allowed = true;
        }
      }
      if (allowed) {
    	  mydb.insertRoleH(roleName);
      } else {
        throw new DatabaseInsertException();
      }
    }
  }

  public static void SetDownInventory(DatabaseBackup databasebackup)
      throws SQLException, DatabaseInsertException {
    LinkedHashMap<Integer, Integer> inventory = new LinkedHashMap<Integer, Integer>();
    inventory = databasebackup.getInventory();
    for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
    	mydb.insertInventoryH(entry.getKey(), entry.getValue());
    }
  }

  public static void SetDownEverything(DatabaseBackup databasebackup, DatabaseDriverAndroidHelper mydb1)
      throws SQLException, DatabaseInsertException {
	  
	mydb = mydb1;  
    SetDownUsers(databasebackup);
    SetDownSales(databasebackup);
    SetDownRoles(databasebackup);
    SetDownUserPw(databasebackup);
    SetDownUserRole(databasebackup);
    SetDownItimizedSales(databasebackup);
    SetDownItems(databasebackup);
    SetDownAccount(databasebackup);
    SetDownAccountSummary(databasebackup);
    SetDownInventory(databasebackup);
    System.out.println("Serialized data successfully restored");
  }

}
