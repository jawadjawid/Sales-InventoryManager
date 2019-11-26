package com.b07.interaction;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.store.Sale;
import com.b07.store.SalesApplication;
import com.b07.store.SalesLog;
import com.b07.users.Admin;
import com.b07.users.Employee;
import com.b07.users.Roles;
import com.b07.users.User;

public class AdminInteraction extends UserInteraction {

	// promote employee in preUser to Admin
	public static void promoteEmployee(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId,
			User preUser) {
		try {
			String promoteEmployeeOptions = "Enter the id of the employee to promote. Enter 0 to exit.";
			ArrayList<String> employeeIdChoices = getAllIds(rolesToId, Roles.EMPLOYEE);
			// to add option of exiting
			employeeIdChoices.add("0");
			String promoteEmployeeChoice = userOptions(bufferedReader, promoteEmployeeOptions, employeeIdChoices);
			if (!promoteEmployeeChoice.equals("0")) {
				User preUser2 = DatabaseSelectHelper.getUserDetails(Integer.parseInt(promoteEmployeeChoice));
				// convert user to employee
				Employee employee = new Employee(preUser2.getId(), preUser2.getName(), preUser2.getAge(),
						preUser2.getAddress(), true, rolesToId.get(Roles.EMPLOYEE.name()));

				// convert user to admin
				Admin admin1 = new Admin(preUser.getId(), preUser.getName(), preUser.getAge(), preUser.getAddress(),
						true, rolesToId.get(Roles.ADMIN.name()));
				if (!admin1.promoteEmployee(employee)) {
					throw new DatabaseInsertException();
				} else {
					System.out.println("Employee promotion successful!");
				}

			}
		} catch (NumberFormatException | DatabaseInsertException | SQLException e) {
			System.out.println("Database Error.");
		}
	}

	public static void performAdminOptions(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId,
			User preUser) throws SQLException {
		String adminOptions = "Menu\n" + "0 - Exit\n" + "1 - Promote Employee\n" + "2 - View All Sales";
		String adminChoice = userOptions(bufferedReader, adminOptions, SalesApplication.getChoices(0, 2));

		if (adminChoice.equals("1")) {
			promoteEmployee(bufferedReader, rolesToId, preUser);
		} else{
			viewBooks();
		} 
	}

	public static void viewBooks() throws SQLException {
		SalesLog withPrice = DatabaseSelectHelper.getSales();
		SalesLog withHashMap = DatabaseSelectHelper.getItemizedSales();
		ArrayList<Sale> salesWithPrice = withPrice.getSales();
		ArrayList<Sale> salesWithHashMap = withHashMap.getSales();
		sortSalesList(salesWithPrice);
		sortSalesList(salesWithHashMap);

		BigDecimal allSalesTotal = new BigDecimal("0.00");
		HashMap<Item, Integer> totalItems = new HashMap<Item, Integer>();

		for (int i = 0; i < salesWithPrice.size(); i++) {
			if (DatabaseSelectHelper.getRoleName(salesWithPrice.get(i).getUser().getRoleId())
					.equals(Roles.CUSTOMER.toString())) {
				String name = salesWithPrice.get(i).getUser().getName();
				int saleId = salesWithPrice.get(i).getId();
				BigDecimal totalPrice = salesWithPrice.get(i).getTotalPrice();
				System.out.println("Customer: " + name);
				System.out.println("Purchase Number: " + saleId);
				System.out.println("Total Purchase Price: " + totalPrice);
				allSalesTotal = allSalesTotal.add(totalPrice);
				System.out.println("Itemized Breakdown: ");
				HashMap<Item, Integer> items = salesWithHashMap.get(i).getItemMap();
				for (Item e : items.keySet()) {
					int quantity = items.get(e);
					System.out.println(e + ": " + quantity);
					if (exists(totalItems, e)) {
						totalItems.put(e, totalItems.get(e) + quantity);
					} else {
						totalItems.put(e, quantity);
					}
				}
				System.out.println("----------------------------------------------------------------");
			}
		}

		for (Item i : totalItems.keySet()) {
			System.out.println("Number of " + i.getName() + " Sold: " + totalItems.get(i));
		}
		System.out.println("TOTAL SALES: " + allSalesTotal);

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
