package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class ProfileFrag extends Fragment {

    TextView name,mobile,email,address,zip;

    ProgressBar bar;

    Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile, container, false);

        toolbar = (Toolbar) ((MainActivity) getContext()).findViewById(R.id.toolbar);

        name = (TextView) view.findViewById(R.id.name);
        mobile = (TextView) view.findViewById(R.id.mobile);
        email = (TextView) view.findViewById(R.id.email);
        address = (TextView) view.findViewById(R.id.address);
        bar = (ProgressBar) view.findViewById(R.id.progress);
        zip = (TextView) view.findViewById(R.id.zip);

        bar.setVisibility(View.VISIBLE);
        Bean b = (Bean) getContext().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<ViewBean> call = cr.view(b.userid);

        Log.d("ret", b.userid);

        call.enqueue(new Callback<ViewBean>() {
            @Override
            public void onResponse(Call<ViewBean> call, Response<ViewBean> response) {

                Bean b = (Bean) getContext().getApplicationContext();

                Toast.makeText(b, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                Log.d("dsgsdf", response.body().getData().getUsername());

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

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        DrawerLayout drawer = (DrawerLayout)((MainActivity) getContext()).findViewById(R.id.activity_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitle("My Profile");

    }
}

