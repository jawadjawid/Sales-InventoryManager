package com.Application.View.Employee.makeNewAccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.Application.Controller.Employee.EmployeeOptionsController;
import com.Application.View.Employee.EmployeeOptionsView;
import com.Application.View.Employee.MainActivity;
import com.example.Application.R;


public class MakeNewAccountFragment extends Fragment {

    private MakeNewAccountViewModel makeNewAccountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        makeNewAccountViewModel =
                ViewModelProviders.of(this).get(MakeNewAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_make_new_account, container, false);
     //   final TextView textView = root.findViewById(R.id.text_make_new_account);
      //  makeNewAccountViewModel.getText().observe(this, new Observer<String>() {
      //      @Override
      //      public void onChanged(@Nullable String s) {
      //          textView.setText(s);
      //      }
      //  });
        NumberPicker numberPicker = root.findViewById(R.id.numberPicker);
        if (numberPicker != null) {
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(10);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
             //       String text = "Changed from " + oldVal + " to " + newVal + "but now it is" + picker.getValue();
            //        Toast.makeText(EmployeeOptionsController.getContext(), text, Toast.LENGTH_SHORT).show();
                }


            });
        }

        return root;
    }
}