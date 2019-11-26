package com.Application.Model.interaction;

import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.Application.Model.database.helper.DatabaseInsertHelper;
import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Item;
import com.Application.Model.store.Account;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.store.ShoppingCart;

public class CustomerInteraction extends UserInteraction {

	public static void displayItemsInCart(ShoppingCart cart) {
		List<Item> currItems = cart.getItems();
		System.out.println("Current Items in Cart.");
		for (Item item : currItems) {
			System.out.println("Item Name: " + item.getName() + "; Item Price: " + item.getPrice() + ";");
		}
	}

	// modifies item in cart where item and quantity to be changed is specified by
	// user input and message specifies whether the quantity is added or removed
	// from cart
	public static void modifyCartItems(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId,
			ShoppingCart cart, String message) {
		try {
			ArrayList<String> itemNameChoices = new ArrayList<String>();
			List<Item> allItems = DatabaseSelectHelper.getAllItems();
			for (Item item : allItems) {
				itemNameChoices.add(item.getId() + "");
			}
			String itemChoice = userOptions(bufferedReader, "Choose item", itemNameChoices);

			int quantity = userOptionsNums(bufferedReader, message);

			Item item = DatabaseSelectHelper.getItem(Integer.parseInt(itemChoice));
			if (message.equals("Enter quantity to add")) {
				cart.addItem(item, quantity);
			} else {
				cart.removeItem(item, quantity);
			}
		} catch (SQLException e1) {
			System.out.println("Database Error.\n\n");
		} catch (DatabaseInsertException e2) {
			System.out.println("Insufficient items in Cart for removal.\n\n");
		}
	}

	// ask the user if they want to continue shopping, based on their answer and
	// whether checkout was completed successfully, "6" or "5" is returned where "6"
	// indicates that the context menu for the customer is closed
	// NOTE: this method is called after the user chooses to check out
	public static String askToContinueShopping(BufferedReader bufferedReader, ShoppingCart cart) {
		try {
			String continueShoppingOptions = "Do you wish to continue shopping? (Y/N)";
			ArrayList<String> continueShoppingChoices = new ArrayList<>();
			continueShoppingChoices.add("Y");
			continueShoppingChoices.add("N");
			continueShoppingChoices.add("y");
			continueShoppingChoices.add("n");
			String continueShoppingChoice = userOptions(bufferedReader, continueShoppingOptions,
					continueShoppingChoices);
			if (continueShoppingChoice.equals("N")) {
				if (cart.checkOut()) {
					return "6";
				} else {
					return "5";
				}
			} else {
				cart.checkOut();
				return "5";
			}
		} catch (SQLException e1) {
			System.out.println("Database error.");
			return "5";
		} catch (DatabaseInsertException e2) {
			System.out.println("Insufficient items in inventory.");
			return "5";
		}
	}

	// display options to customer and perform the desired operation based on user
	// input
	// keep looping through options until a 6 is entered
	public static void performCustomerOptions(BufferedReader bufferedReader, HashMap<String, Integer> rolesToId,
			ShoppingCart cart, String customerOptions) throws SQLException, DatabaseInsertException {
		String customerChoice = "6";
		do {
			customerChoice = userOptions(bufferedReader, customerOptions, SalesApplication.getChoices(1, 6));

			if (customerChoice.equals("1")) {
				displayItemsInCart(cart);
			} else if (customerChoice.equals("2")) {
				modifyCartItems(bufferedReader, rolesToId, cart, "Enter quantity to add");
			} else if (customerChoice.equals("3")) {
				System.out.println(cart.getTotal());
			} else if (customerChoice.equals("4")) {
				modifyCartItems(bufferedReader, rolesToId, cart, "Enter quantity to remove");
			} else if (customerChoice.equals("5")) {
				customerChoice = askToContinueShopping(bufferedReader, cart);
			}
		} while (!customerChoice.equals("6"));

		storeShoppingCart(bufferedReader, cart);
	}

	private static void storeShoppingCart(BufferedReader bufferedReader, ShoppingCart cart)
			throws SQLException, DatabaseInsertException {
		// ask to save to account
		if (!cart.getItemMap().isEmpty()) {
			// if cart is not empty ask to save to an account

			ArrayList<Account> accountChoices = DatabaseSelectHelper.getUserAccounts(cart.getCustomer().getId());
			// you gotta make sure that the chosen account is empty

			for (Iterator<Account> accIt = accountChoices.iterator(); accIt.hasNext();) {
				if (!accIt.next().getItemMap().isEmpty()) {
					accIt.remove();
				}
			}
			if (!accountChoices.isEmpty()) {
				ArrayList<String> options = new ArrayList<>();
				options.add("Y");
				options.add("N");
				options.add("y");
				options.add("n");
				String customerSaveChoice = userOptions(bufferedReader,
						"Would you like to store your shopping cart and come back to it later?", options);
				if (customerSaveChoice.equals("Y") || customerSaveChoice.equals("y")) {

					Account chosenAccount = chooseAccount(
							"Enter one of the following account ids to store the shopping cart to the one in that account.\n",
							bufferedReader, accountChoices);
					if (chosenAccount != null) {
						for (Item item : cart.getItemMap().keySet()) {
							DatabaseInsertHelper.insertAccountSummary(chosenAccount.getId(), item.getId(),
									cart.getItemMap().get(item));
						}
					}
				}

			}
		}
	}

	private static Account chooseAccount(String accountIdOptions, BufferedReader bufferedReader,
			ArrayList<Account> accounts) {
		ArrayList<String> accountIdChoices = new ArrayList<>();

		for (Account account : accounts) {
			accountIdChoices.add(account.getId() + "");
			accountIdOptions += account.getId() + "\n";
		}
		// add option for not restoring any shopping cart
		if (!"Enter one of the following account ids to store the shopping cart to the one in that account.\n"
				.equals(accountIdOptions)) {
			accountIdChoices.add("0");
		}

		int accountChoice = Integer.parseInt(userOptions(bufferedReader, accountIdOptions, accountIdChoices));

		Account chosenAccount = null;
		int index = 0;
		while (chosenAccount == null && accountChoice != 0) {
			if (accounts.get(index).getId() == accountChoice) {
				chosenAccount = accounts.get(index);
			}
			index++;
		}

		return chosenAccount;
	}

	public static void restoreShoppingCart(BufferedReader bufferedReader, ShoppingCart cart) throws SQLException {
		ArrayList<Account> accounts = DatabaseSelectHelper.getUserAccounts(cart.getCustomer().getId());
		if (!accounts.isEmpty()) {
			Account chosenAccount = chooseAccount(
					"Enter one of the following account ids to restore the shopping cart from it. Enter 0 to start blank.\n",
					bufferedReader, accounts);
			if (chosenAccount != null) {
				cart.setItemMap(chosenAccount.getItemMap());
			}
		}

	}
}
