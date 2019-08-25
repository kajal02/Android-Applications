package com.kajal.kajal_contactapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 987747 on 9/28/2015.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return PagerFragment.newInstace(i);
    }

    @Override
    public int getCount() {
        return ContactManager.getInstance().getContactList().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return super.getPageTitle(position);
    }
}
