package com.Application.View.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.Application.Controller.Employee.HomeFragmentController;
import com.example.Application.R;

public class HomeFragmentView extends Fragment {

  private HomeFragmentController controller;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_employee_home, container, false);

    controller = new HomeFragmentController(root);
    Button logOutButton = root.findViewById(R.id.logOutButton);
    logOutButton.setOnClickListener(controller);
    return root;
  }
}