package com.Application.Model.store;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
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
        total = new BigDecimal("0.00");
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

    public boolean checkOut(DatabaseDriverAndroidHelper mydb) throws DatabaseInsertException {
        if (customer != null && checkInventory(mydb)) {
            int userId = customer.getId();
            int salesId = Math.toIntExact(mydb.insertSaleH(userId, total));

            Inventory inventory = mydb.getInventoryH();
            // update itemized sale and inventory
            for (Item item : items.keySet()) {
                if (items.get(item) > 0) {
                    Log.d("HAHA", "gotemmmm " + item.getId());
                    mydb.insertItemizedSaleH(salesId, item.getId(), items.get(item));
                    mydb.updateInventoryQuantityH(inventory.getItemMap().get(getInventoryVersion(inventory, item)) - items.get(item),
                            item.getId());
                }
            }
        }

        clearCart();
        return true;
    }

    private boolean checkInventory(DatabaseDriverAndroidHelper mydb) {
        Inventory inventory = mydb.getInventoryH();
        // check if there is enough of requested items in inventory
        Map<Item, Integer> inventoryItemMap = inventory.getItemMap();

        for (Item cartItem : items.keySet()) {
            for (Item inventoryItem : inventoryItemMap.keySet()) {
                if (cartItem.getId() == inventoryItem.getId()) {
                    if (items.get(cartItem) > inventoryItemMap.get(inventoryItem)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Item getInventoryVersion(Inventory inventory, Item item) {
        for (Item inventoryItem : inventory.getItemMap().keySet()) {
            if (inventoryItem.getId() == item.getId()) {
                return inventoryItem;
            }
        }
        return null;
    }

    public void clearCart() {
        items.clear();
    }
}
