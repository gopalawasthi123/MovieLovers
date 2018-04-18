package com.example.gopalawasthi.movielovers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gopal Awasthi on 18-04-2018.
 */

public class FavouritePagerAdapter extends FragmentPagerAdapter {

private Context context;
    public FavouritePagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context =  context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FavouriteFragment();

            case 1:
                return new FavouriteTvFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){

            return "movies";
        }else{
            return "tv";
        }

    }
}
