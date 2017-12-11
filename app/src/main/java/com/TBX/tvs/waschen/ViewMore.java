package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.CancelPOJO.CancelBean;
import com.TBX.tvs.waschen.FaqPOJO.FaqBean;
import com.TBX.tvs.waschen.OrderDetailPOJO.DetailBean;
import com.TBX.tvs.waschen.ViewMorePOJO.Datum;
import com.TBX.tvs.waschen.ViewMorePOJO.ViewMoreBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewMore extends AppCompatActivity {

    TextView date1 , id  , address , zip  , pending;

    ProgressBar bar;

    Button cancel;

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    ViewAdpter adapter;

    List<Datum>list;

    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        cancel = (Button) findViewById(R.id.cancel);

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


        toolbar.setTitle("Order Details");

        id = (TextView) findViewById(R.id.id);

        pending = (TextView) findViewById(R.id.pending);
        date1 = (TextView) findViewById(R.id.Date);
        bar = (ProgressBar) findViewById(R.id.progress);

        address = (TextView) findViewById(R.id.address);

        zip = (TextView) findViewById(R.id.zip);

        grid = (RecyclerView) findViewById(R.id.grid);

        manager = new GridLayoutManager(this , 1);

        list = new ArrayList<>();

        adapter = new ViewAdpter(this , list);

        grid.setAdapter(adapter);

        grid.setLayoutManager(manager);


        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<ViewMoreBean> call = cr.more(b.userid ,getIntent().getStringExtra("order"));

        Log.d("sdgnlkf" , b.userid);
        Log.d("hglkdf" , getIntent().getStringExtra("order"));

        call.enqueue(new Callback<ViewMoreBean>() {
            @Override
            public void onResponse(Call<ViewMoreBean> call, Response<ViewMoreBean> response) {

                zip.setText(response.body().getZip());
                id.setText(response.body().getOrderStatus());
                pending.setText(response.body().getOrderStatus1());
                date1.setText(response.body().getPlacedDate());
                address.setText(response.body().getAddress());

                adapter.setgrid(response.body().getData());

                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ViewMoreBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bar.setVisibility(View.VISIBLE);

                Bean b = (Bean)getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                allAPIs cr = retrofit.create(allAPIs.class);
                Call<CancelBean> call = cr.cancel(b.userid ,getIntent().getStringExtra("order"));

                call.enqueue(new Callback<CancelBean>() {
                    @Override
                    public void onResponse(Call<CancelBean> call, Response<CancelBean> response) {

                        Toast.makeText(ViewMore.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();


                        bar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<CancelBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                    }
                });


            }
        });


    }
}
