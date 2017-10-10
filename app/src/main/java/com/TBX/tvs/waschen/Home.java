package com.TBX.tvs.waschen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.LoginPOJO.LoginBean;
import com.TBX.tvs.waschen.ServicesPOJO.Datum;
import com.TBX.tvs.waschen.ServicesPOJO.ServiceBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Home extends Fragment {

    LinearLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4;

    RecyclerView recyclerView;
    GridLayoutManager manager;
    ProgressBar bar ;
    ServiceAdapter adapter;
    List<Datum> list;
    TextView contact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

        contact = (TextView)view.findViewById(R.id.contactus);
        bar = (ProgressBar) view.findViewById(R.id.progress);

        list = new ArrayList<>();

        adapter = new ServiceAdapter(getContext() , list);
        manager = new GridLayoutManager(getContext() , 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getContext().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<ServiceBean> call = cr.service();
        call.enqueue(new Callback<ServiceBean>() {
            @Override
            public void onResponse(Call<ServiceBean> call, Response<ServiceBean> response) {

                Bean b = (Bean)getContext().getApplicationContext();

                adapter.setgrid(response.body().getData());
                Toast.makeText(b, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ServiceBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext() , Contactus.class);

                startActivity(i);
                bar.setVisibility(View.GONE);
            }
        });


        return view;
    }


}
