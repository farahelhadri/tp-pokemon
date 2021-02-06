package com.example.pokemonbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        intent = new Intent(this, MainActivity.class);
        splashScreen(7000);

    }

        public void splashScreen (final int x)
        {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                    finish();
                }
            }).start();

    }
}