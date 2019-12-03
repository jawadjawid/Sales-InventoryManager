package com.Application.Controller.Employee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.inventory.Inventory;
import com.Application.Model.inventory.Item;
import com.example.Application.R;


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
    items = new Item[5];
    for (Item item : inventory.getItemMap().keySet()) {
      items[item.getId() - 1] = item;
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
          restockQuantity("Current Number of Fishing Rods in stock: " + inventory.getItemMap()
              .get(items[index]));
          break;
        case R.id.addHockeyStickButton:
          index = 1;
          restockQuantity("Current Number of Hockey Sticks in stock: " + inventory.getItemMap()
              .get(items[index]));
          break;
        case R.id.addSkatesButton:
          index = 2;
          restockQuantity(
              "Current Number of Skates in stock: " + inventory.getItemMap().get(items[index]));
          break;
        case R.id.addRunningShoesButton:
          index = 3;
          restockQuantity("Current Number of Running Shoes in stock: " + inventory.getItemMap()
              .get(items[index]));
          break;
        case R.id.addProteinBarButton:
          index = 4;
          restockQuantity("Current Number of Protein Bars in stock: " + inventory.getItemMap()
              .get(items[index]));
          break;
        case R.id.saveChangesButton:
          for (int i = 0; i < items.length; i++) {
            mydb.updateInventoryQuantityH(inventory.getItemMap().get(items[i]), i + 1);
          }
          Toast.makeText(appContext, "Inventory Successfully Updated.", Toast.LENGTH_SHORT).show();
          break;
        default:
          break;
      }

    } catch (DatabaseInsertException e) {
      Toast.makeText(appContext, "Database Error. Inventory Restock Unsuccessful.",
          Toast.LENGTH_SHORT).show();
    }
  }

  private void restockQuantity(String title) {
    AlertDialog.Builder builder = new AlertDialog.Builder(appContext);
    builder.setTitle(title);

    final EditText input = new EditText(appContext);
    input.setInputType(InputType.TYPE_CLASS_NUMBER);
    input.setHint("    Quantity to add to stock");
    InputFilter[] filterArray = new InputFilter[1];
    filterArray[0] = new InputFilter.LengthFilter(5);
    input.setFilters(filterArray);
    builder.setView(input);

    // Set up the buttons
    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if (input.getText().toString().equals("")) {
          Toast.makeText(appContext, "Please Enter a quantity before adding to stock",
              Toast.LENGTH_SHORT).show();
        } else {
          int quantity = Integer.parseInt(input.getText().toString());
          inventory.updateMap(items[index], quantity);
        }
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
