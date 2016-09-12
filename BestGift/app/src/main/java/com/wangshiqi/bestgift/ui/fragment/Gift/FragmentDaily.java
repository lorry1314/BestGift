package com.wangshiqi.bestgift.ui.fragment.Gift;

import android.os.Bundle;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class FragmentDaily extends AbsFragment {
    public static FragmentDaily newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentDaily fragment = new FragmentDaily();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
