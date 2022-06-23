package com.xw.privatelib.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xw.privatelib.LoadingDialog;
import com.xw.privatelib.utils.AtyContainer;
import com.xw.privatelib.utils.SharePrefrenceUtils;

public class BaseLoadingActivity extends AppCompatActivity {

    protected LoadingDialog loadingDialog;
    protected SharePrefrenceUtils utils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AtyContainer.getInstance().addActivity(this);
        loadingDialog = new LoadingDialog(this);
        utils = new SharePrefrenceUtils(this);
    }

    @Override
    protected void onDestroy() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
        super.onDestroy();
    }
}
