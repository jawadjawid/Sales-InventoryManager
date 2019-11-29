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
        View root = inflater.inflate(R.layout.fragment_employee_restock_inventory, container, false);

        controller = new RestockInventoryFragmentController(root);
        Button addFishingRodButton = root.findViewById(R.id.addFishingRodButton);
        Button addHockeyStickButton = root.findViewById(R.id.addHockeyStickButton);
        Button addSkatesButton = root.findViewById(R.id.addSkatesButton);
        Button addRunningShoesButton = root.findViewById(R.id.addRunningShoesButton);
        Button addProteinBarButton = root.findViewById(R.id.addProteinBarButton);
        Button saveChangesButton = root.findViewById(R.id.saveChangesButton);

        addFishingRodButton.setOnClickListener(controller);
        addHockeyStickButton.setOnClickListener(controller);
        addSkatesButton.setOnClickListener(controller);
        addRunningShoesButton.setOnClickListener(controller);
        addProteinBarButton.setOnClickListener(controller);
        saveChangesButton.setOnClickListener(controller);


        return root;
    }
}