package com.xw.privatelib.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebViewClient;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xw.privatelib.R;
import com.xw.privatelib.web.MediaUtility;
import com.xw.privatelib.web.OpenFileWebChromeClient;

import java.io.File;

import io.reactivex.disposables.Disposable;

public class WebViewActivity extends AppCompatActivity {

    private AgentWeb mAgentWeb;
    private String loadUrl = "";
    private OpenFileWebChromeClient mOpenFileWebChromeClient;
    private String[] permission = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        FrameLayout frameLayout = findViewById(R.id.frameLayout_web);

        loadUrl = getIntent().getStringExtra("load_url");

        if (TextUtils.isEmpty(loadUrl)) {
            Toast.makeText(this, "loadUrl is null", Toast.LENGTH_SHORT).show();
            finish();
        }

        rxPermissions = new RxPermissions(this);
        Disposable d = rxPermissions.request(permission[0],permission[1],permission[2])
                .subscribe(granted -> {
                    if (!granted) {
                        Toast.makeText(this, "Please enable the necessary permissions", Toast.LENGTH_SHORT).show();
                    }
                });

        mOpenFileWebChromeClient = new OpenFileWebChromeClient(this);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((ViewGroup) frameLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT))
                .closeIndicator()
                .setWebViewClient(webViewClient)
                .setWebChromeClient(mOpenFileWebChromeClient)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(loadUrl);

        WebSettings webSettings = mAgentWeb.getAgentWebSettings().getWebSettings();
        webSettings.setLoadWithOverviewMode(true);
        //下面这些
        webSettings.setSupportZoom(true);//设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);//设置出现缩放工具
        webSettings.setUseWideViewPort(true);//扩大比例的缩放
        webSettings.setDisplayZoomControls(false);//隐藏缩放控件
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setTextZoom(100);
        webSettings.setJavaScriptEnabled(true);

    }

    private WebViewClient webViewClient = new WebViewClient(){

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            Log.e("info","======跳转url1=====" + url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
//            Log.e("info","======跳转url2=====" + url);
        }
    };

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OpenFileWebChromeClient.REQUEST_FILE_PICKER) {
            if (mOpenFileWebChromeClient.mFilePathCallback != null) {
                Uri result = data == null || resultCode != Activity.RESULT_OK ? null
                        : data.getData();
                if (result != null) {
                    String path = MediaUtility.getPath(getApplicationContext(),
                            result);
                    Uri uri = Uri.fromFile(new File(path));
                    mOpenFileWebChromeClient.mFilePathCallback
                            .onReceiveValue(uri);
                } else {
                    mOpenFileWebChromeClient.mFilePathCallback
                            .onReceiveValue(null);
                }
            }
            if (mOpenFileWebChromeClient.mFilePathCallbacks != null) {
                Uri result = data == null || resultCode != Activity.RESULT_OK ? null
                        : data.getData();
                if (result != null) {
                    String path = MediaUtility.getPath(getApplicationContext(),
                            result);
                    Uri uri = Uri.fromFile(new File(path));
                    mOpenFileWebChromeClient.mFilePathCallbacks
                            .onReceiveValue(new Uri[] { uri });
                } else {
                    mOpenFileWebChromeClient.mFilePathCallbacks
                            .onReceiveValue(null);
                }
            }

            mOpenFileWebChromeClient.mFilePathCallback = null;
            mOpenFileWebChromeClient.mFilePathCallbacks = null;
        }
    }
}