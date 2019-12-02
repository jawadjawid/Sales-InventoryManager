package com.Application.Controller.Admin;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.interaction.AdminInteraction;
import com.example.Application.R;

import java.sql.SQLException;

public class BooksFragmentController implements View.OnClickListener {

    private View view;
    private Context appContext;

    public BooksFragmentController(View view) {
        this.view = view;
        appContext = view.getContext();
        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);
        try {
            String books = AdminInteraction.viewBooks(mydb);
            TextView booksTextView = view.findViewById(R.id.booksTextView);
            booksTextView.setText(books);
        }catch (SQLException e){

        }
    }

    @Override
    public void onClick(View view) {

    }
}