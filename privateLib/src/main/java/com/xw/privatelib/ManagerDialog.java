package com.xw.privatelib;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xw.privatelib.ui.ReportActivity;

public class ManagerDialog {

    private Context context;
    private PopupWindow popupWindow;
    private CallBack callBack;

    public ManagerDialog(Context context) {
        this.context = context;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void showPopupwindow(View view) {

        if (popupWindow == null) {

            View view1 = LayoutInflater.from(context).inflate(R.layout.layout_popupwindow,null);
            TextView tv_report = view1.findViewById(R.id.tv_report);
            TextView tv_block = view1.findViewById(R.id.tv_block);

            tv_report.setOnClickListener(v -> {
                Intent intent = new Intent(context, ReportActivity.class);
                context.startActivity(intent);
                popupWindow.dismiss();
            });

            tv_block.setOnClickListener(v -> {
                if (callBack != null) {
                    callBack.onBlock();
                }
                popupWindow.dismiss();
            });

            popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(context.getDrawable(R.drawable.ic_popupwindow_bg));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
        }

        popupWindow.showAsDropDown(view,-40,20, Gravity.RIGHT);
    }

    public interface CallBack {
        void onBlock();
    }
}
