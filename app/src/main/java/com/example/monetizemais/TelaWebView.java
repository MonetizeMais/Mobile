package com.example.monetizemais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class TelaWebView extends AppCompatActivity {
    private WebView webView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_web_view);

        webView = findViewById(R.id.webview);

        handler = new Handler();

        handler.postDelayed(checkInternetRunnable, 10000);

        loadWebView();

        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadWebView();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadWebView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean verificaRede() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    // Runnable para verificar a conectividade à internet periodicamente
    private Runnable checkInternetRunnable = new Runnable() {
        @Override
        public void run() {
            loadWebView();
            // Agendar a próxima verificação após 10 segundos
            handler.postDelayed(this, 10000);
        }
    };

    private void loadWebView() {
        if (verificaRede()) {
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setDomStorageEnabled(true);
                webView.loadUrl("https://front-end-wfl2.onrender.com/");
            }
        }
    }
