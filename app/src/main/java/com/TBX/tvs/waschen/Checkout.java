package com.TBX.tvs.waschen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Checkout extends AppCompatActivity {

    Toolbar toolbar;
    CustomViewPager pager;
    PagerAdapter paadapter;
    TabLayout tabs;
    String total;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        total = getIntent().getStringExtra("total");
        date = getIntent().getStringExtra("date");


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        pager = (CustomViewPager) findViewById(R.id.pager);

        pager.setPagingEnabled(false);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setTitle("Check Out Details");

        tabs = (TabLayout) findViewById(R.id.tabs);

        paadapter = new PagerAdapter(getSupportFragmentManager(), 4);


        tabs.addTab(tabs.newTab().setText("PickUp"));
        tabs.addTab(tabs.newTab().setText("Drop"));
        tabs.addTab(tabs.newTab().setText("Payment"));
        tabs.addTab(tabs.newTab().setText("Done"));

        pager.setAdapter(paadapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("PickUp");
        tabs.getTabAt(1).setText("Drop");
        tabs.getTabAt(2).setText("Payment");
        tabs.getTabAt(3).setText("Done");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm , int tab) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
            {
                return new PickUp();
            }
            else if (position == 1)
            {
                return new Drop();
            }
            if (position == 2)
            {

                Payment payment = new Payment();
                Bundle b = new Bundle();
                b.putString("total" , total);
                b.putString("date" , date);
                payment.setArguments(b);

                return payment;
            }
            else if (position == 3)
            {
                return new Done();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
