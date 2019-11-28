package com.Application.Controller.Customer;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        } else if (v.getId() == R.id.addCartBtn) {

        }
    }
}
