package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.LoginPOJO.LoginBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Profile extends AppCompatActivity {
    Toolbar toolbar;
    TextView name,mobile,email,address,zip;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView) findViewById(R.id.name);
        mobile = (TextView) findViewById(R.id.mobile);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        bar = (ProgressBar) findViewById(R.id.progress);
        zip = (TextView) findViewById(R.id.zip);
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

        bar.setVisibility(View.VISIBLE);
        Bean b = (Bean)getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<ViewBean> call = cr.view(b.userid);

        Log.d("ret" , b.userid);

        call.enqueue(new Callback<ViewBean>() {
            @Override
            public void onResponse(Call<ViewBean> call, Response<ViewBean> response) {

                Bean b = (Bean)getApplicationContext();

                Toast.makeText(b, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                Log.d("dsgsdf" , response.body().getData().getUsername());

                name.setText(response.body().getData().getUsername());
                mobile.setText(response.body().getData().getPhone());
                email.setText(response.body().getData().getEmail());
                address.setText(response.body().getData().getAddress());
                zip.setText(response.body().getData().getZip());

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ViewBean> call, Throwable t) {

                bar.setVisibility(View.GONE);



            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("PROFILE");
    }

}
