package com.Application.Controller.Customer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.Model.inventory.Item;
import com.Application.Model.inventory.ItemImpl;
import com.Application.View.Customer.CustomerHomeView;
import com.Application.View.Customer.CustomerShoppingView;
import com.example.Application.R;

import java.math.BigDecimal;
import java.util.HashMap;


public class CustomerShoppingController implements View.OnClickListener {
    private CustomerShoppingView view;
    private Context appContext;
    private int recievedQuantities[];

    public CustomerShoppingController(Context context) {
        appContext = context;
        view = (CustomerShoppingView) appContext;
        setQuantities();
        displayQuantity(R.id.RodCurrentQuantity, 0);
        displayQuantity(R.id.StickCurrentQuantity, 1);
        displayQuantity(R.id.SkatesCurrentQuantity, 2);
        displayQuantity(R.id.ShoesCurrentQuantity, 3);
        displayQuantity(R.id.BarCurrentQuantity, 4);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.RodAddBtn) {
            updateQuantity(0, 0, R.id.RodCurrentQuantity, R.id.RodQuantity);
        } else if (v.getId() == R.id.RodRemoveBtn) {
            updateQuantity(0, 1, R.id.RodCurrentQuantity, R.id.RodQuantity);
        } else if (v.getId() == R.id.StickAddBtn) {
            updateQuantity(1, 0, R.id.StickCurrentQuantity, R.id.StickQuantity);
        } else if (v.getId() == R.id.StickRemoveBtn) {
            updateQuantity(1, 1, R.id.StickCurrentQuantity, R.id.StickQuantity);
        } else if (v.getId() == R.id.SkatesAddBtn) {
            updateQuantity(2, 0, R.id.SkatesCurrentQuantity, R.id.SkatesQuantity);
        } else if (v.getId() == R.id.SkatesRemoveBtn) {
            updateQuantity(2, 1, R.id.SkatesCurrentQuantity, R.id.SkatesQuantity);
        } else if (v.getId() == R.id.ShoesAddBtn) {
            updateQuantity(3, 0, R.id.ShoesCurrentQuantity, R.id.ShoesQuantity);
        } else if (v.getId() == R.id.ShoesRemoveBtn) {
            updateQuantity(3, 1, R.id.ShoesCurrentQuantity, R.id.ShoesQuantity);
        } else if (v.getId() == R.id.BarAddBtn) {
            updateQuantity(4, 0, R.id.BarCurrentQuantity, R.id.BarQuantity);
        } else if (v.getId() == R.id.BarRemoveBtn) {
            updateQuantity(4, 1, R.id.BarCurrentQuantity, R.id.BarQuantity);
        } else if (v.getId() == R.id.ResetAllBtn) {
            for (int i = 0; i < 5; i++) {
                recievedQuantities[i] = 0;
            }
            displayQuantity(R.id.RodCurrentQuantity, 0);
            displayQuantity(R.id.StickCurrentQuantity, 1);
            displayQuantity(R.id.SkatesCurrentQuantity, 2);
            displayQuantity(R.id.ShoesCurrentQuantity, 3);
            displayQuantity(R.id.BarCurrentQuantity, 4);
        } else if (v.getId() == R.id.GoHomeBtn) {
            sendItemMap();
        }
    }

    private void sendItemMap() {
        HashMap<Item, Integer> allItems = new HashMap<>();
        Item rod = new ItemImpl(1, "Fishing Rod", new BigDecimal("12.00"));
        Item stick = new ItemImpl(2, "Hockey Stick", new BigDecimal("8.50"));
        Item skate = new ItemImpl(3, "Skates", new BigDecimal("10.00"));
        Item shoes = new ItemImpl(4, "Running Shoes", new BigDecimal("15.00"));
        Item bar = new ItemImpl(5, "Protein Bar", new BigDecimal("3.00"));
        allItems.put(rod, recievedQuantities[0]);
        allItems.put(stick, recievedQuantities[1]);
        allItems.put(skate, recievedQuantities[2]);
        allItems.put(shoes, recievedQuantities[3]);
        allItems.put(bar, recievedQuantities[4]);
        Intent intent = new Intent(appContext, CustomerHomeView.class);
        intent.putExtra("ItemMap", allItems);
        view.startActivity(intent);
    }


    private void setQuantities() {
        Intent intent = view.getIntent();
        int allQuantities[] = (int[]) intent.getSerializableExtra("quantities");
        if (allQuantities == null) {
            allQuantities = new int[5];
            for (int i = 0; i < 5; i++) {
                allQuantities[i] = -1;
            }
        }
        this.recievedQuantities = allQuantities;
    }

    private void displayQuantity(int textViewId, int index) {
        TextView totalQuantity = view.findViewById(textViewId);
        if (recievedQuantities[index] == -1) {
            recievedQuantities[index] = 0;
        }
        totalQuantity.setText(recievedQuantities[index] + "");
    }


    private int readEnteredQuantity(int textViewId) {
        TextView quantityText = view.findViewById(textViewId);
        if (quantityText.getText().length() == 0) {
            return -1;
        }
        return Integer.parseInt(quantityText.getText().toString());
    }

    private void updateQuantity(int updateIndex, int operation, int idCurrentQuantity, int idEnteredQuantity) {
        int enteredQuantity = readEnteredQuantity(idEnteredQuantity);
        if (enteredQuantity == -1) {
            Toast.makeText(appContext, "Before Adding/Removing items, Please Enter a quantity!", Toast.LENGTH_SHORT).show();
        } else {
            int newQuantity;
            if (operation == 0) {
                recievedQuantities[updateIndex] += enteredQuantity;
            } else {
                newQuantity = recievedQuantities[updateIndex] - enteredQuantity;
                if (newQuantity >= 0) {
                    recievedQuantities[updateIndex] -= enteredQuantity;
                }
            }
            displayQuantity(idCurrentQuantity, updateIndex);
        }
        TextView eraseEnteredQuantity = view.findViewById(idEnteredQuantity);
        eraseEnteredQuantity.setText("");
    }
}

