package com.example.lifelinev2.Patient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Khizer Mehmood on 12/1/2018.
 */

public class ProfilePagerAdapter extends FragmentPagerAdapter {

    int numTabs;

    public ProfilePagerAdapter(FragmentManager fm, int NoTabs) {
        super(fm);
        this.numTabs = NoTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0 :
                ProfileInfo profileInfo = new ProfileInfo();
                return profileInfo;
            case 1 :
               ProfileEdit profileEdit = new ProfileEdit();
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
