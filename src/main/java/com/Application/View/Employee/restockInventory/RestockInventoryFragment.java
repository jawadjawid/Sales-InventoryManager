package com.Application.View.Employee.restockInventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.Application.R;


public class RestockInventoryFragment extends Fragment {

    private RestockInventoryViewModel restockInventoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        restockInventoryViewModel =
                ViewModelProviders.of(this).get(RestockInventoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_restock_inventory, container, false);
     //   final TextView textView = root.findViewById(R.id.text_restock_inventory);
      //  restockInventoryViewModel.getText().observe(this, new Observer<String>() {
     //       @Override
        //    public void onChanged(@Nullable String s) {
      //          textView.setText(s);
       //     }
      //  });
        return root;
    }
}