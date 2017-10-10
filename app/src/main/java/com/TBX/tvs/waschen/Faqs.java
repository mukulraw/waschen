package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.FaqPOJO.Datum;
import com.TBX.tvs.waschen.FaqPOJO.FaqBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Faqs extends AppCompatActivity {

    Toolbar toolbar;

    ProgressBar bar;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    FaqAdapter adapter;
    List<Datum>list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        list = new ArrayList<>();
        adapter = new FaqAdapter(this , list);
        manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        bar = (ProgressBar) findViewById(R.id.progress);
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
        Call<FaqBean> call = cr.faq();

        call.enqueue(new Callback<FaqBean>() {
            @Override
            public void onResponse(Call<FaqBean> call, Response<FaqBean> response) {

                Bean b = (Bean)getApplicationContext();
                Toast.makeText(b, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                adapter.Setgrid(response.body().getData());
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<FaqBean> call, Throwable t) {

                bar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("FAQS");
    }
}

