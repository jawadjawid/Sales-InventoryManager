package com.Application.Model.validator;

import java.sql.SQLException;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.User;

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
