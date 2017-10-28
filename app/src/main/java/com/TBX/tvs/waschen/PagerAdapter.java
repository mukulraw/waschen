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
