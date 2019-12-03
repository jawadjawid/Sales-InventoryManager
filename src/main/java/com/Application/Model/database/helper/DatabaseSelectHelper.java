package com.Application.Model.database.helper;

import com.Application.Model.database.DatabaseSelector;
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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseSelectHelper extends DatabaseSelector {

  public static List<Integer> getRoleIds() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRoles(connection);
    List<Integer> ids = new ArrayList<>();
    while (results.next()) {
      ids.add(results.getInt("ID"));
    }
    results.close();
    connection.close();
    return ids;
  }

  public static String getRoleName(int roleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String role = DatabaseSelector.getRole(roleId, connection);
    connection.close();
    return role;
  }

  public static int getUserRoleId(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int roleId = DatabaseSelector.getUserRole(userId, connection);
    connection.close();
    return roleId;
  }

  public static List<Integer> getUsersByRole(int roleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUsersByRole(roleId, connection);
    List<Integer> userIds = new ArrayList<>();
    while (results.next()) {
      userIds.add(results.getInt("USERID"));
    }
    results.close();
    connection.close();
    return userIds;
  }

  public static List<User> getUsersDetails() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUsersDetails(connection);
    int roleId;
    String role;

    List<User> users = new ArrayList<>();
    User user;
    while (results.next()) {
      roleId = DatabaseSelector.getUserRole(results.getInt("ID"), connection);
      role = DatabaseSelector.getRole(roleId, connection);
      user = createUser(results.getInt("ID"), results.getString("NAME"), results.getInt("AGE"),
          results.getString("ADDRESS"), role, roleId);
      users.add(user);
    }
    results.close();
    connection.close();
    return users;
  }

  public static User getUserDetails(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
    int roleId;
    String role;

    User user = null;
    while (results.next()) {
      roleId = DatabaseSelector.getUserRole(results.getInt("ID"), connection);
      role = DatabaseSelector.getRole(roleId, connection);
      user = createUser(results.getInt("ID"), results.getString("NAME"), results.getInt("AGE"),
          results.getString("ADDRESS"), role, roleId);
    }
    results.close();
    connection.close();
    return user;
  }

  private static User createUser(int id, String name, int age, String address, String role,
      int roleId) {
    if ("ADMIN".equals(Roles.valueOf(role))) {
      return new Admin(id, name, age, address, roleId);
    } else if ("EMPLOYEE".equals(Roles.valueOf(role))) {
      return new Employee(id, name, age, address, roleId);
    } else {
      return new Customer(id, name, age, address, roleId);
    }
  }

  public static String getPassword(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String password = DatabaseSelector.getPassword(userId, connection);
    connection.close();
    return password;
  }

  public static List<Item> getAllItems() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAllItems(connection);
    List<Item> items = new ArrayList<>();
    Item item;
    while (results.next()) {
      item = new ItemImpl(results.getInt("ID"), results.getString("NAME"),
          new BigDecimal(results.getString("PRICE")));
      items.add(item);
    }
    results.close();
    connection.close();
    return items;
  }

  public static Item getItem(int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItem(itemId, connection);

    Item item = null;
    while (results.next()) {
      item = new ItemImpl(results.getInt("ID"), results.getString("NAME"),
          new BigDecimal(results.getString("PRICE")));
    }
    results.close();
    connection.close();
    return item;
  }

  public static Inventory getInventory() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getInventory(connection);
    Item item;
    Inventory inventory = new InventoryImpl();
    while (results.next()) {
      item = DatabaseSelectHelper.getItem(results.getInt("ITEMID"));
      inventory.updateMap(item, results.getInt("QUANTITY"));
    }
    results.close();
    connection.close();
    return inventory;
  }

  public static int getInventoryQuantity(int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int quantity = DatabaseSelector.getInventoryQuantity(itemId, connection);
    connection.close();
    return quantity;
  }

  public static SalesLog getSales() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSales(connection);

    SalesLog salesLog = new SalesLogImpl();
    // double check this
    while (results.next()) {
      salesLog.addSale(new SaleImpl(results.getInt("ID"),
          DatabaseSelectHelper.getUserDetails(results.getInt("USERID")),
          new BigDecimal(results.getString("TOTALPRICE"))));
    }
    results.close();
    connection.close();
    return salesLog;
  }

  public static Sale getSaleById(int saleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSaleById(saleId, connection);
    Sale sale = null;
    while (results.next()) {
      User user = DatabaseSelectHelper.getUserDetails(results.getInt("USERID"));
      sale = new SaleImpl(results.getInt("ID"), user,
          new BigDecimal(results.getString("TOTALPRICE")));
    }
    results.close();
    connection.close();
    return sale;
  }

  public static List<Sale> getSalesToUser(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelectHelper.getSalesToUser(userId, connection);
    List<Sale> sales = new ArrayList<>();
    while (results.next()) {
      User user = DatabaseSelectHelper.getUserDetails(results.getInt("USERID"));
      sales.add(new SaleImpl(results.getInt("ID"), user,
          new BigDecimal(results.getString("TOTALPRICE"))));
    }
    results.close();
    connection.close();
    return sales;
  }

  public static Sale getItemizedSaleById(int saleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSaleById(saleId, connection);

    Sale sale = new ItemizedSaleImpl(results.getInt("SALEID"));
    while (results.next()) {
      Item item = DatabaseSelectHelper.getItem(results.getInt("ITEMID"));
      sale.getItemMap().put(item, results.getInt("QUANTITY"));
    }
    results.close();
    connection.close();
    return sale;
  }

  public static SalesLog getItemizedSales() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSales(connection);
    SalesLog salesLog = new SalesLogImpl();

    // double check
    while (results.next()) {
      Sale sale = salesLog.getSale(results.getInt("SALEID"));
      if (sale == null) {
        HashMap<Item, Integer> itemMap = new HashMap<>();
        itemMap.put(DatabaseSelectHelper.getItem(results.getInt("ITEMID")),
            results.getInt("QUANTITY"));
        salesLog.addSale(new ItemizedSaleImpl(results.getInt("SALEID"), itemMap));
      } else {
        sale.getItemMap().put(DatabaseSelectHelper.getItem(results.getInt("ITEMID")),
            results.getInt("QUANTITY"));
      }
    }
    results.close();
    connection.close();
    return salesLog;
  }

  public static boolean userIdExists(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
    boolean exists = false;
    while (results.next()) {
      exists = true;
    }
    results.close();
    connection.close();
    return exists;
  }

  public static ArrayList<Account> getUserAccounts(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserAccounts(userId, connection);
    ResultSet accountDetailsResult = null;
    User user = getUserDetails(userId);
    Account currentAccount = null;
    ArrayList<Account> accounts = new ArrayList<>();
    while (results.next()) {
      accountDetailsResult = DatabaseSelector.getAccountDetails(results.getInt("ID"), connection);
      currentAccount = new AccountImpl(results.getInt("ID"), user);
      while (accountDetailsResult.next()) {
        currentAccount.addItem(DatabaseSelectHelper.getItem(accountDetailsResult.getInt("ITEMID")),
            accountDetailsResult.getInt("QUANTITY"));
      }
      accounts.add(currentAccount);
    }
    results.close();
    connection.close();
    return accounts;
  }

}
