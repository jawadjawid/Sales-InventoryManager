package com.b07.validator;

import java.sql.SQLException;

import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.inventory.Item;
import com.b07.store.Account;
import com.b07.store.AccountImpl;

public class AccountSummaryValidator {

	public static boolean validateItemId(int itemId) {
		try {
			Item item = DatabaseSelectHelper.getItem(itemId);
			if (item == null) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean validateAccountId(int accountId) {
		return true;
	}

	public static boolean validateQuantity(int quantity) {
		if (quantity <= 0) {
			return false;
		}
		return true;
	}
}
