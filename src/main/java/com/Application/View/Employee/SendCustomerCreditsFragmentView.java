package com.Application.View.Employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.Application.Controller.Employee.SendCustomerCreditsFragmentController;
import com.example.Application.R;

public class SendCustomerCreditsFragmentView extends Fragment {

  private SendCustomerCreditsFragmentController controller;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater
        .inflate(R.layout.fragment_employee_send_customer_credits, container, false);

    controller = new SendCustomerCreditsFragmentController(root);
    Button sendCreditButton = root.findViewById(R.id.sendCreditButton);

    sendCreditButton.setOnClickListener(controller);
    return root;
  }
}