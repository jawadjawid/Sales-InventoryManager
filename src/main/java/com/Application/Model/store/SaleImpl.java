package com.b07.store;

import java.math.BigDecimal;
import java.util.HashMap;
import com.b07.inventory.Item;
import com.b07.users.User;

public class SaleImpl implements Sale {

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
