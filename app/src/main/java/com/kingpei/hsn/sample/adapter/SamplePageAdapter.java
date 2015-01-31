package com.kingpei.hsn.sample.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kingpei.hsn.sample.SampleFragment;

/**
 * Created by Administrator on 2015/1/30.
 */
public class SamplePageAdapter extends FragmentPagerAdapter {

    private String[] titles = {"头条", "新闻", "财经", "娱乐", "体育", "军情观察", "国际政治", "本地"};

    public SamplePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        SampleFragment sampleFragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", "content" + position);
        sampleFragment.setArguments(bundle);
        return sampleFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
