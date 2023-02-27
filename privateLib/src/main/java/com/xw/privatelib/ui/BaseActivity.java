package com.xw.privatelib.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xw.privatelib.MDProgressDialog;
import com.xw.privatelib.utils.AtyContainer;
import com.xw.privatelib.utils.SharePrefrenceUtils;

public class BaseActivity extends AppCompatActivity {

    protected MDProgressDialog mdProgressDialog;
    protected SharePrefrenceUtils utils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AtyContainer.getInstance().addActivity(this);
        mdProgressDialog = new MDProgressDialog(this);
        utils = new SharePrefrenceUtils(this);

    }

    @Override
    protected void onDestroy() {
        if (mdProgressDialog != null) {
            mdProgressDialog.cancel();
        }
        super.onDestroy();
    }
}
