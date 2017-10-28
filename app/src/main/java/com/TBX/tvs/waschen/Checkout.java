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
    ViewPager pager;
    PagerAdapter paadapter;
    TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);

        tabs = (TabLayout) findViewById(R.id.tabs);
        paadapter = new PagerAdapter(getSupportFragmentManager(), 4);


        tabs.addTab(tabs.newTab().setText("PickUp").setIcon(R.drawable.one));
        tabs.addTab(tabs.newTab().setText("Drop").setIcon(R.drawable.two));
        tabs.addTab(tabs.newTab().setText("Payment").setIcon(R.drawable.three));
        tabs.addTab(tabs.newTab().setText("Done").setIcon(R.drawable.chaar));

        pager.setAdapter(paadapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("PickUp").setIcon(R.drawable.one);
        tabs.getTabAt(1).setText("Drop").setIcon(R.drawable.two);
        tabs.getTabAt(2).setText("Payment").setIcon(R.drawable.three);
        tabs.getTabAt(3).setText("Done").setIcon(R.drawable.chaar);

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
                return new Payment();
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
