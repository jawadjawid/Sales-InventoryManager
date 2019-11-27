package com.Application.View.Employee.authenticateNewEmployee;

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

public class AuthenticateNewEmployeeFragment extends Fragment {

    private AuthenticateNewEmployeeViewModel authenticateNewEmployeeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        authenticateNewEmployeeViewModel =
                ViewModelProviders.of(this).get(AuthenticateNewEmployeeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_authenticate_new_employee, container, false);
       // final TextView textView = root.findViewById(R.id.text_authenticate_new_employee);
       // authenticateNewEmployeeViewModel.getText().observe(this, new Observer<String>() {
      //      @Override
      //      public void onChanged(@Nullable String s) {
      //          textView.setText(s);
      //      }
     //   });
        return root;
    }
}