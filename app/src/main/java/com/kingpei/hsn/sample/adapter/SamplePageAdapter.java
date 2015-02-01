package com.kingpei.hsn.sample.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;

import com.kingpei.hsn.lib.TabDeletionManager;
import com.kingpei.hsn.sample.SampleFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/1/30.
 */
public class SamplePageAdapter extends FragmentPagerAdapter implements TabDeletionManager.OnTabRemovedListener{

    private static  final String TAG = SamplePageAdapter.class.getSimpleName();

    private String[] titles = {"头条", "新闻", "财经", "娱乐", "体育", "军情观察", "国际政治", "本地"};
    private ArrayList<String> tabTitles = new ArrayList<String>();

    public SamplePageAdapter(FragmentManager fm) {
        super(fm);

        tabTitles.addAll(Arrays.asList(titles));
    }

    @Override
    public Fragment getItem(int position) {
        SampleFragment sampleFragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", tabTitles.get(position) + "内容");
        sampleFragment.setArguments(bundle);

        Log.v(TAG, "getItem" + tabTitles.get(position));
        return sampleFragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);

        if(fragment != null && fragment instanceof SampleFragment){
            ((SampleFragment) fragment).setContent(tabTitles.get(position));
        }

        Log.v(TAG, "instantiateItem" + tabTitles.get(position));

        return fragment;
    };

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

    @Override
    public void removeTabs(SparseBooleanArray booleanArray) {

        ArrayList<String> tabsTobeRemove = new ArrayList<String>();

        for (int i = 0; i < getCount(); i++) {
            boolean isRemove = booleanArray.get(i);

            if(isRemove){
                tabsTobeRemove.add(tabTitles.get(i));
                Log.v(TAG, "" + tabTitles.get(i));
            }
        }

        tabTitles.removeAll(tabsTobeRemove);

        notifyDataSetChanged();
    }


    @Override
    public int getCurrentIndex(SparseBooleanArray booleanArray, int oldIndex) {
        int index = oldIndex;

        for (int i = 0; i < getCount(); i++) {
            boolean isRemove = booleanArray.get(i);
            if(isRemove && i <= oldIndex){
                index--;
            }
        }
        return index;
    }
}
