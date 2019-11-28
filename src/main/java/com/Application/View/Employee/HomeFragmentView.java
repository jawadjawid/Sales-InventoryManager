package com.Application.View.Employee;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Application.Controller.Employee.AuthenticateNewEmployeeFragmentController;
import com.Application.Controller.Employee.HomeFragmentController;
import com.example.Application.R;

public class HomeFragmentView extends Fragment {

    private HomeFragmentController controller;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle bundle = getArguments();
        Log.d("hehe", "aa");
        controller = new HomeFragmentController(root, this);
        Button logOutButton = root.findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(controller);
        return root;
    }
}