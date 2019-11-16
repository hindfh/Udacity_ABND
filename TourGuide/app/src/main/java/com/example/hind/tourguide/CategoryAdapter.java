package com.example.hind.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hind on 23/05/2018 AD.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HolyPlacesFragment();
        } else if (position == 1) {
            return new ShoppingPlacesFragment();
        } else if (position == 2) {
            return new FoodPlacesFragment();
        } else {
            return new ActivityPlacesFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_holy_places);
        } else if (position == 1) {
            return mContext.getString(R.string.category_shopping_places);
        } else if (position == 2) {
            return mContext.getString(R.string.category_food_places);
        } else {
            return mContext.getString(R.string.category_activities);
        }
    }
}
