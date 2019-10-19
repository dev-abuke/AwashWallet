package com.awash.mobile.banking.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.awash.mobile.banking.Classes.Constants;
import com.awash.mobile.banking.ui_fragments.paymentOptions.AirPayFragment;
import com.awash.mobile.banking.ui_fragments.paymentOptions.DsTvPayFragment;
import com.awash.mobile.banking.ui_fragments.paymentOptions.OtherPayFragment;
import com.awash.mobile.banking.ui_fragments.paymentOptions.SchoolPayFragment;

public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm, int behaviour) {
        super(fm,behaviour);
    }

    @Override
    public int getCount() {
        return Constants.TAB_TITLES.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SchoolPayFragment();
            case 1:
                return new AirPayFragment();
            case 2:
                return new DsTvPayFragment();
            case 3:
                return new OtherPayFragment();
        }

        throw new IllegalStateException("There's no fragment for position " + position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.TAB_TITLES[position];
    }
}