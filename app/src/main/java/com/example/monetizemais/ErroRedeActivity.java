package com.example.monetizemais;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ErroRedeActivity extends AppCompatActivity {

    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isNetworkAvailable(context)) {
                // Conexão voltou, fechar a tela de erro e retornar para a TelaWebView
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erro_rede);

        // Registrar o BroadcastReceiver para monitorar a conectividade
        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver); // Desregistrar o receiver ao destruir a activity
    }

    // Método auxiliar para verificar se a conexão de rede está disponível
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Sobrescreve o botão de voltar para fechar o aplicativo
    @Override
    public void onBackPressed() {
        // Finaliza todas as atividades e fecha o aplicativo
        finishAffinity();
    }
}