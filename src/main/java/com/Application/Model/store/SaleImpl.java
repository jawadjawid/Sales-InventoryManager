package com.Application.Model.store;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import com.Application.Model.inventory.Item;
import com.Application.Model.users.User;

public class SaleImpl implements Sale, Serializable {

  private int id;
  private User user;
  private BigDecimal total;
  private HashMap<Item, Integer> itemMap;

  public SaleImpl(int id, User user, BigDecimal total) {
    this.id = id;
    this.user = user;
    this.total = total;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BigDecimal getTotalPrice() {
    return total;
  }

  public void setTotalPrice(BigDecimal price) {
    total = price;
  }

  public HashMap<Item, Integer> getItemMap() {
    return null;
  }

  public void setItemMap(HashMap<Item, Integer> itemMap) {
  }
}
