package com.Application.Model.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseDeleter {

  public static void delete() {
    Statement statement = null;
    Connection connection = DatabaseDriver.connectOrCreateDataBase();
    try {
      statement = connection.createStatement();
      String sql = "DROP TABLE ROLES";
      statement.executeUpdate(sql);

      sql = "DROP TABLE USERS";
      statement.executeUpdate(sql);

      sql = "DROP TABLE USERROLE";
      statement.executeUpdate(sql);

      sql = "DROP TABLE USERPW";
      statement.executeUpdate(sql);

      sql = "DROP TABLE ITEMS";
      statement.executeUpdate(sql);

      sql = "DROP TABLE INVENTORY";
      statement.executeUpdate(sql);

      sql = "DROP TABLE SALES";
      statement.executeUpdate(sql);

      sql = "DROP TABLE ITEMIZEDSALES";
      statement.executeUpdate(sql);
      
      sql = "DROP TABLE ACCOUNT";
      statement.executeUpdate(sql);
      
      sql = "DROP TABLE ACCOUNTSUMMARY";
      statement.executeUpdate(sql);

      statement.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
