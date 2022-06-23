package com.xw.privatelib.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xw.privatelib.LoadingDialog;
import com.xw.privatelib.utils.SharePrefrenceUtils;

public class BaseLoadingFragment extends Fragment {

    protected SharePrefrenceUtils utils;
    protected LoadingDialog loadingDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadingDialog = new LoadingDialog(view.getContext());
        utils = new SharePrefrenceUtils(view.getContext());
    }

    @Override
    public void onDestroyView() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
        super.onDestroyView();
    }
}
