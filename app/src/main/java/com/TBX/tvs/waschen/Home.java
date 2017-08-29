package com.TBX.tvs.waschen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class Home extends Fragment {

    LinearLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page , container , false);
        relativeLayout1 = (LinearLayout)view.findViewById(R.id.relative1);
        relativeLayout2 = (LinearLayout)view.findViewById(R.id.relative2);
        relativeLayout3 = (LinearLayout)view.findViewById(R.id.relative3);
        relativeLayout4 = (LinearLayout)view.findViewById(R.id.relativ4);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                BucketFragment buk= new BucketFragment();
                ft.replace(R.id.replace, buk);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                BucketFragment buk= new BucketFragment();
                ft.replace(R.id.replace, buk);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                BucketFragment buk= new BucketFragment();
                ft.replace(R.id.replace, buk);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                BucketFragment buk= new BucketFragment();
                ft.replace(R.id.replace, buk);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }


}
