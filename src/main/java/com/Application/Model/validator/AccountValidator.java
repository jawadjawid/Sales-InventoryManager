package com.b07.validator;

import java.sql.SQLException;

import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.users.User;

public class AccountValidator {
	public static boolean validateUserId(int id) {
		try {
			User user = DatabaseSelectHelper.getUserDetails(id);
			if (user == null) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
