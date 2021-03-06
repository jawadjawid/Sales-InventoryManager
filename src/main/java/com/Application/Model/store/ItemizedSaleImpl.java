package com.Application.Model.store;

import com.Application.Model.inventory.Item;
import com.Application.Model.users.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

public class ItemizedSaleImpl implements Sale, Serializable {

  private int id;
  private BigDecimal total;
  private HashMap<Item, Integer> itemMap;
  private User user;

  public ItemizedSaleImpl(int id, HashMap<Item, Integer> itemMap) {
    this.id = id;
    this.itemMap = itemMap;
  }

  public ItemizedSaleImpl(int id) {
    this.id = id;
    itemMap = new HashMap<>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return null;
  }

  public void setUser(User user) {
  }

  public BigDecimal getTotalPrice() {
    return null;
  }

  public void setTotalPrice(BigDecimal price) {

  }

  public HashMap<Item, Integer> getItemMap() {
    return itemMap;
  }

  public void setItemMap(HashMap<Item, Integer> itemMap) {
    this.itemMap = itemMap;
  }

}
