package com.Application.View.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.Application.Controller.Admin.AccountsFragmentController;
import com.example.Application.R;


public class AccountsFragmentView extends Fragment {

  private AccountsFragmentController controller;


  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    // booksFragmentController =
    //         ViewModelProviders.of(this).get(BooksFragmentController.class);
    View root = inflater.inflate(R.layout.fragment_admin_accounts, container, false);
    //   final TextView textView = root.findViewById(R.id.text_notifications);
    //  booksFragmentController.getText().observe(this, new Observer<String>() {
    //      @Override
    //      public void onChanged(@Nullable String s) {
    //          textView.setText(s);
    //       }
    //  });
    controller = new AccountsFragmentController(root, this);

    Button selectCustomerIdButton = root.findViewById(R.id.selectCustomerIdForAccountsButton);
    selectCustomerIdButton.setOnClickListener(controller);
    return root;
  }


}