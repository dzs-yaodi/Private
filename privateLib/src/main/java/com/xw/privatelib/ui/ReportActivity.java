package com.xw.privatelib.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.privatelib.MDProgressDialog;
import com.xw.privatelib.R;
import com.xw.privatelib.utils.UITools;

public class ReportActivity extends BaseActivity {

    private MDProgressDialog mdProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        String themeColor = getIntent().getStringExtra("theme_color");
        if (TextUtils.isEmpty(themeColor)) {
            themeColor = "#5939D2";
        }
        FrameLayout frame_contaner = findViewById(R.id.frame_contaner);
        UITools.initTitleColorBar(this,frame_contaner,themeColor);

        findViewById(R.id.image_back).setOnClickListener(v -> finish());
        TextView tv_send = findViewById(R.id.tv_send);

        mdProgressDialog = new MDProgressDialog(this);
        tv_send.setOnClickListener(v -> {
            mdProgressDialog.show();
            tv_send.postDelayed(()-> {
                mdProgressDialog.dismiss();
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            },500);
        });
    }
}