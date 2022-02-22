package com.xw.privatelib.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xw.privatelib.MDProgressDialog;
import com.xw.privatelib.utils.AtyContainer;

public class BaseActivity extends AppCompatActivity {

    protected MDProgressDialog mdProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AtyContainer.getInstance().addActivity(this);
        mdProgressDialog = new MDProgressDialog(this);
    }

    @Override
    protected void onDestroy() {
        if (mdProgressDialog != null) {
            mdProgressDialog.cancel();
        }
        super.onDestroy();
    }
}
