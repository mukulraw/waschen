package com.TBX.tvs.waschen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        /*if (position == 0)
        {
            return new Billing();
        }
        else if (position == 1)
        {
            return new Shipping();
        }*/
        if (position == 0)
        {
            return new Payment();
        }
        else if (position == 1)
        {
            return new Done();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
