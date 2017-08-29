package com.TBX.tvs.waschen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(Splash.this , SignIn.class);
                startActivity(intent);
                finish();



            }
        } , 1500);

    }
}
