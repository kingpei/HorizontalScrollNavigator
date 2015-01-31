package com.kingpei.hsn.lib;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/1/29.
 */
public class HorizontalScrollNavigator extends HorizontalScrollView implements ViewPager.OnPageChangeListener{

    private static final String TAG = HorizontalScrollNavigator.class.getName();

    private ViewPager mViewPager;

    private int mTabBackgroundRes = R.drawable.selector_tab_item_default_background;

    private int mTextAppearance = R.style.tab_item_text_appearance;

    private int mSelectedItemIndex = 0;

    private int[] mTabMargins = {4, 4, 4, 4};

    private int[] mPaddings = {4,4,4,4};

    private int mHeightDp = 40;

    private LinearLayout mTabLayout;

    private final float mDensity;

    private final int mScreenWidth;

    private OnClickListener mTabOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final int item = ((TabView)v).getIndex();
            setCurrentItem(item);
        }
    };


    public HorizontalScrollNavigator(Context context) {
        this(context, null);
    }

    public HorizontalScrollNavigator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        DisplayMetrics dm = DisplayUtil.getDisplayMetrics((Activity) context);
        mDensity = dm.density;
        mScreenWidth = dm.widthPixels;

        mTabLayout = new LinearLayout(getContext());
        mTabLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, getPixelsNum(mHeightDp)));
        addView(mTabLayout);
    }

    /** 将dp数值转化成像素值 */
    private int getPixelsNum(int dpNum){
        return (int) (mDensity * dpNum);
    }


    /** 设置ViewPager */
    public void setViewPager(ViewPager viewPager){
        if(mViewPager == viewPager){
            return;
        }

        if(mViewPager != null){
            mViewPager.setOnPageChangeListener(null);
        }

        final PagerAdapter adapter = viewPager.getAdapter();
        if(adapter == null){
           throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        mViewPager = viewPager;
        notifyDataSetChanged();
        mViewPager.setOnPageChangeListener(this);
    }

    /** 更新数据 */
    public void notifyDataSetChanged(){
        mTabLayout.removeAllViews();

        final PagerAdapter adapter = mViewPager.getAdapter();
        final int count = adapter.getCount();

        for (int i=0; i<count; i++){
            TabView tb = new TabView(getContext());
            tb.setTextAppearance(getContext(), mTextAppearance);
            tb.setText(adapter.getPageTitle(i));
            tb.setBackgroundResource(mTabBackgroundRes);
            tb.setFocusable(true);
            tb.setGravity(Gravity.CENTER);
            tb.setPadding(getPixelsNum(mPaddings[0]),getPixelsNum(mPaddings[1]),getPixelsNum(mPaddings[2]),getPixelsNum(mPaddings[3]));
            tb.setSingleLine();
            tb.setIndex(i);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            layoutParams.leftMargin = getPixelsNum(mTabMargins[0]);
            layoutParams.topMargin = getPixelsNum(mTabMargins[1]);
            layoutParams.rightMargin = getPixelsNum(mTabMargins[2]);
            layoutParams.bottomMargin = getPixelsNum(mTabMargins[3]);


            tb.setLayoutParams(layoutParams);

            tb.setOnClickListener(mTabOnClickListener);

            mTabLayout.addView(tb, i);
        }

        if(mSelectedItemIndex >= count){
            mSelectedItemIndex = count - 1;
        }

        setCurrentItem(mSelectedItemIndex);
    }

    private void setTabPaddings(int[] paddings){
        mPaddings = paddings;
    }

    /**  取消旧的当前项，设置新的当前项 */
    private void setCurrentItem(int item){
        final TextView currentView = (TextView) mTabLayout.getChildAt(item);
        int viewWidth = currentView.getMeasuredWidth();
        int marginWidth = (int) (mDensity * (mTabMargins[0] + mTabMargins[1]));
        int viewLeft = currentView.getLeft();


        int scrollDistance = viewWidth/2 + marginWidth/2 + viewLeft - mScreenWidth/8;
        smoothScrollTo(scrollDistance, 0);

        mTabLayout.getChildAt(mSelectedItemIndex).setSelected(false);
        currentView.setSelected(true);
        mViewPager.setCurrentItem(item);

        mSelectedItemIndex = item;
    }



    private class TabView extends TextView{

        private int mIndex;

        public int getIndex() {
            return mIndex;
        }

        public void setIndex(int index) {
            this.mIndex = index;
        }

        public TabView(Context context) {
            super(context);
        }

        public TabView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
    }

    /** 设置标签背景 */
    public void setTabBackgroundResource(int tabBackgroundRes){
        mTabBackgroundRes = tabBackgroundRes;
    }

    /** 设置标签文字外观
     * 包括颜色，gravity,文字样式,大小等，
     * 详细设置请看{@link android.widget.TextView#setTextAppearance(android.content.Context, int)} */
    public void setTabTextAppearance(int textAppearance){
        mTextAppearance = textAppearance;
    }

    /** 设置标签内边距 */
    public void setTabMarings(int[] margins){
        mTabMargins = margins;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
