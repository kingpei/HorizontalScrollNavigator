package com.kingpei.hsn.lib;

import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/2/1.
 */
public class TabDeletionManager {
    private HorizontalScrollNavigator horizontalScrollNavigator;
    private Activity activity;

    /** 存放标签被选中的数据 */
    private SparseBooleanArray mSeleteds = new SparseBooleanArray();

    /** 标签被选中和取消的监听 */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setSelected(!v.isSelected());
            mSeleteds.put(((LinearLayout)v.getParent()).indexOfChild(v), v.isSelected());
        }
    };

    public TabDeletionManager(HorizontalScrollNavigator horizontalScrollNavigator) {
        this.horizontalScrollNavigator = horizontalScrollNavigator;
        activity = (Activity) horizontalScrollNavigator.getContext();
    }

    public void init(){
            final LinearLayout tabLayout = horizontalScrollNavigator.getTabLayout();

            horizontalScrollNavigator.setOnTabLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //防止重复调用
                    if (!horizontalScrollNavigator.isTabDeleting()) {
                        mSeleteds.clear();

                        horizontalScrollNavigator.setIsTabDeleting(true);

                        for (int j = 0; j < tabLayout.getChildCount(); j++) {
                            TextView tv = (TextView) tabLayout.getChildAt(j);
                            if (tv.isSelected()) {
                                tv.setSelected(false);
                            }

                            tv.setOnClickListener(onClickListener);
                        }

                        v.setSelected(true);
                        mSeleteds.put(tabLayout.indexOfChild(v), true);

                        activity.startActionMode(callback);


                        return true;
                    }

                    return  false;

                }
            });

        horizontalScrollNavigator.notifyDataSetChanged();

        }

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_deletion, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();

            if(id == R.id.menu_deletion_cancel){
                //取消时清除
                mSeleteds.clear();
                mode.finish();
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //表示当确认时就删除相关选项
            horizontalScrollNavigator.removeTabs(mSeleteds);
            horizontalScrollNavigator.setIsTabDeleting(false);
            horizontalScrollNavigator.notifyDataSetChanged();
        }
    };

    /** 由viewpager的adapter实现 */
    public interface OnTabRemovedListener{
        public void removeTabs(SparseBooleanArray booleanArray);
        public int getCurrentIndex(SparseBooleanArray booleanArray, int index);
    }


}
