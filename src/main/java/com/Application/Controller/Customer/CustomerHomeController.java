package com.Application.Controller.Customer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.inventory.Item;
import com.Application.Model.inventory.ItemImpl;
import com.Application.Model.store.Account;
import com.Application.Model.store.ShoppingCart;
import com.Application.Model.users.Customer;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerCartView;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Customer.CustomerShoppingView;
import com.Application.View.MainLoginView;
import com.example.Application.R;

import java.math.BigDecimal;
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
    private static int accountId = -1;

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
        if (allItems != null) {
            this.cart.setItemMap(allItems);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shopBtn) {
            Intent intent = new Intent(appContext, CustomerShoppingView.class);
            filterQuantities();
            intent.putExtra("quantities", allQuantities);
            view.startActivity(intent);
        } else if (v.getId() == R.id.cartBtn) {
            Intent intent = new Intent(appContext, CustomerCartView.class);
            intent.putExtra("cart", cart);
            intent.putExtra("account id", accountId);
            view.startActivity(intent);
        } else if (v.getId() == R.id.LogOutBtn) {
            this.numHomePageVisits = 0;
            this.cart.setItemMap(new HashMap<Item, Integer>());
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
            filterItemMap();
            cart.setItemMap(restoringAccount.getItemMap());
            filterQuantities();
        }
    }

    private void filterItemMap() {
        String allItems[] = {"Fishing Rod", "Hockey Stick", "Skates", "Running Shoes", "Protein Bar"};
        boolean itemsExist[] = {false, false, false, false, false};
        String allPrices[] = {"12.00", "8.50", "10.00", "15.00", "3.00"};
        for(Item i: restoringAccount.getItemMap().keySet()){
            if(i.getName().equals(allItems[0])){
                itemsExist[0] = true;
            }else if(i.getName().equals(allItems[1])){
                itemsExist[1] = true;
            }else if(i.getName().equals(allItems[2])){
                itemsExist[2] = true;
            }else if(i.getName().equals(allItems[3])){
                itemsExist[3] = true;
            }else if(i.getName().equals(allItems[4])){
                itemsExist[4] = true;
            }
        }

        for(int i = 0; i< 5; i++){
            if(!itemsExist[i]){
                Item newItem = new ItemImpl(i+1, allItems[i], new BigDecimal(allPrices[i]));
                restoringAccount.addItem(newItem, 0);
            }
        }
    }

    private boolean validAccountId(int id) {
        DatabaseDriverAndroidHelper helper = new DatabaseDriverAndroidHelper(appContext);
        ArrayList<Account> activeAccounts = helper.getUserActiveAccountsH(recievedUser.getId());
        for (Account i : activeAccounts) {
            if (i.getId() == id) {
                this.restoringAccount = i;
                this.accountId = id;
                return true;
            }
        }
        return false;
    }

    private void filterQuantities() {
        if (cart.getItemMap().size() != 0) {
            this.allQuantities = new int[5];
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
