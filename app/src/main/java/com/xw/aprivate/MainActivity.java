package com.xw.aprivate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.xw.privatelib.ManagerDialog;
import com.xw.privatelib.ui.ChatActivity;
import com.xw.privatelib.ui.FeedbackActivity;
import com.xw.privatelib.ui.FeedbackWhiteActivity;
import com.xw.privatelib.ui.ReportActivity;
import com.xw.privatelib.ui.SetWhiteActivity;
import com.xw.privatelib.ui.SettingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, FeedbackActivity.class);
            intent.putExtra("theme_color","#ffffff");
            intent.putExtra("type","white");
//            Intent intent = new Intent(this,MainActivity2.class);
            startActivity(intent);
        });
    }
}