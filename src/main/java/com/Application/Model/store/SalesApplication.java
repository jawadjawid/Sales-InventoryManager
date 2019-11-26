package com.b07.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.b07.database.DatabaseDeleter;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.ConnectionFailedException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.interaction.AdminInteraction;
import com.b07.interaction.CustomerInteraction;
import com.b07.interaction.EmployeeInteraction;
import com.b07.interaction.UserInteraction;
import com.b07.inventory.Inventory;
import com.b07.inventory.ItemTypes;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.EmployeeInterface;
import com.b07.users.Roles;
import com.b07.users.User;

public class SalesApplication {

	private static BufferedReader bufferedReader;
	private static HashMap<String, Integer> rolesToId;

	// think about prices, remove it before submitting
	private static void insertIntoItemsTable() throws SQLException, DatabaseInsertException {
		ItemTypes[] values = ItemTypes.values();
		BigDecimal price;
		for (int i = 0; i < values.length; i++) {
			if (i % 2 == 0) {
				price = new BigDecimal("20.00");
			} else {
				price = new BigDecimal("40.00");
			}
			price.setScale(2);
			DatabaseInsertHelper.insertItem(values[i].name(), price);
		}
	}

	public static HashMap<String, Integer> getRolesToId() {
		return rolesToId;
	}

	// need
	private static void insertIntoRolesTable() throws DatabaseInsertException, SQLException {
		rolesToId = new HashMap<>();
		for (Roles role : Roles.values()) {
			rolesToId.put(role.name(), DatabaseInsertHelper.insertRole(role.name()));
		}
	}

	private static void initializeRolesToId() {
		try {
			rolesToId = new HashMap<>();
			int id = 0;
			String roleName = "";
			do {
				id++;
				roleName = DatabaseSelectHelper.getRoleName(id);
				rolesToId.put(roleName, id);
			} while (true);
		} catch (Exception e) {

		}
	}

	// return an arraylist containing integers from min to max as strings
	public static ArrayList<String> getChoices(int min, int max) {
		ArrayList<String> choices = new ArrayList<>();
		for (int i = min; i <= max; i++) {
			choices.add(i + "");
		}
		return choices;
	}

	public static void main(String[] argv) throws NumberFormatException, IOException {
		Connection connection = DatabaseDriverExtender.connectOrCreateDataBase();
		if (connection == null) {
			System.out.print("NOOO");
		}
		try {
			String initialArg = argv.length == 1 ? argv[0] : "";
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String input = "";
			if (initialArg.equals("-1")) {
				DatabaseDeleter.delete();
				DatabaseDriverExtender.initialize(connection);
				insertIntoRolesTable();
				initializeRolesToId();
				insertIntoItemsTable(); // inserts items into items table with random prices
				UserInteraction.createFirstRunAccounts(bufferedReader, rolesToId);
				
				// think abt if error occurs at inserting user role then in finally should remove from users
			} else if (initialArg.equals("1")) {
				initializeRolesToId();
				User preUser = UserInteraction.logIn(bufferedReader, "*Admin Mode*", Roles.ADMIN);
				if (preUser != null) {
					AdminInteraction.performAdminOptions(bufferedReader, rolesToId, preUser);
				}
			} else {
				initializeRolesToId();
				String mainMenuOptions = "Menu\r\n" + "1 - Employee Login\r\n" + "2 - Customer Login\r\n"
						+ "0 - Exit\r\n";
				String mainMenuChoice = UserInteraction.userOptions(bufferedReader, mainMenuOptions, getChoices(0, 2));
				if (mainMenuChoice.equals("1")) {
					User preUser = UserInteraction.logIn(bufferedReader, "Employee Login", Roles.EMPLOYEE);
					if (preUser != null) {

						// if employee login successful
						Employee employee = new Employee(preUser.getId(), preUser.getName(), preUser.getAge(),
								preUser.getAddress(), true, rolesToId.get(Roles.EMPLOYEE.name()));

						Inventory inventory = DatabaseSelectHelper.getInventory();
						EmployeeInterface emIn = new EmployeeInterface(employee, inventory);

						EmployeeInteraction.performEmployeeOptions(bufferedReader, rolesToId, emIn);
					}
				} else if (mainMenuChoice.equals("2")) {
					User preUser;
					while ((preUser = UserInteraction.logIn(bufferedReader, "Customer Login",
							Roles.CUSTOMER)) == null) {
						System.out.println("Incorrect. Try again.");
					}
					// if customer login successful
					Customer customer = new Customer(preUser.getId(), preUser.getName(), preUser.getAge(),
							preUser.getAddress(), true, rolesToId.get(Roles.CUSTOMER.name()));

					ShoppingCart cart = new ShoppingCart(customer);

					// ask them if they want to revert to an old shopping cart
					CustomerInteraction.restoreShoppingCart(bufferedReader, cart);
					
					String customerOptions = "1. List current items in cart\r\n"
							+ "2. Add a quantity of an item to the cart\r\n"
							+ "3. Check total price of items in the cart\r\n"
							+ "4. Remove a quantity of an item from the cart\r\n" + "5. Check out\r\n" + "6. Exit";
					CustomerInteraction.performCustomerOptions(bufferedReader, rolesToId, cart, customerOptions);
				}
			}
		} catch (NullPointerException | ConnectionFailedException | SQLException | DatabaseInsertException e) {
			e.printStackTrace();
			System.out.println("Something went wrong :(");
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println("Looks like it was closed already :)");
			}
		}

	}
}
