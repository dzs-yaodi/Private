package com.xw.privatelib.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xw.privatelib.R;
import com.xw.privatelib.utils.AtyContainer;
import com.xw.privatelib.utils.SharePrefrenceUtils;

public class SettingFragment extends Fragment {

    private SharePrefrenceUtils utils;
    private String sqlStr;

    public static SettingFragment newInstance(String themeColor,String sqlStr,String bgColor,String textColor) {

        Bundle args = new Bundle();
        args.putString("theme_color",themeColor);
        args.putString("sql_string",sqlStr);
        args.putString("bg_color",bgColor);
        args.putString("text_color",textColor);
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linerMain = view.findViewById(R.id.linear_main);
        FrameLayout frameContaner = view.findViewById(R.id.frame_container);
        TextView tvService = view.findViewById(R.id.tv_service);
        TextView tvPrivacy = view.findViewById(R.id.tv_Privacy);
        TextView tvAccount = view.findViewById(R.id.tv_account);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String themeColor = bundle.getString("theme_color");
            sqlStr = bundle.getString("sql_string");
            String bgColor = bundle.getString("bg_color");
            String textColor = bundle.getString("text_color");

            if (!TextUtils.isEmpty(themeColor)) {
                frameContaner.setBackgroundColor(Color.parseColor(themeColor));
            }

            if (!TextUtils.isEmpty(bgColor)) {
                linerMain.setBackgroundColor(Color.parseColor(bgColor));
            }

            if (!TextUtils.isEmpty(textColor)) {
                tvService.setTextColor(Color.parseColor(textColor));
                tvPrivacy.setTextColor(Color.parseColor(textColor));
                tvAccount.setTextColor(Color.parseColor(textColor));
            }
        }

        utils = new SharePrefrenceUtils(view.getContext());
        tvService.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), ProtectWebActivity.class);
            intent.putExtra("load_url","file:android_asset/terms.html");
            startActivity(intent);
        });

        tvPrivacy.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), ProtectWebActivity.class);
            intent.putExtra("load_url","file:android_asset/policy.html");
            startActivity(intent);
        });

        tvAccount.setOnClickListener(v -> {
            utils.saveString(sqlStr,"");

            AtyContainer.getInstance().finishAllActivity();
        });

    }
}
