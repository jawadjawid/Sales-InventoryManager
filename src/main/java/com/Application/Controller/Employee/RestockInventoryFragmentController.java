package com.Application.Controller.Employee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.Item;
import com.Application.Model.inventory.ItemImpl;
import com.example.Application.R;

import java.math.BigDecimal;


public class RestockInventoryFragmentController implements View.OnClickListener {
    private View view;
    private Context appContext;
    private int index = -1;
   private Inventory inventory;

    private Item[] items;

    public RestockInventoryFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
        inventory = mydb.getInventoryH();
        Log.d("efefe", "RestockInventoryFragmentController: ");

        items = new Item[5];
        for(Item item: inventory.getItemMap().keySet()){
            items[item.getId()-1] = item;
        }
    }

    @Override
    public void onClick(View v) {
        try {
            int id = v.getId();
            DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);



            switch (id) {
                case R.id.addFishingRodButton:
                    index = 0;
                    restockQuantity("Input Quantity of Fishing Rods to Restock:" + inventory.getItemMap().get(items[index]));
                    break;
                case R.id.addHockeyStickButton:
                    index = 1;
                    restockQuantity("Input Quantity of Hockey Sticks to Restock:" + inventory.getItemMap().get(items[index]));
                    break;
                case R.id.addSkatesButton:
                    index = 2;
                    restockQuantity("Input Quantity of Skates to Restock:" + inventory.getItemMap().get(items[index]));
                    break;
                case R.id.addRunningShoesButton:
                    index = 3;
                    restockQuantity("Input Quantity of Running Shoes to Restock:" + inventory.getItemMap().get(items[index]));
                    break;
                case R.id.addProteinBarButton:
                    index = 4;
                    restockQuantity("Input Quantity of Protein Bars to Restock:" + inventory.getItemMap().get(items[index]));
                    break;
                case R.id.saveChangesButton:
                    for (int i = 0; i < items.length; i++) {
                        mydb.updateInventoryQuantityH(inventory.getItemMap().get(items[i]), i + 1);
                    }
                    Toast.makeText(appContext, "Done saved", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

            for(int i = 0; i < items.length; i++){
                Log.d("inventry","quantity is " + inventory.getItemMap().get(items[i]));
            }
        } catch (DatabaseInsertException e) {

        }
    }

    private void restockQuantity(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
        builder.setTitle(title);

        final EditText input = new EditText(appContext);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int quantity = Integer.parseInt(input.getText().toString());
                Log.d("invent", "onClick: quantity entered is " + quantity);
                Log.d("invent", "onClick: already in inventory is " + inventory.getItemMap().get(items[index]));
                inventory.updateMap(items[index], quantity);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


}
