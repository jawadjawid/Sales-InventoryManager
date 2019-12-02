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
		SalesLog withHashMap =mydb.getItemizedSalesH();
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
				books.append("Customer: " + name);
				books.append("Purchase Number: " + saleId);
				books.append("Total Purchase Price: " + totalPrice);
				allSalesTotal = allSalesTotal.add(totalPrice);
				books.append("Itemized Breakdown: ");
				HashMap<Item, Integer> items = salesWithHashMap.get(i).getItemMap();
				for (Item e : items.keySet()) {
					int quantity = items.get(e);
					books.append(e + ": " + quantity);
					if (exists(totalItems, e)) {
						totalItems.put(e, totalItems.get(e) + quantity);
					} else {
						totalItems.put(e, quantity);
					}
				}
				books.append("----------------------------------------------------------------");
			}
		}

		for (Item i : totalItems.keySet()) {
			books.append("Number of " + i.getName() + " Sold: " + totalItems.get(i));
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
