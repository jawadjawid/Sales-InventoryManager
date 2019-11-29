package com.Application.View.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Application.Controller.Admin.EmployeesFragmentController;
import com.example.Application.R;


public class EmployeesFragmentView extends Fragment {

    private EmployeesFragmentController employeesFragmentController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
     //   employeesFragmentController =
          //      ViewModelProviders.of(this).get(EmployeesFragmentController.class);
        View root = inflater.inflate(R.layout.fragment_admin_employees, container, false);
      //  final TextView textView = root.findViewById(R.id.text_home);
      //  employeesFragmentController.getText().observe(this, new Observer<String>() {
      ///      @Override
       //     public void onChanged(@Nullable String s) {
      //          textView.setText(s);
      //      }
      //  });
        employeesFragmentController = new EmployeesFragmentController(root);
        return root;
    }
}