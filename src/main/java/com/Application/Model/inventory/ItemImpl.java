package com.b07.inventory;

import java.math.BigDecimal;

public class ItemImpl implements Item {

  private int id;
  private String name;
  private BigDecimal price;

  public ItemImpl(int id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public boolean equals(Object o) {
    return this.getId() == ((Item) o).getId();
  }
  
  public String toString() {
    return name + " (" + price + " ea)";
  }
}
