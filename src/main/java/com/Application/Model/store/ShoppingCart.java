package com.Application.Model.store;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Application.Model.database.helper.DatabaseInsertHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.database.helper.DatabaseUpdateHelper;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.Item;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.users.Customer;

public class ShoppingCart implements Serializable {

	private final BigDecimal TAXRATE = new BigDecimal("1.13");
	private HashMap<Item, Integer> items;
	private Customer customer;
	private BigDecimal total;

	public ShoppingCart(Customer customer) {
		items = new HashMap<>();
		this.customer = customer;
		total = new BigDecimal("0.00");
	}

	public void addItem(Item item, int quantity) {
		Item oldItem = null;
		for (Item curItem : items.keySet()) {
			if (curItem.getId() == item.getId()) {
				oldItem = curItem;
			}
		}
		if (oldItem != null) {
			// item exists
			items.put(oldItem, items.get(oldItem) + quantity);
			total = total.add(oldItem.getPrice().multiply(new BigDecimal(quantity)));
		} else {
			items.put(item, quantity);
			total = total.add(item.getPrice().multiply(new BigDecimal(quantity)));
		}
		total.setScale(2);
	}

	public void removeItem(Item item, int quantity) throws DatabaseInsertException {

		Item oldItem = null;
		for (Item curItem : items.keySet()) {
			if (curItem.getId() == item.getId()) {
				oldItem = curItem;
			}
		}
		if (oldItem != null) {
			// item exists
			int rem = items.get(oldItem);
			if (rem == quantity) {
				items.remove(oldItem);
			} else if (rem > quantity) {
				items.put(oldItem, rem - quantity);
			} else {
				// insufficient items in cart
				throw new DatabaseInsertException();
			}
			total = total.subtract(item.getPrice().multiply(new BigDecimal(quantity)));
			total.setScale(2);
		} else {
			// item does not exist in cart at all
			throw new DatabaseInsertException();
		}
	}

	public List<Item> getItems() {
		List<Item> onlyItems = new ArrayList<>();
		onlyItems.addAll(items.keySet());
		return onlyItems;
	}

	public HashMap<Item, Integer> getItemMap() {
		return items;
	}

	public void setItemMap(HashMap<Item, Integer> itemMap) {
		items = itemMap;
		for (Item item : itemMap.keySet()) {
			total = total.add(item.getPrice().multiply(new BigDecimal(itemMap.get(item))));
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getTaxRate() {
		return TAXRATE;
	}

	public boolean checkOut() throws DatabaseInsertException, SQLException {
		if (customer != null) {
			int userId = customer.getId();
			int salesId = DatabaseInsertHelper.insertSale(userId, total);

			Inventory inventory = DatabaseSelectHelper.getInventory();
			// check if there is enough of requested items in inventory
			Map<Item, Integer> inventoryItemMap = inventory.getItemMap();
			Item oldItem = null;
			for (Item item : items.keySet()) {
				oldItem = null;
				for (Item curItem : inventoryItemMap.keySet()) {
					if (curItem.getId() == item.getId()) {
						oldItem = curItem;
					}
				}
				if (oldItem != null) {
					if (items.get(item) > inventoryItemMap.get(oldItem)) {
						// inventory has lower quantity than cart of the given item
						throw new DatabaseInsertException();
					}
				} else {
					// the item does not exist in inventory at all so throw exception
					throw new DatabaseInsertException();
				}
			}
			// update itemized sale and inventory if enough requested items exist
			for (Item item : items.keySet()) {
				DatabaseInsertHelper.insertItemizedSale(salesId, item.getId(), items.get(item));
				oldItem = null;
				for (Item curItem : inventoryItemMap.keySet()) {
					if (curItem.getId() == item.getId()) {
						oldItem = curItem;
					}
				}
				if (oldItem != null) {
					// item exists in inventory
					DatabaseUpdateHelper.updateInventoryQuantity(inventory.getItemMap().get(oldItem) - items.get(item),
							item.getId());
				} else {
					// item does not exist in inventory
					throw new DatabaseInsertException();
				}
			}
			
			
			BigDecimal withTax = total.multiply(TAXRATE);
			withTax.setScale(2);
			System.out.println("Your total is: " + withTax);
			clearCart();
			return true;
		}
		return false;
	}

	public void clearCart() {
		items.clear();
	}
}
