package com.Application.View.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Application.Controller.Employee.MakeNewAccountFragmentController;
import com.example.Application.R;

public class MakeNewAccountFragmentView extends Fragment {

    private MakeNewAccountFragmentController controller;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_make_new_account, container, false);

        controller = new MakeNewAccountFragmentController(root);
      //  Button authenticateNewEmployeeButton = root.findViewById(R.id.authenticateNewEmployeeButton);
      //  authenticateNewEmployeeButton.setOnClickListener(controller);
        return root;
    }
}