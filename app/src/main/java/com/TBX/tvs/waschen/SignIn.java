package com.TBX.tvs.waschen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    EditText email,pass;
    TextView signin , create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = (EditText) findViewById(R.id.e);
        pass = (EditText) findViewById(R.id.p);

        create = (TextView)findViewById(R.id.create);

        signin = (TextView) findViewById(R.id.sign);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignIn.this , MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SignIn.this , CreateAccount.class);
                startActivity(i);

            }
        });



    }
}
