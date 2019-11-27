package com.Application.View.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Application.Controller.Employee.RestockInventoryFragmentController;
import com.example.Application.R;

public class RestockInventoryFragmentView extends Fragment {

    private RestockInventoryFragmentController controller;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_restock_inventory, container, false);

        controller = new RestockInventoryFragmentController(root);
     //   Button authenticateNewEmployeeButton = root.findViewById(R.id.authenticateNewEmployeeButton);
     //   authenticateNewEmployeeButton.setOnClickListener(controller);
        return root;
    }
}