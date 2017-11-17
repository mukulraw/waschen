package com.TBX.tvs.waschen;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.TBX.tvs.waschen.GetZipPOJO.GetZipBean;
import com.TBX.tvs.waschen.UpdateProfilePOJO.UpdateProfileBean;
import com.TBX.tvs.waschen.ViewProfilePOJO.ViewBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProfileEdit extends AppCompatActivity {

     EditText fn , ln , city , state , contact , address , country , email  , land;

     Spinner pincode;

     List<String> codes;

     String code = "";

    String date1 = "";

     ProgressBar bar;

     TextView update , birth;

     Toolbar toolbar;

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

        birth = (TextView) findViewById(R.id.birthday);

        land = (EditText) findViewById(R.id.landmark);

        codes = new ArrayList<>();

        pincode = (Spinner) findViewById(R.id.pc);

        country = (EditText) findViewById(R.id.country);

        email = (EditText) findViewById(R.id.email);

        bar = (ProgressBar) findViewById(R.id.progress);

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

        toolbar.setTitle("Edit Profile");


        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(ProfileEdit.this);
                dialog.setContentView(R.layout.pop_up);
                dialog.setCancelable(true);
                dialog.show();

                Button ok = (Button)dialog.findViewById(R.id.ok);
                final DatePicker date = (DatePicker) dialog.findViewById(R.id.picker);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String day = String.valueOf(date.getDayOfMonth());
                        String month = String.valueOf(date.getMonth() + 1);
                        String year = String.valueOf(date.getYear());

                        date1 = day + "-" + month + "-" + year;

                        birth.setText(day + "-" + month + "-" + year);

                        dialog.dismiss();

                    }
                });
            }

        });



        Bean b = (Bean)getApplicationContext();
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

                Bean b = (Bean)getApplicationContext();

                Toast.makeText(b, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                Log.d("dsgsdf", response.body().getData().getUserName());

                fn.setText(response.body().getData().getUserName());
                contact.setText(response.body().getData().getPhone());
                email.setText(response.body().getData().getEmail());
                address.setText(response.body().getData().getAddress());
               // pincode.setText(response.body().getData().getZipcode());

                state.setText(response.body().getData().getState());

                country.setText(response.body().getData().getCountry());
                city.setText(response.body().getData().getCity());
                land.setText(response.body().getData().getLandmark());
                birth.setText(response.body().getData().getBirthday());


                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ViewBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });

        bar.setVisibility(View.VISIBLE);



        Call<GetZipBean> call1 = cr.bean();

        call1.enqueue(new Callback<GetZipBean>() {
            @Override
            public void onResponse(Call<GetZipBean> call, Response<GetZipBean> response) {


                for (int i = 0 ; i < response.body().getDeliveryMethod().size() ; i++)
                {
                    codes.add(response.body().getDeliveryMethod().get(i).getCoupon());
                }


                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ProfileEdit.this,
                        android.R.layout.simple_spinner_item, codes);

                pincode.setAdapter(dataAdapter);


                //Toast.makeText(getContext(), response.body().getDeliveryMethod().get(0).getCoupon(), Toast.LENGTH_SHORT).show();
                //adapter.setdata(response.body().getDeliveryMethod());

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetZipBean> call, Throwable t) {

                bar.setVisibility(View.GONE);


            }
        });


        pincode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                code = codes.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        update = (TextView) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bar.setVisibility(View.VISIBLE);

                String f = fn.getText().toString();
                String c = contact.getText().toString();
                String ci = city.getText().toString();
                String s = state.getText().toString();
                String a = address.getText().toString();
                String  em = email.getText().toString();
                String coun = country.getText().toString();
                String bir = birth.getText().toString();
                String lan = land.getText().toString();

                Bean b = (Bean)getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                allAPIs cr = retrofit.create(allAPIs.class);
                Call<UpdateProfileBean> call2 = cr.updateprofile(b.userid , f , c , a , ci , code ,coun , bir , lan , s);
                call2.enqueue(new Callback<UpdateProfileBean>() {
                    @Override
                    public void onResponse(Call<UpdateProfileBean> call, Response<UpdateProfileBean> response) {


                        Toast.makeText(ProfileEdit.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();

                        bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<UpdateProfileBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                    }
                });






            }
        });


    }
}
