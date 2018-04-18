package com.example.gopalawasthi.movielovers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragment extends Fragment {

    private SmartTabLayout mSmartTabLayout;
    private ViewPager mViewPager;
    public RootFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_root, container, false);
        mSmartTabLayout = (SmartTabLayout) view.findViewById(R.id.tab_view_pager_fav);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager_fav);
        mViewPager.setAdapter(new FavouritePagerAdapter(getChildFragmentManager(), getContext()));
        mSmartTabLayout.setViewPager(mViewPager);

        return view;
    }

}
