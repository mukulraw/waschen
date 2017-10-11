package com.TBX.tvs.waschen;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.TBX.tvs.waschen.FaqPOJO.Datum;
import com.TBX.tvs.waschen.FaqPOJO.FaqBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 10/11/2017.
 */

public class FaqFrag extends Fragment{

    ProgressBar bar;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    FaqAdapter adapter;
    List<Datum> list;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.activity_faqs , container , false);



        recyclerView = (RecyclerView)v. findViewById(R.id.recycler);
        list = new ArrayList<>();
        adapter = new FaqAdapter(getContext() , list);
        manager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        bar = (ProgressBar)v. findViewById(R.id.progress);

        bar.setVisibility(View.VISIBLE);
        Bean b = (Bean)getContext().getApplicationContext();
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

                Bean b = (Bean)getContext().getApplicationContext();
                Toast.makeText(b, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                adapter.Setgrid(response.body().getData());
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<FaqBean> call, Throwable t) {

                bar.setVisibility(View.GONE);
            }
        });


         return v;
    }
}
