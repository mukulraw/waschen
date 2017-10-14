package com.TBX.tvs.waschen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Billing extends Fragment {

    TextView city  , address , pincode , email ,state , contact , shipadd , shipdiff , edit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.billinf_layout , container ,false);

        city = (TextView)view.findViewById(R.id.city);
        edit = (TextView)view.findViewById(R.id.edit);
        address = (TextView)view.findViewById(R.id.address);
        pincode = (TextView)view.findViewById(R.id.pincode);
        email = (TextView)view.findViewById(R.id.email);
        state = (TextView)view.findViewById(R.id.state);
        contact = (TextView)view.findViewById(R.id.contact);
        shipadd = (TextView)view.findViewById(R.id.shipadd);
        shipdiff = (TextView)view.findViewById(R.id.shipdiff);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        return  view;
    }
}
