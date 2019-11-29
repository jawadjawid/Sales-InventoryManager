package com.Application.Controller.Customer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.Model.store.ShoppingCart;
import com.Application.Model.users.Customer;
import com.Application.Model.users.User;
import com.Application.View.Customer.CustomerCartView;
import com.Application.View.Customer.CustomerShoppingView;
import com.example.Application.R;


public class CustomerShoppingController implements View.OnClickListener {
    private CustomerShoppingView view;
    private Context appContext;

    public CustomerShoppingController(Context context) {
        appContext = context;
        view = (CustomerShoppingView) appContext;
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
            Intent intent = view.getIntent();
            User user = (User) intent.getSerializableExtra("user");

            Customer c = new Customer(user.getId(), user.getName(), user.getAge(), user.getAddress());
            ShoppingCart cart = new ShoppingCart(c);
            Toast.makeText(appContext, "customer name" + c.getName(), Toast.LENGTH_SHORT).show();
            intent = new Intent(appContext, CustomerCartView.class);
            intent.putExtra("cart", cart);
            view.startActivity(intent);
            Toast.makeText(appContext, "All items have been added to the cart!", Toast.LENGTH_SHORT).show();
        }
    }
}
