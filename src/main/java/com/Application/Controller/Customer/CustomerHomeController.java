package com.Application.Controller.Customer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.inventory.Item;
import com.Application.Model.store.Account;
import com.Application.Model.store.ShoppingCart;
import com.Application.Model.users.Customer;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerCartView;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Customer.CustomerShoppingView;
import com.Application.View.MainLoginView;
import com.example.Application.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerHomeController implements View.OnClickListener {
    private CustomerHomeView view;
    private Context appContext;

    private static User recievedUser;
    private static int numHomePageVisits = 0;
    private static ShoppingCart cart;
    private static int allQuantities[] = null;
    private static Account restoringAccount = null;

    public CustomerHomeController(Context context) {
        appContext = context;
        view = (CustomerHomeView) appContext;
        if (numHomePageVisits == 0) {
            setUserName();
            displayRestoreAlert();
            numHomePageVisits = 1;
        }
        displayUserName();
        updateCart();
    }

    private void displayUserName() {
        TextView usernameTextView = view.findViewById(R.id.customerName);
        usernameTextView.setText(recievedUser.getName());
    }

    private void updateCart() {
        Intent intent = view.getIntent();
        HashMap<Item, Integer> allItems = (HashMap<Item, Integer>) intent.getSerializableExtra("ItemMap");
        if(allItems != null){
            this.cart.setItemMap(allItems);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shopBtn) {
            Intent intent = new Intent(appContext, CustomerShoppingView.class);
            if (allQuantities != null) {
                intent.putExtra("quantities", allQuantities);
            }
            view.startActivity(intent);
        } else if (v.getId() == R.id.cartBtn) {
            Intent intent = new Intent(appContext, CustomerCartView.class);
            view.startActivity(intent);
        } else if (v.getId() == R.id.LogOutBtn) {
            this.numHomePageVisits = 0;
            Intent intent = new Intent(appContext, MainLoginView.class);
            view.startActivity(intent);
        }
    }

    public void setUserName() {
        Intent intent = view.getIntent();
        User user = (User) intent.getSerializableExtra("user");
        this.recievedUser = user;
    }

    private void displayRestoreAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(appContext);
        final EditText edittext = new EditText(appContext);
        edittext.setHintTextColor(Color.BLACK);
        edittext.setHint("    Account ID");
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);

        alert.setMessage("\n\nPlease enter the ID of the account to restore from below:\n");
        alert.setTitle("Restore Shopping Cart ?");
        alert.setView(edittext);

        alert.setNegativeButton("Do not Restore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                createNewCart(false, 0);
            }
        });
        alert.setPositiveButton("Restore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (edittext.getText().length() == 0) {
                    Toast.makeText(appContext, "Cannot restore as no Account ID was entered", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(edittext.getText().toString());
                    if (validAccountId(id)) {
                        createNewCart(true, id);
                        Toast.makeText(appContext, "Shopping Cart restored from account with id" + id, Toast.LENGTH_SHORT).show();
                    } else {
                        createNewCart(false, id);
                        Toast.makeText(appContext, "Cannot Restore as Account was not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        alert.setCancelable(false);
        alert.show();
    }

    private void createNewCart(boolean restore, int accountId) {
        Customer c = new Customer(recievedUser.getId(), recievedUser.getName(), recievedUser.getAge(), recievedUser.getAddress(), recievedUser.getRoleId());
        if (!restore) {
            this.cart = new ShoppingCart(c);
        } else {
            this.cart = new ShoppingCart(c);
            cart.setItemMap(restoringAccount.getItemMap());
            filterQuantities();
        }
    }

    private boolean validAccountId(int id) {
        DatabaseDriverAndroidHelper helper = new DatabaseDriverAndroidHelper(appContext);
        ArrayList<Account> activeAccounts = helper.getUserActiveAccountsH(recievedUser.getId());
        for (Account i : activeAccounts) {
            if (i.getId() == id) {
                this.restoringAccount = i;
                return true;
            }
        }
        return false;
    }

    private void filterQuantities() {
        if (cart.getItemMap().size() != 0) {
            allQuantities = new int[5];
            for (Item i : cart.getItemMap().keySet()) {
                if (i.getName().equals("Fishing Rod")) {
                    allQuantities[0] = cart.getItemMap().get(i);
                } else if (i.getName().equals("Hockey Stick")) {
                    allQuantities[1] = cart.getItemMap().get(i);
                } else if (i.getName().equals("Skates")) {
                    allQuantities[2] = cart.getItemMap().get(i);
                } else if (i.getName().equals("Running Shoes")) {
                    allQuantities[3] = cart.getItemMap().get(i);
                } else if (i.getName().equals("Protein Bar")) {
                    allQuantities[4] = cart.getItemMap().get(i);
                }
            }
        }
    }
}
