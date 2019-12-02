package com.Application.Model.users;

import com.Application.Model.store.SalesApplication;

import java.math.BigDecimal;

public class Customer extends User {


  public Customer(int id, String name, int age, String address) {
    super(id, name, age, address);
  }

  public Customer(int id, String name, int age, String address, int roleId) {
    super(id, name, age, address, roleId, SalesApplication.INITIAL_BALANCE);
  }

  public Customer(int id, String name, int age, String address, int roleId, BigDecimal balance) {
    super(id, name, age, address, roleId, balance);
  }

  public Customer(int id, String name, int age, String address, boolean authenticated, int roleId) {
    super(id, name, age, address, authenticated, roleId);
  }

  public Customer(int id, String name, int age, String address, boolean authenticated) {
    super(id, name, age, address, authenticated);
  }
}
