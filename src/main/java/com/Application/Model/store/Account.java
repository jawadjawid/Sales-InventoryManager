package com.b07.store;

import java.util.HashMap;

import com.b07.inventory.Item;
import com.b07.users.User;

public interface Account {

	public int getId();

	public User getUser();

	public HashMap<Item, Integer> getItemMap();

	public void setItemMap(HashMap<Item, Integer> itemMap);
	
	public void addItem(Item item, int quantity);
	
	public void removeItem(Item item, int quantity);

}
