package com.Application.Model.users;

import com.Application.Model.database.helper.DatabaseInsertHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.database.helper.DatabaseUpdateHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.Item;

import java.sql.SQLException;
import java.util.List;

public class EmployeeInterface {

  private Employee employee;
  private Inventory inventory;

  public EmployeeInterface(Employee employee, Inventory inventory) {
    this.employee = employee;
    this.inventory = inventory;
  }

  public EmployeeInterface(Inventory inventory) {
    this.inventory = inventory;
    employee = null;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setCurrentEmployee(Employee employee) {
    this.employee = employee;
  }

  public boolean hasCurrentEmployee() {
    return employee != null;
  }

  public boolean restockInventory(Item item, int quantity) {
    try {
      Item oldItem = null;
      for (Item curItem : inventory.getItemMap().keySet()) {
        if (curItem.getId() == item.getId()) {
          oldItem = curItem;
        }
      }
      if (oldItem != null) {
        // quantity of this item already exists
        inventory.getItemMap().put(oldItem, quantity);
        DatabaseUpdateHelper
            .updateInventoryQuantity(inventory.getItemMap().get(oldItem), oldItem.getId());
      } else {
        // item is not in inventory
        inventory.getItemMap().put(item, quantity);
        DatabaseInsertHelper.insertInventory(item.getId(), quantity);
      }
      return true;
    } catch (DatabaseInsertException e2) {
      System.out.println("Cannot Restock Inventory at this time.");
      return false;
    } catch (SQLException e3) {
      System.out.println("Database Error.");
      return false;
    }
  }

  public int createCustomer(String name, int age, String address, String password)
      throws DatabaseInsertException, SQLException {
    // add to users
    int userId = DatabaseInsertHelper.insertNewUser(name, age, address, password);
    List<Integer> roleIds = DatabaseSelectHelper.getRoleIds();
    int index = 0;
    while (!DatabaseSelectHelper.getRoleName(roleIds.get(index)).equals("CUSTOMER")) {
      index++;
    }

    // add to userrole
    DatabaseInsertHelper.insertUserRole(userId, roleIds.get(index));
    return userId;
  }

  public int createEmployee(String name, int age, String address, String password)
      throws SQLException, DatabaseInsertException {
    // add to users
    int userId = DatabaseInsertHelper.insertNewUser(name, age, address, password);
    List<Integer> roleIds = DatabaseSelectHelper.getRoleIds();
    int index = 0;
    while (!DatabaseSelectHelper.getRoleName(roleIds.get(index)).equals("EMPLOYEE")) {
      index++;
    }

    // add to userrole
    DatabaseInsertHelper.insertUserRole(userId, roleIds.get(index));
    return userId;
  }

}
