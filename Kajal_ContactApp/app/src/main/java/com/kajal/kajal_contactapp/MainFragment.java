package com.kajal.kajal_contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.TabLayout;
import android.view.ViewGroup;

/**
 * Created by 987747 on 9/28/2015.
 */
public class MainFragment extends Fragment {

    ViewPager viewPager;
    CustomPagerAdapter customPagerAdapter;

    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.main_frag,container,false);

        viewPager= (ViewPager) view.findViewById(R.id.view_pager);
        customPagerAdapter=new CustomPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(customPagerAdapter);

        Intent intent=getActivity().getIntent();
        int pos=intent.getIntExtra("pos",0);

        viewPager.setCurrentItem(pos);

        return view;
    }
}
