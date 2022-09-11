package ru.vbnz.deltasofttest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;


public class MainActivity extends AppCompatActivity {

    private FirebaseWrapper mFirebaseWrapper;
    private DeviceChecker mDeviceChecker;

    private boolean mCanGoBack;

    private void ShowContent() {
        startActivity(new Intent(this, Content.class));
    }

    private void UpdateContent() {
        getSupportActionBar().hide();
        String url = mFirebaseWrapper.GetUrl();
        if (mDeviceChecker.IsUserDevice() && !url.isEmpty())
            ShowWeb(url);
        else
            ShowContent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCanGoBack = true;
        mDeviceChecker = new DeviceChecker(this);
        mFirebaseWrapper = new FirebaseWrapper(this);
        UpdateContent();
    }

    @Override
    protected void onResume(){
        super.onResume();
        UpdateContent();
    }

    @Override
    public void onBackPressed() {
        if (mCanGoBack) super.onBackPressed();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void ShowWeb(String url) {
        setContentView(R.layout.activity_main);
        WebView webview = (WebView)findViewById(R.id.mainWeb);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
        mCanGoBack = false;
    }
}