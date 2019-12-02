package com.Application.Model.store;

import java.util.HashMap;

import com.Application.Model.inventory.Item;
import com.Application.Model.users.User;

public interface Account {

	public int getId();

	public User getUser();

	public HashMap<Item, Integer> getItemMap();

	public void setItemMap(HashMap<Item, Integer> itemMap);
	
	public void addItem(Item item, int quantity);
	
	public void removeItem(Item item, int quantity);
	
	public void setActive(boolean active);
	
	public boolean getActive();

}
