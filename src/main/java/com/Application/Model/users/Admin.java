package com.b07.users;

import java.sql.SQLException;
import java.util.List;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;

public class Admin extends User {

  public Admin(int id, String name, int age, String address) {
    super(id, name, age, address);
  }

  public Admin(int id, String name, int age, String address, int roleId) {
    super(id, name, age, address, roleId);
  }

  public Admin(int id, String name, int age, String address, boolean authenticated, int roleId) {
    super(id, name, age, address, authenticated, roleId);
  }

  public Admin(int id, String name, int age, String address, boolean authenticated) {
    super(id, name, age, address, authenticated);
  }

  public boolean promoteEmployee(Employee employee) throws DatabaseInsertException {
    try {
      // find role id belonging to admin
      List<Integer> roleIds = DatabaseSelectHelper.getRoleIds();
      int index = 0;

      while (!DatabaseSelectHelper.getRoleName(roleIds.get(index)).equals("ADMIN")) {
        index++;
      }
      DatabaseUpdateHelper.updateUserRole(roleIds.get(index), employee.getId());
      return true;
    } catch (SQLException | DatabaseInsertException e) {
      System.out.println("Database Error");
      return false;
    }
  }

}
