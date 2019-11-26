package com.b07.interaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidLoginException;
import com.b07.users.EmployeeInterface;
import com.b07.users.Roles;
import com.b07.users.User;

public class UserInteraction {

  // Asks user for account information and updates database with account
  // information if entered information is valid
  public static boolean createAccount(BufferedReader bufferedReader,
      HashMap<String, Integer> rolesToId, Roles role,
      EmployeeInterface emIn) {
    try {
      System.out.print("Enter username: ");
      String name = bufferedReader.readLine();
      System.out.print("Enter age: ");
      int age = Integer.parseInt(bufferedReader.readLine());
      System.out.print("Enter address: ");
      String address = bufferedReader.readLine();
      System.out.print("Enter password: ");
      String password = bufferedReader.readLine();

      int userId = 0;
      if (emIn != null) {
        if (role.name().equals("CUSTOMER")) {
          userId = emIn.createCustomer(name, age, address, password);
        } else {
          userId = emIn.createEmployee(name, age, address, password);
        }
      } else {
        userId = DatabaseInsertHelper.insertNewUser(name, age, address, password);
        DatabaseInsertHelper.insertUserRole(userId, rolesToId.get(role.name()));
      }
      System.out.println("Account created successfully.");
      System.out.println("Remember your id is " + userId);
      return true;
    } catch (IOException | NumberFormatException e1) {
      System.out.println("Error with Account Creation.");
      System.out.println("Please ensure to provide appropriate account information.\n\n");
      return false;
    } catch (DatabaseInsertException | SQLException e2) {
      System.out.println("Database Error.\n\n");
      e2.printStackTrace();
      return false;
    }
  }

  // Create the administrator and employee account
  // NOTE: this is called when -1 is passed on as an argument
  public static void createFirstRunAccounts(BufferedReader bufferedReader,
      HashMap<String, Integer> rolesToId) {
    // Create an administrator account with a password
    System.out.println("Time to create the first administrator account!");
    while (!createAccount(bufferedReader, rolesToId, Roles.ADMIN, null)) {
      System.out.println("Let's try creating the administrator account again!");
    }
    // Create an employee account with a password
    System.out.println("Time to create the first employee account now!");
    while (!createAccount(bufferedReader, rolesToId, Roles.EMPLOYEE, null)) {
      System.out.println("Let's try creating the employee account again!");
    }
    System.out.println("Both accounts successfully created!");
  }

  // allows user to input id and password and returns a user object if the login
  // was successful. Returns null otherwise
  public static User logIn(BufferedReader bufferedReader, String logInType, Roles role) {
    try {
      System.out.println(logInType);
      System.out.print("Enter id: ");
      int id = Integer.parseInt(bufferedReader.readLine());
      System.out.print("Enter password: ");
      String password = bufferedReader.readLine();
      User user = DatabaseSelectHelper.getUserDetails(id);

      if (user.authenticate(password) && DatabaseSelectHelper.getRoleName(user.getRoleId())
          .equals(role.name())) {
        return user;
      }
      if (!user.authenticate(password)) {
        throw new InvalidLoginException();
      }
      if (!DatabaseSelectHelper.getRoleName(user.getRoleId()).equals(role.name())) {
        throw new InvalidLoginException();
      }
      return null;
    } catch (IOException | NumberFormatException | NullPointerException | InvalidLoginException e1) {
      System.out.println("Error with Account Login.");
      System.out.println("Please ensure to provide appropriate login information.\n\n");
      return null;
    } catch (SQLException e2) {
      System.out.println("Database Error.\n\n");
      e2.printStackTrace();
      return null;
    }
  }

  // displays instructions to the user and prompts user for input until a
  // non-negative integer is entered which is then returned by the method
  public static int userOptionsNums(BufferedReader bufferedReader, String instructions) {
    int choice = -1;
    do {
      try {
        System.out.println(instructions);
        choice = Integer.parseInt(bufferedReader.readLine());
        if (!(choice >= 0)) {
          System.out.println("Error with Selection.");
          System.out.println("Please ensure to select an appropriate option.\n\n");
        }
      } catch (IOException e1) {
        System.out.println("Error with Selection.");
        System.out.println("Please ensure to select an appropriate option.\n\n");
      } catch (NumberFormatException e2) {
        System.out.println("Error with Selection.");
        System.out.println("Please ensure to select an appropriate option.\n\n");
      }
    } while (!(choice >= 0));
    return choice;
  }

  // displays instructions to user and prompts user for input until one of the
  // strings in acceptableOptions is entered
  public static String userOptions(BufferedReader bufferedReader, String instructions,
      ArrayList<String> acceptableOptions) {

    String choice = "";
    do {
      try {
        System.out.println(instructions);
        choice = bufferedReader.readLine();

        if (!acceptableOptions.contains(choice)) {
          System.out.println("Error with Selection.");
          System.out.println("Please ensure to select an appropriate option.\n\n");
        }
      } catch (IOException e1) {
        System.out.println("Error with Selection.");
        System.out.println("Please ensure to select an appropriate option.\n\n");
      }
    } while (!acceptableOptions.contains(choice));

    return choice;
  }
  
  // returns an arraylist including the ids of all employees in database as strings
  public static ArrayList<String> getAllIds(HashMap<String, Integer> rolesToId, Roles role) {
    try {
      List<User> users = DatabaseSelectHelper.getUsersDetails();
      ArrayList<String> idChoices = new ArrayList<>();
      int id = rolesToId.get(role.name());
      for (User user : users) {
        if (id == user.getRoleId()) {
          idChoices.add("" + user.getId());
        }
      }
      return idChoices;
    } catch (SQLException e) {
      System.out.println("Database Error.");
      return null;
    }
  }
  
}
