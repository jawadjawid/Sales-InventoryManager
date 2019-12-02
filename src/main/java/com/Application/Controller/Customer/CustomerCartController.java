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
import com.Application.Model.store.Account;
import com.Application.Model.store.ShoppingCart;
import com.Application.View.Customer.CustomerCartView;
import com.example.Application.R;

import java.util.ArrayList;


public class CustomerCartController implements View.OnClickListener {

    private CustomerCartView view;
    private Context appContext;
    private static ShoppingCart recievedCart;
    private static Account storingAccount = null;
    private static int accountId = -1;

    public CustomerCartController(Context context) {
        appContext = context;
        view = (CustomerCartView) appContext;
        setShoppingCart();
        displayCartItems();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkOutBtn) {
            //recievedCart.checkOut();
        } else {
            // save cart
            // ask which account to store to
            displayStoreAlert();

        }
    }

    private void displayStoreAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(appContext);
        final EditText edittext = new EditText(appContext);
        edittext.setHintTextColor(Color.BLACK);
        edittext.setHint("    Account ID");
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);

        alert.setMessage("\n\nPlease enter the ID of the account to store to below:\n");
        alert.setTitle("Store Shopping Cart ?");
        alert.setView(edittext);

        alert.setNegativeButton("Do not Store", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.setPositiveButton("Restore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (edittext.getText().length() == 0) {
                    Toast.makeText(appContext, "Cannot restore as no Account ID was entered", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(edittext.getText().toString());
                    if (validAccountId(id)) {
                        storeCart();
                        Toast.makeText(appContext, "Shopping Cart stored to account with id" + id, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(appContext, "Cannot store as Account was not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        alert.setCancelable(false);
        alert.show();
    }

    private void storeCart(){

    }

    private boolean validAccountId(int id) {
        DatabaseDriverAndroidHelper helper = new DatabaseDriverAndroidHelper(appContext);
        ArrayList<Account> activeAccounts = helper.getUserActiveAccountsH(recievedCart.getCustomer().getId());
        for (Account i : activeAccounts) {
            if (i.getId() == id) {
                this.storingAccount = i;
                this.accountId = id;
                return true;
            }
        }
        return false;
    }

    public void setShoppingCart() {
        Intent intent = view.getIntent();
        ShoppingCart cart = (ShoppingCart) intent.getSerializableExtra("cart");
        this.recievedCart = cart;
    }

    public void displayCartItems() {
        if (recievedCart.getItemMap().size() != 0) {
            String itemsLabel;
            for (Item i : recievedCart.getItemMap().keySet()) {
                if (recievedCart.getItemMap().get(i) != 0) {
                    itemsLabel = i.getName() + " ($" + i.getPrice() + ")";
                    if (i.getName().equals("Fishing Rod")) {
                        displayItemDetail(R.id.rodLabel, itemsLabel, R.id.RodQuantity, recievedCart.getItemMap().get(i));
                    } else if (i.getName().equals("Hockey Stick")) {
                        displayItemDetail(R.id.stickLabel, itemsLabel, R.id.StickQuantity, recievedCart.getItemMap().get(i));
                    } else if (i.getName().equals("Skates")) {
                        displayItemDetail(R.id.skatesLabel, itemsLabel, R.id.SkatesQuantity, recievedCart.getItemMap().get(i));
                    } else if (i.getName().equals("Running Shoes")) {
                        displayItemDetail(R.id.shoesLabel, itemsLabel, R.id.ShoesQuantity, recievedCart.getItemMap().get(i));
                    } else if (i.getName().equals("Protein Bar")) {
                        displayItemDetail(R.id.barLabel, itemsLabel, R.id.BarQuantity, recievedCart.getItemMap().get(i));
                    }
                }
            }
            displayTotal();
        } else {
            Toast.makeText(appContext, "No Items to see here!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayTotal() {
        TextView totalPrice = view.findViewById(R.id.totalPriceText);
        totalPrice.setText("$ " + recievedCart.getTotal().toString());
    }

    public void displayItemDetail(int textViewIdLabel, String itemLabel, int textViewIdQuantity, int totalQuantity) {
        TextView item = view.findViewById(textViewIdLabel);
        TextView quantity = view.findViewById(textViewIdQuantity);
        item.setText(itemLabel);
        quantity.setText(totalQuantity + "");
    }
}
