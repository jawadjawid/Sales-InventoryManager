package com.Application.Controller.Admin;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.Application.View.Admin.ActiveAccountsFragmentView;
import com.Application.View.Admin.InactiveAccountsFragmentView;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ActiveAccountsFragmentView activeAccountsFragmentView = new ActiveAccountsFragmentView();
                return activeAccountsFragmentView;
            case 1:
                InactiveAccountsFragmentView inactiveAccountsFragmentView = new InactiveAccountsFragmentView();
                return inactiveAccountsFragmentView;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}