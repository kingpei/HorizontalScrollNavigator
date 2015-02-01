package com.kingpei.hsn.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2015/1/31.
 */
public class HorizotalScrollPlusNavigator extends LinearLayout{

    private final ImageButton mPlusButton;

    private Class mPlusActivityClass;

    private static String PLUS_ACTIVITY_EXTRA = "plus_activity_extra";

    /** 通过这个RequestCode，当添加标题成功后，导航栏所在Activity可以获得消息和数据，并处理 */
    private static int REQUEST_TO_PLUS_ACTIVITY = 999;

    public HorizontalScrollNavigator getHorizontalScrollNavigator() {
        return mHorizontalScrollNavigator;
    }

    private final HorizontalScrollNavigator mHorizontalScrollNavigator;

    public HorizotalScrollPlusNavigator(Context context) {
        this(context, null);
    }

    public HorizotalScrollPlusNavigator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHorizontalScrollNavigator = new HorizontalScrollNavigator(getContext());
        LayoutParams lp =  new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        mHorizontalScrollNavigator.setLayoutParams(lp);
        addView(mHorizontalScrollNavigator, 0);

        mPlusButton = new ImageButton(getContext());
        mPlusButton.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mPlusButton.setBackgroundResource(R.drawable.selector_default_plus);
        addView(mPlusButton, 1);


    }


    /** 设置管理标签图标 */
    public void setPlusButtonIcon(int res){
        mPlusButton.setBackgroundResource(res);
    }

    /** 设置管理标签的按钮点击打开的Activity */
    public void setPlusActivityClass(Class plusActivityClass, final Bundle bundle){
        mPlusActivityClass = plusActivityClass;

        mPlusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), mPlusActivityClass);
                it.putExtra(PLUS_ACTIVITY_EXTRA, bundle);
                ((Activity)v.getContext()).startActivityForResult(it, REQUEST_TO_PLUS_ACTIVITY);
            }
        });
    }

    /** 设置管理标签的按钮点击打开的Activity */
    public void setPlusActivityClass(Class plusActivityClass){
        setPlusActivityClass(plusActivityClass, null);
    }
}
