package com.Application.View.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Application.Controller.Admin.AccountsFragmentController;
import com.example.Application.R;


public class AccountsFragmentView extends Fragment {

    private AccountsFragmentController accountsFragmentController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
     //   accountsFragmentController =
      //          ViewModelProviders.of(this).get(AccountsFragmentController.class);
        View root = inflater.inflate(R.layout.fragment_admin_accounts, container, false);
       // final TextView textView = root.findViewById(R.id.text_dashboard);
       // dashboardViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
       // });
        accountsFragmentController = new AccountsFragmentController(root);

        return root;
    }
}