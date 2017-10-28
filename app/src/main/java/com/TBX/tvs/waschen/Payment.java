package com.TBX.tvs.waschen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.TBX.tvs.waschen.GetZipPOJO.GetZipBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 8/24/2017.
 */

public class Payment extends Fragment {

    TextView cont;
    ViewPager pager;
    EditText code;
    Button redeem;
    RadioGroup radio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment , container , false);

        //pager = ((Checkout.this)getContext()).findViewById(R.id.pager);

        pager = (ViewPager)((Checkout)getContext()).findViewById(R.id.pager);

        cont = (TextView)view.findViewById(R.id.cont);

        redeem = (Button)view.findViewById(R.id.redeem);
        code = (EditText) view.findViewById(R.id.code);
        radio = (RadioGroup) view.findViewById(R.id.radio);


        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Bean b = (Bean)getContext().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                allAPIs cr = retrofit.create(allAPIs.class);
                Call<GetZipBean> call = cr.bean();




            }
        });


        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(3);

            }
        });
        return  view;
    }
}
