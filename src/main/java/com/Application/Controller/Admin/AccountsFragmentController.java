package com.Application.Controller.Admin;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.Application.Model.users.User;
import com.Application.View.Admin.AccountsFragmentView;
import com.Application.View.Admin.ActiveAccountsFragmentView;
import com.Application.View.Admin.InactiveAccountsFragmentView;
import com.example.Application.R;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

public class AccountsFragmentController implements View.OnClickListener {

    private View viewView;
    private Context appContext;
    TabLayout tabLayout;
    ViewPager viewPager;
    private AccountsFragmentView accountsFragmentView;

    public AccountsFragmentController(View view, AccountsFragmentView accountsFragmentView) {
        this.viewView = view;
        this.accountsFragmentView = accountsFragmentView;
        appContext = view.getContext();
        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Active"));
        tabLayout.addTab(tabLayout.newTab().setText("Inactive"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(appContext,accountsFragmentView.getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        EditText userIdEditText = viewView.findViewById(R.id.userIdEditText);

        int customerId = Integer.parseInt(userIdEditText.getText().toString());
        ActiveAccountsFragmentController.displayActiveAccounts(customerId);
        InactiveAccountsFragmentController.displayInactiveAccounts(customerId);
    }
}