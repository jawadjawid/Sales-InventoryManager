package com.b07.users;

public class Customer extends User {

  public Customer(int id, String name, int age, String address) {
    super(id, name, age, address);
  }

  public Customer(int id, String name, int age, String address, int roleId) {
    super(id, name, age, address, roleId);
  }

  public Customer(int id, String name, int age, String address, boolean authenticated, int roleId) {
    super(id, name, age, address, authenticated, roleId);
  }

  public Customer(int id, String name, int age, String address, boolean authenticated) {
    super(id, name, age, address, authenticated);
  }
}
