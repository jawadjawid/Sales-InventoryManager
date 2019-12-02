package com.Application.Model.interaction;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Item;
import com.Application.Model.inventory.ItemImpl;
import com.Application.Model.store.Sale;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.store.SalesLog;
import com.Application.Model.users.Admin;
import com.Application.Model.users.Employee;
import com.Application.Model.users.Roles;
import com.Application.Model.users.User;

public class AdminInteraction extends UserInteraction {


	public static String viewBooks(DatabaseDriverAndroidHelper mydb) throws SQLException {
		StringBuilder books = new StringBuilder();

		SalesLog withPrice = mydb.getSalesH();
		SalesLog withHashMap = mydb.getItemizedSalesH();
		ArrayList<Sale> salesWithPrice = withPrice.getSales();
		ArrayList<Sale> salesWithHashMap = withHashMap.getSales();
		sortSalesList(salesWithPrice);
		sortSalesList(salesWithHashMap);

		BigDecimal allSalesTotal = new BigDecimal("0.00");
		HashMap<Item, Integer> totalItems = new HashMap<Item, Integer>();

		for (int i = 0; i < salesWithPrice.size(); i++) {
			if (mydb.getRoleNameH(salesWithPrice.get(i).getUser().getRoleId())
					.equals(Roles.CUSTOMER.toString())) {
				String name = salesWithPrice.get(i).getUser().getName();
				int saleId = salesWithPrice.get(i).getId();
				BigDecimal totalPrice = salesWithPrice.get(i).getTotalPrice();
				books.append("Customer: " + name + "\n");
				books.append("Purchase Number: " + saleId + "\n");
				books.append("Total Purchase Price: " + totalPrice + "\n");
				allSalesTotal = allSalesTotal.add(totalPrice);
				books.append("Itemized Breakdown: " + "\n");
				HashMap<Item, Integer> items = salesWithHashMap.get(i).getItemMap();
				for (Item e : items.keySet()) {
					int quantity = items.get(e);
					books.append(e + ": " + quantity + "\n");
					if (exists(totalItems, e)) {
						totalItems.put(e, totalItems.get(e) + quantity);
					} else {
						totalItems.put(e, quantity);
					}
				}
				books.append("----------------------------------------------------------------");
			}
		}

		HashMap<Item, Integer> noDuplicates = new HashMap<>();

		Item rod = new ItemImpl(1, "Fishing Rod", new BigDecimal("12.00"));
		Item stick = new ItemImpl(2, "Hockey Stick", new BigDecimal("8.50"));
		Item skate = new ItemImpl(3, "Skates", new BigDecimal("10.00"));
		Item shoes = new ItemImpl(4, "Running Shoes", new BigDecimal("15.00"));
		Item bar = new ItemImpl(5, "Protein Bar", new BigDecimal("3.00"));

		noDuplicates.put(rod, 0);
		noDuplicates.put(stick, 0);
		noDuplicates.put(skate, 0);
		noDuplicates.put(shoes, 0);
		noDuplicates.put(bar, 0);

		int quantity;
		for (Item j : noDuplicates.keySet()) {
			quantity = 0;
			for (Item i : totalItems.keySet()) {
				if (i.getId() == j.getId()) {
					quantity += totalItems.get(i);
				}
			}
			noDuplicates.put(j,quantity);
		}
		for (Item i : noDuplicates.keySet()) {
			books.append("Number of " + i.getName() + " Sold: " + noDuplicates.get(i) + "\n");

		}

		books.append("TOTAL SALES: " + allSalesTotal);

		return books.toString();
	}


	private static boolean exists(HashMap<Item, Integer> items, Item i) {
		return items.containsKey(i);
	}

	private static void sortSalesList(ArrayList<Sale> s) {
		Collections.sort(s, new Comparator<Sale>() {
			@Override
			public int compare(Sale o1, Sale o2) {
				return o1.getId() - o2.getId();
			}
		});
	}
}
