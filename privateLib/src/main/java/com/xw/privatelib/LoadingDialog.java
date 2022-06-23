package com.xw.privatelib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class LoadingDialog extends Dialog {

    private AnimationDrawable animationDrawable;
    private int imageResouce = R.drawable.lib_fc_editor_loading_anim;
    private int size ;
    private View view;
    private final ImageView imageLoading;
    private final LinearLayout linearLoading;

    public LoadingDialog(@NonNull Context context) {
        super(context);

        view = LayoutInflater.from(context).inflate(R.layout.lib_fc_loading_layout,null);
        linearLoading = view.findViewById(R.id.linear_loading);
        imageLoading = view.findViewById(R.id.image_loading);
        size = context.getResources().getDisplayMetrics().widthPixels / 3 - 50 ;

        Drawable drawable = ContextCompat.getDrawable(context, imageResouce).mutate();
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
//        DrawableCompat.setTint(wrappedDrawable, themeColor);

        imageLoading.setBackground(wrappedDrawable);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        setCancelable(true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size,size);
        params.gravity = Gravity.CENTER;
        linearLoading.setLayoutParams(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(view);
    }

    @Override
    public void show() {
        super.show();
        if (animationDrawable == null) {
            animationDrawable = getAnimationDrawable(imageLoading.getBackground());
        }
        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    /**
     * 获取动画Drawable
     */
    private AnimationDrawable getAnimationDrawable(Drawable drawable) {
        Drawable d = DrawableCompat.unwrap(drawable);
        if (d instanceof AnimationDrawable) {
            return (AnimationDrawable) drawable;
        }
        return null;
    }
}
