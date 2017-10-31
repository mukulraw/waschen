package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.TBX.tvs.waschen.FaqPOJO.FaqBean;
import com.TBX.tvs.waschen.OrderPojo.Datum;
import com.TBX.tvs.waschen.OrderPojo.OrderBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class History extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView recyclerView;

    GridLayoutManager manager;

    HistoryAdapter adapter;

    ProgressBar bar;

    List<Datum>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        bar = (ProgressBar) findViewById(R.id.progress);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        manager = new GridLayoutManager(this ,1);

        list = new ArrayList<>();

        adapter = new HistoryAdapter(this , list);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(manager);

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
        Call<OrderBean> call = cr.order(b.userid);

        call.enqueue(new Callback<OrderBean>() {
            @Override
            public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {


                adapter.setgrid(response.body().getData());
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<OrderBean> call, Throwable t) {


                bar.setVisibility(View.GONE);

            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("HISTORY");
    }
}
