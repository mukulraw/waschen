package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Contactus extends AppCompatActivity {
    Toolbar toolbar;
    EditText name,phone,email,comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        name = (EditText) findViewById(R.id.full);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.eid);
        comments = (EditText) findViewById(R.id.contact);
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
        toolbar.setTitle("CONTACT US");
    }

}

