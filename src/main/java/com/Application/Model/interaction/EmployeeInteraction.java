package com.Application.Model.interaction;

import com.Application.Model.database.helper.DatabaseInsertHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.Item;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.users.Employee;
import com.Application.Model.users.EmployeeInterface;
import com.Application.Model.users.Roles;
import com.Application.Model.users.User;

import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class EmployeeInteraction extends UserInteraction {

	// display options to employee and perform the desired operation based on user
	// input
	// keep looping through options until a 6 is entered
	public static void performEmployeeOptions(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId,
			EmployeeInterface emIn) throws DatabaseInsertException, SQLException {
		String employeeChoice;
		String employeeOptions = "Choose any of the following options:\r\n"
				+ "1. Authenticate new employee\r\n" + "2. Make new user\r\n"
				+ "3. Make new account\r\n" + "4. Make new employee\r\n" + "5. Restock inventory\r\n"
				+ "6. Exit";
		do {
			employeeChoice = userOptions(bufferedReader, employeeOptions, SalesApplication.getChoices(1, 6));

			if (employeeChoice.equals("1")) {
				authenticateNewEmployee(bufferedReader, rolesToId, emIn);
			} else if (employeeChoice.equals("2")) {
				System.out.println("Time to create a new customer.");
				createAccount(bufferedReader, rolesToId, Roles.CUSTOMER, emIn);
			} else if (employeeChoice.equals("3")) {
				registerNewCustomerAccount(bufferedReader, rolesToId);
			} else if (employeeChoice.equals("4")) {
				System.out.println("Time to create a new employee.");
				createAccount(bufferedReader, rolesToId, Roles.EMPLOYEE, emIn);
			} else if (employeeChoice.equals("5")) {
				displayItemsInInventory(emIn.getInventory());
				restockInventory(bufferedReader, rolesToId, emIn.getInventory(), emIn);
			}
		} while (!employeeChoice.equals("6"));
	}

	public static void registerNewCustomerAccount(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId)
			throws DatabaseInsertException, SQLException {

		String customerIdOptions = "Enter the id of the customer to associate an account to.";

		int chosenCustomerId = Integer.parseInt(userOptions(bufferedReader, customerIdOptions,
				getAllIds(rolesToId,Roles.CUSTOMER)));
		int accountId = DatabaseInsertHelper.insertAccount(chosenCustomerId);
		System.out.println("An account was created for the customer with the account id " + accountId);

	}

	// authenticates employee by allowing them to enter id and password and checking
	// whether given password matches the password in the database
	public static void authenticateNewEmployee(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId,
			EmployeeInterface emIn) {
		// pick new employee id
		// type its pass
		User preUser = logIn(bufferedReader, "Employee MainLoginView", Roles.EMPLOYEE);
		if (preUser != null) {
			// if employee login successful
			// convert user to employee
			Employee employee = new Employee(preUser.getId(), preUser.getName(), preUser.getAge(), preUser.getAddress(),
					true, rolesToId.get(Roles.EMPLOYEE.name()));
			emIn.setCurrentEmployee(employee);
			System.out.println("Authentication Successful! \n\n");
		}
	}

	// restocks inventory by allowing employee to view items, give an item id and
	// quantity as input in order to specify the restocking options
	public static void restockInventory(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId,
										Inventory inventory, EmployeeInterface emIn) {
		try {
			String itemNameOptions = "Choose one of the following items:\r\n";
			List<Item> items = DatabaseSelectHelper.getAllItems();

			// Display available items
			int choiceNum = 0;
			for (int i = 0; i < items.size(); i++) {
				choiceNum++;
				itemNameOptions += (choiceNum + ". " + items.get(i).getName() + "\r\n");
			}
			itemNameOptions += (choiceNum + 1) + ". Exit\r\n";

			// Choose item to restock
			String itemChoice;
			itemChoice = userOptions(bufferedReader, itemNameOptions, SalesApplication.getChoices(0, choiceNum + 1));

			// Choose quantity
			// Chosen item is items.get(itemChoice-1)
			if (Integer.parseInt(itemChoice) != (choiceNum + 1)) {
				String newQuantityInstruction = "Choose new quantity";

				int newQuantityChoice = userOptionsNums(bufferedReader, newQuantityInstruction);
				if (!emIn.restockInventory(items.get(Integer.parseInt(itemChoice) - 1), newQuantityChoice)) {
					throw new SQLException();
				}
			}
		} catch (SQLException e) {
			System.out.println("Database Error.");
		}
	}

	public static void displayItemsInInventory(Inventory inventory) {
		HashMap<Item, Integer> itemToQuantity = inventory.getItemMap();
		System.out.println("Current Availiability");
		System.out.println("Item Name - Quantity");
		for (Item item : itemToQuantity.keySet()) {
			System.out.println(item.getName() + " - " + itemToQuantity.get(item));
		}
	}

}
