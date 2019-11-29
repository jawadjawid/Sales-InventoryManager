package com.Application.View.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Application.Controller.Admin.BooksFragmentController;
import com.example.Application.R;


public class BooksFragmentView extends Fragment {

    private BooksFragmentController booksFragmentController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // booksFragmentController =
       //         ViewModelProviders.of(this).get(BooksFragmentController.class);
        View root = inflater.inflate(R.layout.fragment_admin_books, container, false);
     //   final TextView textView = root.findViewById(R.id.text_notifications);
      //  booksFragmentController.getText().observe(this, new Observer<String>() {
      //      @Override
      //      public void onChanged(@Nullable String s) {
      //          textView.setText(s);
     //       }
      //  });
        booksFragmentController = new BooksFragmentController(root);
        return root;
    }
}