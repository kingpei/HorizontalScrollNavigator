package com.kingpei.hsn.lib;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2015/1/31.
 */
public class DisplayUtil {
    public static DisplayMetrics getDisplayMetrics(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm;
    };
}
