package com.xw.privatelib.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xw.privatelib.R;
import com.xw.privatelib.utils.AtyContainer;
import com.xw.privatelib.utils.SharePrefrenceUtils;
import com.xw.privatelib.utils.UITools;

public class SetWhiteActivity extends BaseActivity {

    private SharePrefrenceUtils utils;
    private String sqlChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_white);

        String themeColor = getIntent().getStringExtra("theme_color");
        sqlChat = getIntent().getStringExtra("sql_string");
        FrameLayout frame_container = findViewById(R.id.frame_container);
        UITools.initTitleColorBar(this, frame_container, themeColor);
        ImageView image_back = findViewById(R.id.image_back);
        image_back.setOnClickListener(v -> finish());

        utils = new SharePrefrenceUtils(this);
        findViewById(R.id.tv_service).setOnClickListener(v -> {
            Intent intent = new Intent(this, ProtectWebActivity.class);
            intent.putExtra("load_url","file:android_asset/terms.html");
            startActivity(intent);
        });

        findViewById(R.id.tv_Privacy).setOnClickListener(v -> {
            Intent intent = new Intent(this, ProtectWebActivity.class);
            intent.putExtra("load_url","file:android_asset/policy.html");
            startActivity(intent);
        });

        findViewById(R.id.tv_account).setOnClickListener(v -> {
            utils.saveString(sqlChat,"");
            AtyContainer.getInstance().finishAllActivity();
        });
    }
}