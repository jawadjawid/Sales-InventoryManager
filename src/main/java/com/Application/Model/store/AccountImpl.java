package com.Application.Model.store;

import java.io.Serializable;
import java.util.HashMap;

import com.Application.Model.inventory.Item;
import com.Application.Model.users.User;

public class AccountImpl implements Account, Serializable {

  private int id;
  private User user;
  private HashMap<Item, Integer> itemMap;
  private boolean active;

  public AccountImpl(int id, User user) {
    this.id = id;
    itemMap = new HashMap<>();
    this.user = user;
  }

  public AccountImpl(User user, boolean active) {
    this.user = user;
    this.active = active;
  }


  public int getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public HashMap<Item, Integer> getItemMap() {
    return itemMap;
  }

  public void setItemMap(HashMap<Item, Integer> itemMap) {
    this.itemMap = itemMap;

  }

  public void addItem(Item item, int quantity) {
    itemMap.put(item, quantity);
  }

  public void removeItem(Item item, int quantity) {
    itemMap.remove(item);
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

}
