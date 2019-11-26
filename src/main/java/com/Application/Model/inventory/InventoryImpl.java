package com.Application.Model.inventory;

import java.util.HashMap;

public class InventoryImpl implements Inventory {

  private HashMap<Item, Integer> itemMap;
  private int total;

  public InventoryImpl() {
    itemMap = new HashMap<Item, Integer>();
  }

  public HashMap<Item, Integer> getItemMap() {
    return itemMap;
  }

  public void setItemMap(HashMap<Item, Integer> itemMap) {
    this.itemMap = itemMap;
  }

  public void updateMap(Item item, Integer value) {
    itemMap.put(item, value);
  }

  public int getTotalItems() {
    return total;
  }

  public void setTotalItems(int total) {
    this.total = total;
  }

}
