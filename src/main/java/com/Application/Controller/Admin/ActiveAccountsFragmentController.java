package com.Application.Controller.Admin;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.store.Account;
import com.Application.View.Admin.ActiveAccountsFragmentView;
import com.example.Application.R;

import java.util.ArrayList;

public class ActiveAccountsFragmentController {

  private static View view;
  private static Context appContext;
  private static ActiveAccountsFragmentView activeAccountsFragmentView;

  public ActiveAccountsFragmentController(View view,
      ActiveAccountsFragmentView activeAccountsFragmentView) {
    this.view = view;
    appContext = view.getContext();
    this.activeAccountsFragmentView = activeAccountsFragmentView;
  }

  public static void displayActiveAccounts(int userId) {
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(appContext);

    ArrayList<Account> accounts = mydb.getUserActiveAccountsH(userId);
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
