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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.ContactusPOJO.ContactBean;
import com.TBX.tvs.waschen.CreatePOJO.CreateBean;
import com.TBX.tvs.waschen.SubmitPOJO.SubmitBean;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ContactFrag extends Fragment {

    EditText name,phone,email,comments;

    Button submit;

    TextView address , phone1 , email1;

    Toolbar toolbar;

    ProgressBar bar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_contactus , container , false);
        name = (EditText) v.findViewById(R.id.name);
        phone = (EditText)v. findViewById(R.id.phone);
        email = (EditText)v. findViewById(R.id.email);
        comments = (EditText) v.findViewById(R.id.comment);
        submit = (Button) v.findViewById(R.id.submit);
        bar = (ProgressBar) v.findViewById(R.id.progress);

        address = (TextView)v.findViewById(R.id.address);
        phone1 = (TextView)v.findViewById(R.id.phone1);
        email1 = (TextView)v.findViewById(R.id.email1);

        toolbar = (Toolbar) ((MainActivity) getContext()).findViewById(R.id.toolbar);


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String n = name.getText().toString();
                String p = phone.getText().toString();
                String e = email.getText().toString();
                String c = comments.getText().toString();



                if (n.length()>0){

                    if (Utils.isValidMobile(p)){



                        if (e.length()>0){


                            if (c.length()>0){

                                bar.setVisibility(View.VISIBLE);


                                Bean b = (Bean)getContext().getApplicationContext();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                allAPIs cr = retrofit.create(allAPIs.class);
                                Call<SubmitBean> call = cr.submit(n , e , p , c);

                                call.enqueue(new Callback<SubmitBean>() {
                                    @Override
                                    public void onResponse(Call<SubmitBean> call, Response<SubmitBean> response) {



                                        Toast.makeText(getContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        bar.setVisibility(View.GONE);

                                        Log.d("ghdkjh" , "response");

                                    }

                                    @Override
                                    public void onFailure(Call<SubmitBean> call, Throwable t) {

                                        Log.d("rgtr" , t.toString());

                                        bar.setVisibility(View.GONE);
                                    }
                                });








                            }
                            else {

                                Toast.makeText(getContext(), "Invalid Comments", Toast.LENGTH_SHORT).show();
                            }


                        }

                        else {

                            Toast.makeText(getContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {

                        Toast.makeText(getContext(), "Invalid Phone", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "Invalid Name", Toast.LENGTH_SHORT).show();
                }



                Log.d("jgdf" , "hmmm");



            }
        });

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allAPIs cr = retrofit.create(allAPIs.class);
        Call<ContactBean> call = cr.contact();

        call.enqueue(new Callback<ContactBean>() {
            @Override
            public void onResponse(Call<ContactBean> call, Response<ContactBean> response) {

                Bean b = (Bean)getContext().getApplicationContext();

                address.setText(response.body().getData().getAddress());
                phone1.setText(response.body().getData().getPhone());
                email1.setText(response.body().getData().getEmail());

                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ContactBean> call, Throwable t) {

                bar.setVisibility(View.GONE);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        DrawerLayout drawer = (DrawerLayout)((MainActivity) getContext()).findViewById(R.id.activity_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitle("Contact Us");

    }

}
