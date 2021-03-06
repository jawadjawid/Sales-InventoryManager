package com.Application.Model.inventory;

import java.io.Serializable;
import java.util.HashMap;

public interface Inventory extends Serializable {

  public HashMap<Item, Integer> getItemMap();

  public void setItemMap(HashMap<Item, Integer> itemMap);

  public void updateMap(Item item, Integer value);

  public int getTotalItems();

  public void setTotalItems(int total);

}
