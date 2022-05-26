package com.xw.privatelib.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xw.privatelib.MDProgressDialog;
import com.xw.privatelib.utils.SharePrefrenceUtils;

public class BaseFragment extends Fragment {

    protected MDProgressDialog mdProgressDialog;
    protected SharePrefrenceUtils utils;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mdProgressDialog = new MDProgressDialog(view.getContext());
        utils = new SharePrefrenceUtils(view.getContext());
    }
}
