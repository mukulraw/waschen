package com.TBX.tvs.waschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class ProfileEdit extends AppCompatActivity {

     EditText fn , ln , city , state , contact , address , country;

      Spinner pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        fn = (EditText) findViewById(R.id.fn);
        contact = (EditText) findViewById(R.id.cn);
        ln = (EditText) findViewById(R.id.ln);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        address = (EditText) findViewById(R.id.add);
        pincode = (Spinner) findViewById(R.id.pincode);
        address = (EditText) findViewById(R.id.add);
        country = (EditText) findViewById(R.id.country);
    }
}
