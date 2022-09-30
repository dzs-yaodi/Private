package com.xw.privatelib.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xw.privatelib.R;
import com.xw.privatelib.utils.Glide4Engine;
import com.xw.privatelib.utils.UITools;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class FeedbackActivity extends BaseActivity {

    public static final int REQUEST_IMAGE = 1002;
    private String[] permission = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private RxPermissions rxPermissions;
    private ImageView imageCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        FrameLayout frame_container = findViewById(R.id.frame_container);
        ImageView image_back = findViewById(R.id.image_back);
        image_back.setOnClickListener(v -> finish());

        String themeColor = getIntent().getStringExtra("theme_color");
        UITools.initTitleColorBar(this, frame_container, themeColor);

        rxPermissions = new RxPermissions(this);

        imageCamera = findViewById(R.id.image_camera);
        Button btn_submit = findViewById(R.id.btn_submit);

        GradientDrawable drawable = (GradientDrawable) btn_submit.getBackground();
        if (drawable != null) {
            drawable.setColor(Color.parseColor(themeColor));
            btn_submit.setBackground(drawable);
        }

        imageCamera.setOnClickListener(v -> {
            Disposable d = rxPermissions.request(permission[0],permission[1],permission[2])
                    .subscribe(granted -> {
                        if (!granted) {
                            Toast.makeText(this, "Please enable the necessary permissions", Toast.LENGTH_SHORT).show();
                        } else {
                            Matisse.from(this)
                                    .choose(MimeType.ofImage())
                                    .showSingleMediaType(true)
                                    .capture(true)
                                    .captureStrategy(new CaptureStrategy(true,getPackageName() + ".ui.FcFileProvider"))
                                    .maxSelectable(1)
                                    .imageEngine(new Glide4Engine())
                                    .forResult(REQUEST_IMAGE);
                        }
                    });
        });

        btn_submit.setOnClickListener(v -> {
            mdProgressDialog.show();
            btn_submit.postDelayed(() -> {
                mdProgressDialog.dismiss();
                finish();
            },500);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = Matisse.obtainPathResult(data);
            if (pathList.size() > 0 && !TextUtils.isEmpty(pathList.get(0))) {
                String path = pathList.get(0);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                imageCamera.setImageBitmap(bitmap);
            }
        }
    }
}