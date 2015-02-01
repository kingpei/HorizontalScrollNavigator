package com.kingpei.hsn.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/1/30.
 */
public class SampleFragment extends Fragment {

    public void setContent(String content) {
        this.mContent = content;
        if(mTextView != null){
            mTextView.setText(mContent);
            Log.v("SampleFragment", "set Text");
        }
    }

    private String mContent;

    private TextView mTextView;

//    public String getContent() {
//        return mContent;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        mContent = args.getString("content");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTextView = new TextView(getActivity());
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setText(mContent);
        return mTextView;
    }
}
