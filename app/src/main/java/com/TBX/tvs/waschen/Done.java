package com.TBX.tvs.waschen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.TBX.tvs.waschen.CouponPOJO.CouponBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Done extends Fragment{

    TextView cont , order;

    ViewPager pager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.done , container , false);

        pager = (ViewPager)((Checkout)getContext()).findViewById(R.id.pager);

        cont = (TextView)view.findViewById(R.id.cont);

        order = (TextView)view.findViewById(R.id.order);


        Bean b = (Bean)getContext().getApplicationContext();

       order.setText("Your Order# is " + b.order_id);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().finish();
                Intent i = new Intent(getContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(i);
                getActivity().finish();
            }
        });

        return view;

    }
}
