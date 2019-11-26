package com.b07.store;

import java.util.HashMap;

import com.b07.inventory.Item;
import com.b07.users.User;

public class AccountImpl implements Account {
	private int id;
	private User user;
	private HashMap<Item, Integer> itemMap;

	public AccountImpl(int id, User user) {
		this.id = id;
		itemMap = new HashMap<>();
		this.user = user;
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

}
