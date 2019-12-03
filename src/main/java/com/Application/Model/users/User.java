package com.Application.Model.users;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.security.PasswordHelpers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

public abstract class User implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 8826950644046688757L;
  private int id;
  private String name;
  private int age;
  private String address;
  private int roleId;
  private boolean authenticated;
  private BigDecimal balance;

  public User(int id, String name, int age, String address) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public User(int id, String name, int age, String address, int roleId, BigDecimal balance) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
    this.roleId = roleId;
    this.balance = balance;
  }

  public User(int id, String name, int age, String address, boolean authenticated, int roleId) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
    this.roleId = roleId;
  }

  public User(int id, String name, int age, String address, boolean authenticated) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
    this.authenticated = authenticated;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public final boolean authenticate(String password) throws SQLException {
    String dbPassword = DatabaseSelectHelper.getPassword(id);
    authenticated = PasswordHelpers.comparePassword(dbPassword, password);
    return authenticated;
  }

  public final boolean authenticate(String password, String dbPassword) {
    authenticated = PasswordHelpers.comparePassword(dbPassword, password);
    return authenticated;
  }

}
