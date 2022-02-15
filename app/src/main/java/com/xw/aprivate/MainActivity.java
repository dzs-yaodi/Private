package com.xw.aprivate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.xw.privatelib.ManagerDialog;
import com.xw.privatelib.ui.ReportActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        ManagerDialog managerDialog = new ManagerDialog(this);

        btn.setOnClickListener(v -> {
//            Intent intent = new Intent(this, ReportActivity.class);
//            startActivity(intent);
            managerDialog.showPopupwindow(btn);
        });
    }
}