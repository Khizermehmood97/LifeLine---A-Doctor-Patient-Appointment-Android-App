package com.example.lifelinev2.Doctor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Khizer Mehmood on 12/1/2018.
 */

public class d_ProfilePagerAdapter extends FragmentPagerAdapter {

    int numTabs;

    public d_ProfilePagerAdapter(FragmentManager fm, int NoTabs) {
        super(fm);
        this.numTabs = NoTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0 :
                d_ProfileInfo dProfileInfo = new d_ProfileInfo();
                return dProfileInfo;
            case 1 :
               d_ProfileEdit profileEdit = new d_ProfileEdit();
                return profileEdit;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
