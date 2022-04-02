package com.xw.aprivate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xw.privatelib.ui.SettingFragment;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SettingFragment fragment = SettingFragment.newInstance("#000000","","#2D2E35","#ffffff");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,fragment)
                .commit();
    }
}