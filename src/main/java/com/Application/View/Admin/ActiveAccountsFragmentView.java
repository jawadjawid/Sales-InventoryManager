package com.Application.View.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Application.Controller.Admin.ActiveAccountsFragmentController;
import com.example.Application.R;

public class ActiveAccountsFragmentView extends Fragment {

    ActiveAccountsFragmentController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment  
        View root = inflater.inflate(R.layout.fragment_admin_active_accounts, container, false);

        controller = new ActiveAccountsFragmentController(root, this);
        return root;
    }

}  