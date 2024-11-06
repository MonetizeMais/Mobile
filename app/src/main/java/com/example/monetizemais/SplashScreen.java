package com.example.monetizemais;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView GIF = findViewById(R.id.imageView);

        Glide.with(this)
                .load(R.drawable.porcoanimado)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Garantir cache eficiente
                .into(GIF);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                carregar();
            }
        }, 7000);
    }

    public void carregar(){
        startActivity(new Intent(this, TelaWebView.class));
        finish();
    }
}