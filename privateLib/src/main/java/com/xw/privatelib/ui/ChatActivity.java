package com.xw.privatelib.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xw.privatelib.R;
import com.xw.privatelib.data.ChatAdapter;
import com.xw.privatelib.data.ChatUser;
import com.xw.privatelib.data.EventChat;
import com.xw.privatelib.utils.SharePrefrenceUtils;
import com.xw.privatelib.utils.UITools;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {

    private List<String> messageList = new ArrayList<>();
    private SharePrefrenceUtils utils;
    private List<ChatUser> userModels;
    private boolean isHaveUser = false;
    private String sqlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FrameLayout frame_container = findViewById(R.id.frame_container);
        ImageView image_back = findViewById(R.id.image_back);
        image_back.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        EditText edit_msg = findViewById(R.id.edit_msg);
        TextView tv_send = findViewById(R.id.tv_send);
        TextView tv_name = findViewById(R.id.tv_name);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String themeColor = getIntent().getStringExtra("theme_color");
        sqlString = getIntent().getStringExtra("sql_string");
        String type = getIntent().getStringExtra("type");
        if ("white".equals(type)) {
            image_back.setImageResource(R.drawable.img_back_black);
            tv_name.setTextColor(Color.BLACK);
        }

        UITools.initTitleColorBar(this, frame_container, themeColor);
        ChatUser model = (ChatUser) getIntent().getSerializableExtra("data");

        utils = new SharePrefrenceUtils(this);
        if (model == null) {
            finish();
            return;
        }

        tv_name.setText(model.name);
        ChatAdapter chatAdapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(chatAdapter);

        String users = utils.getString(sqlString);
        userModels = new Gson().fromJson(users, new TypeToken<List<ChatUser>>() {}.getType());
        if (userModels == null) {
            userModels = new ArrayList<>();
        }

        for (ChatUser user : userModels) {
            if (user.name.equals(model.name)) {
                isHaveUser = true;
            }
        }

        tv_send.setOnClickListener(v -> {
            String msg = edit_msg.getText().toString().trim();

            if (!TextUtils.isEmpty(msg)) {
                messageList.add(msg);
                chatAdapter.notifyDataSetChanged();
                edit_msg.setText(null);
                recyclerView.scrollToPosition(messageList.size() - 1);

                if (!isHaveUser) {
                    userModels.add(model);
                    String userString = new Gson().toJson(userModels);
                    utils.saveString(sqlString,userString);
                    EventBus.getDefault().post(new EventChat());
                    isHaveUser = true;
                }
            }
        });
    }
}