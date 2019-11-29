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
    private ShoppingCart recievedCart = null;

    public CustomerCartController(Context context) {
        appContext = context;
        view = (CustomerCartView) appContext;
        setShoppingCart();
        displayItems();
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

    public void displayItems() {
        String itemsOutput = "";
        if(recievedCart == null){
            itemsOutput = "Please add Items to the Cart";
        }else{
            for (Item i : recievedCart.getItemMap().keySet()) {
                itemsOutput += i.getName() + "    " + recievedCart.getItemMap().get(i) + "     $" + i.getPrice() + " each\n\n";
            }
        }
        TextView allItems = view.findViewById(R.id.itemsTextArea);
        allItems.setText(itemsOutput);
        displayTotal();
    }

    public void displayTotal(){
        if(recievedCart != null){
            TextView totalPrice = view.findViewById(R.id.totalPriceText);
            totalPrice.setText("$ " + recievedCart.getTotal().toString());
        }
    }
}
