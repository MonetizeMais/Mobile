package com.example.monetizemais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TelaWebView extends AppCompatActivity {

    private WebView webView;
    private String currentUrl = "https://front-end-wfl2.onrender.com/";

    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isNetworkAvailable(context)) {
                if (webView.getUrl() == null) {
                    webView.loadUrl(currentUrl);
                } else {
                    webView.reload();
                }
            } else {
                Intent errorIntent = new Intent(TelaWebView.this, ErroRedeActivity.class);
                startActivity(errorIntent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_web_view);

        // Configurar a WebView
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Definir WebViewClient para abrir URLs dentro da WebView
        webView.setWebViewClient(new WebViewClient());

        // Carregar a URL inicial
        webView.loadUrl(currentUrl);

        // Registrar o BroadcastReceiver para monitorar a conectividade
        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }

    // Método auxiliar atualizado para verificar se a conexão de rede está disponível
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return capabilities != null && (
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            );
        }
        return false;
    }
}
