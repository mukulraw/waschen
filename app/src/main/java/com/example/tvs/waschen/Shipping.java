package com.example.tvs.waschen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Shipping extends Fragment {

    TextView first,last,contact,address,city,state,pincode,country;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.shipping,container , false);

        first = (TextView)view.findViewById(R.id.fn);
        last = (TextView)view.findViewById(R.id.ln);
        contact = (TextView)view.findViewById(R.id.cn);
        address = (TextView)view.findViewById(R.id.add);
        city = (TextView)view.findViewById(R.id.city);
        state = (TextView)view.findViewById(R.id.state);
        pincode = (TextView)view.findViewById(R.id.pc);
        country = (TextView)view.findViewById(R.id.c);
        return view;
    }
}
