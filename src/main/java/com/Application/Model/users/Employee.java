package com.Application.Model.users;

public class Employee extends User {

  public Employee(int id, String name, int age, String address) {
    super(id, name, age, address);
  }

  public Employee(int id, String name, int age, String address, int roleId) {
    super(id, name, age, address, roleId);
  }

  public Employee(int id, String name, int age, String address, boolean authenticated, int roleId) {
    super(id, name, age, address, authenticated, roleId);
  }

  public Employee(int id, String name, int age, String address, boolean authenticated) {
    super(id, name, age, address, authenticated);
  }
}
