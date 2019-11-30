package com.Application.Controller.Admin;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.store.Account;
import com.Application.Model.users.User;
import com.Application.View.Admin.ActiveAccountsFragmentView;
import com.Application.View.Admin.InactiveAccountsFragmentView;
import com.example.Application.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class InactiveAccountsFragmentController {

    private static View view;
    private static Context appContext;
    private static InactiveAccountsFragmentView inactiveAccountsFragmentView;

    public InactiveAccountsFragmentController(View view, InactiveAccountsFragmentView inactiveAccountsFragmentView) {
        this.view = view;
        appContext = view.getContext();
        this.inactiveAccountsFragmentView = inactiveAccountsFragmentView;
    }

    public static void displayInactiveAccounts(int userId) {
        DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);

        ArrayList<Account> accounts = mydb.getUserInactiveAccountsH(userId);
        ArrayList<String> accountIds = new ArrayList<>();
        for (Account account : accounts) {
            accountIds.add("" + account.getId());
        }
        //    Collections.sort(accountIds);

        ArrayAdapter adapter = new ArrayAdapter<String>(appContext,
                R.layout.content_listview, accountIds);

        ListView listView = (ListView) view.findViewById(R.id.accountsList);
        listView.setAdapter(adapter);
    }
}
