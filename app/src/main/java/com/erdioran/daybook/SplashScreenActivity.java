package com.erdioran.daybook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.erdioran.daybook.Helper.NetworkCheck;
import com.erdioran.daybook.StartLogin.LoginActivity;
import com.erdioran.daybook.StartLogin.StartActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    NetworkInfo info;
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();
        check();
    }



    @SuppressLint("WrongConstant")
    public void check() {
        Timer t = new Timer();
        boolean checkConnection = new NetworkCheck().checkConnection(this);
        if (checkConnection) {
            t.schedule(new splash(), 3000);
        } else {
            Toast.makeText(SplashScreenActivity.this,
                    "Connection Not Found", SPLASH_TIME_OUT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    check();
                }
            }, 2000);
        }
    }

    class splash extends TimerTask {
        @Override
        public void run() {
            Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
            finish();
            startActivity(i);
        }
    }
}
