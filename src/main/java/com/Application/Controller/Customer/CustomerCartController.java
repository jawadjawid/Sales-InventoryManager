package com.Application.Controller.Customer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.Model.inventory.Item;
import com.Application.Model.store.ShoppingCart;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerCartView;
import com.example.Application.R;


public class CustomerCartController implements View.OnClickListener {

    private CustomerCartView view;
    private Context appContext;
    private static ShoppingCart recievedCart;

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
        }
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
