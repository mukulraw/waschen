package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    Toolbar toolbar;
    TextView name1,mobile2,email3,address4,zip5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name1 = (TextView) findViewById(R.id.name);
        mobile2 = (TextView) findViewById(R.id.mobile);
        email3 = (TextView) findViewById(R.id.email);
        address4 = (TextView) findViewById(R.id.address);
        zip5 = (TextView) findViewById(R.id.zip);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("PROFILE");
    }

}
