package com.TBX.tvs.waschen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {

    Toolbar toolbar;
    EditText user,email,pwd,retype;
    TextView create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitle("SEARCH");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAccount.this , SignIn.class);
                startActivity(i);
                finish();
            }
        });

        user = (EditText) findViewById(R.id.user);
        email = (EditText) findViewById(R.id.emailid);
        pwd = (EditText) findViewById(R.id.pwd);
        create = (TextView) findViewById(R.id.createaccount);
        retype = (EditText) findViewById(R.id.retype);
    }
}
