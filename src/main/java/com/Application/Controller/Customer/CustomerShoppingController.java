package com.Application.Controller.Customer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.Model.inventory.Item;
import com.Application.Model.inventory.ItemImpl;
import com.Application.Model.store.ShoppingCart;
import com.Application.Model.users.Customer;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerCartView;
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
        }else if(v.getId() == R.id.ResetAllBtn){
            for(int i = 0; i < 5; i++){
                recievedQuantities[i] = 0;
            }
            displayQuantity(R.id.RodCurrentQuantity, 0);
            displayQuantity(R.id.StickCurrentQuantity, 1);
            displayQuantity(R.id.SkatesCurrentQuantity, 2);
            displayQuantity(R.id.ShoesCurrentQuantity, 3);
            displayQuantity(R.id.BarCurrentQuantity, 4);
        } else if (v.getId() == R.id.GoHomeBtn) {

            /*Customer c = new Customer(recievedUser.getId(), recievedUser.getName(), recievedUser.getAge(), recievedUser.getAddress(), recievedUser.getRoleId());
            ShoppingCart cart = new ShoppingCart(c);
            HashMap<Item, Integer> allItems = new HashMap<>();

            TextView shoesQuantity = view.findViewById(R.id.runningShoesQuantity);
            TextView stickQuantity = view.findViewById(R.id.hockeyQuantity);
            TextView barQuantity = view.findViewById(R.id.proteinBarQuantity);
            TextView skatesQuantity = view.findViewById(R.id.skatesQuantity);
            TextView rodQuantity = view.findViewById(R.id.fishingRodQuantity);

            int totalShoesQuantity = Integer.parseInt(shoesQuantity.getText().toString());
            int totalStickQuantity = Integer.parseInt(stickQuantity.getText().toString());
            int totalBarQuantity = Integer.parseInt(barQuantity.getText().toString());
            int totalSkateQuantity = Integer.parseInt(skatesQuantity.getText().toString());
            int totalRodQuantity = Integer.parseInt(rodQuantity.getText().toString());

            if (totalShoesQuantity >= 1) {
                Item shoes = new ItemImpl(1, "Running Shoes", new BigDecimal("15.00"));
                allItems.put(shoes, totalShoesQuantity);
            }
            if (totalStickQuantity >= 1) {
                Item stick = new ItemImpl(2, "Hockey Stick", new BigDecimal("8.50"));
                allItems.put(stick, totalStickQuantity);
            }
            if (totalBarQuantity >= 1) {
                Item bar = new ItemImpl(3, "Protein Bar", new BigDecimal("3.00"));
                allItems.put(bar, totalBarQuantity);
            }
            if (totalSkateQuantity >= 1) {
                Item skate = new ItemImpl(4, "Skates", new BigDecimal("10.00"));
                allItems.put(skate, totalSkateQuantity);
            }
            if (totalRodQuantity >= 1) {
                Item rod = new ItemImpl(5, "Fishing Rod", new BigDecimal("12.00"));
                allItems.put(rod, totalRodQuantity);
            }

            cart.setItemMap(allItems);
            Intent intent = new Intent(appContext, CustomerCartView.class);
            Toast.makeText(appContext, "All items have been added to the cart!", Toast.LENGTH_SHORT).show();
            intent.putExtra("cart", cart);
            view.startActivity(intent);*/
        }
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

