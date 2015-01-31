package com.kingpei.hsn.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.kingpei.hsn.lib.HorizontalScrollNavigator;
import com.kingpei.hsn.sample.adapter.SamplePageAdapter;


public class NavigatorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_navigator_vp);
        viewPager.setAdapter(new SamplePageAdapter(getSupportFragmentManager()));
        HorizontalScrollNavigator horizontalScrollNavigator = (HorizontalScrollNavigator) findViewById(R.id.activity_navigator_horizontal_navigate_sv);
        horizontalScrollNavigator.setViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
