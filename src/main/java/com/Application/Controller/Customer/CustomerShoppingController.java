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
    private User recievedUser;

    public CustomerShoppingController(Context context) {
        appContext = context;
        view = (CustomerShoppingView) appContext;
        setUser();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addRodBtn) {
            TextView prevQuantity = view.findViewById(R.id.fishingRodQuantity);
            int oldQuantity = Integer.parseInt(prevQuantity.getText().toString());

            EditText rodQuantity = view.findViewById(R.id.rodEnterQuan);
            if (rodQuantity.getText().length() == 0) {
                Toast.makeText(appContext, "Please Enter a quantity!", Toast.LENGTH_SHORT).show();
            } else {
                int newQuantity = Integer.parseInt(rodQuantity.getText().toString());
                if (newQuantity >= 1) {
                    TextView totalRodQuantity = view.findViewById(R.id.fishingRodQuantity);
                    totalRodQuantity.setText((oldQuantity + newQuantity) + "");
                } else {
                    Toast.makeText(appContext, "Please Enter appropriate quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v.getId() == R.id.addStickBtn) {
            TextView prevQuantity = view.findViewById(R.id.hockeyQuantity);
            int oldQuantity = Integer.parseInt(prevQuantity.getText().toString());

            EditText rodQuantity = view.findViewById(R.id.stickEnterQuan);
            if (rodQuantity.getText().length() == 0) {
                Toast.makeText(appContext, "Please Enter a quantity!", Toast.LENGTH_SHORT).show();
            } else {
                int newQuantity = Integer.parseInt(rodQuantity.getText().toString());
                if (newQuantity >= 1) {
                    TextView totalRodQuantity = view.findViewById(R.id.hockeyQuantity);
                    totalRodQuantity.setText((oldQuantity + newQuantity) + "");
                } else {
                    Toast.makeText(appContext, "Please Enter appropriate quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v.getId() == R.id.addSkatesBtn) {
            TextView prevQuantity = view.findViewById(R.id.skatesQuantity);
            int oldQuantity = Integer.parseInt(prevQuantity.getText().toString());

            EditText rodQuantity = view.findViewById(R.id.skatesEnterQuan);
            if (rodQuantity.getText().length() == 0) {
                Toast.makeText(appContext, "Please Enter a quantity!", Toast.LENGTH_SHORT).show();
            } else {
                int newQuantity = Integer.parseInt(rodQuantity.getText().toString());
                if (newQuantity >= 1) {
                    TextView totalRodQuantity = view.findViewById(R.id.skatesQuantity);
                    totalRodQuantity.setText((oldQuantity + newQuantity) + "");
                } else {
                    Toast.makeText(appContext, "Please Enter appropriate quantity!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (v.getId() == R.id.addBarBtn) {
            TextView prevQuantity = view.findViewById(R.id.proteinBarQuantity);
            int oldQuantity = Integer.parseInt(prevQuantity.getText().toString());

            EditText rodQuantity = view.findViewById(R.id.barEnterQuan);
            if (rodQuantity.getText().length() == 0) {
                Toast.makeText(appContext, "Please Enter a quantity!", Toast.LENGTH_SHORT).show();
            } else {
                int newQuantity = Integer.parseInt(rodQuantity.getText().toString());
                if (newQuantity >= 1) {
                    TextView totalRodQuantity = view.findViewById(R.id.proteinBarQuantity);
                    totalRodQuantity.setText((oldQuantity + newQuantity) + "");
                } else {
                    Toast.makeText(appContext, "Please Enter appropriate quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v.getId() == R.id.addShoesBtn) {
            TextView prevQuantity = view.findViewById(R.id.runningShoesQuantity);
            int oldQuantity = Integer.parseInt(prevQuantity.getText().toString());

            EditText rodQuantity = view.findViewById(R.id.shoesEnterQuan);
            if (rodQuantity.getText().length() == 0) {
                Toast.makeText(appContext, "Please Enter a quantity!", Toast.LENGTH_SHORT).show();
            } else {
                int newQuantity = Integer.parseInt(rodQuantity.getText().toString());
                if (newQuantity >= 1) {
                    TextView totalRodQuantity = view.findViewById(R.id.runningShoesQuantity);
                    totalRodQuantity.setText((oldQuantity + newQuantity) + "");
                } else {
                    Toast.makeText(appContext, "Please Enter appropriate quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v.getId() == R.id.goCartBtn) {

            Customer c = new Customer(recievedUser.getId(), recievedUser.getName(), recievedUser.getAge(), recievedUser.getAddress(), recievedUser.getRoleId());
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

            if(totalShoesQuantity >= 1){
                Item shoes = new ItemImpl(1, "Running Shoes", new BigDecimal("15.00"));
                allItems.put(shoes, totalShoesQuantity);
            }
            if(totalStickQuantity >= 1){
                Item stick = new ItemImpl(2, "Hockey Stick", new BigDecimal("8.50"));
                allItems.put(stick, totalStickQuantity);
            }
            if(totalBarQuantity >= 1){
                Item bar = new ItemImpl(3, "Protein Bar", new BigDecimal("3.00"));
                allItems.put(bar, totalBarQuantity);
            }
            if(totalSkateQuantity >= 1){
                Item skate = new ItemImpl(4, "Skates", new BigDecimal("10.00"));
                allItems.put(skate, totalSkateQuantity);
            }
            if(totalRodQuantity >= 1){
                Item rod = new ItemImpl(5, "Fishing Rod", new BigDecimal("12.00"));
                allItems.put(rod, totalRodQuantity);
            }

            cart.setItemMap(allItems);
            Intent intent = new Intent(appContext, CustomerCartView.class);
            Toast.makeText(appContext, "All items have been added to the cart!", Toast.LENGTH_SHORT).show();
            intent.putExtra("cart", cart);
            view.startActivity(intent);
            }
    }

    public void setUser() {
        Intent intent = view.getIntent();
        User user = (User) intent.getSerializableExtra("user");
        this.recievedUser = user;
    }
}

