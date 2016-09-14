package com.wangshiqi.bestgift.ui.fragment.gift;

import android.os.Bundle;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * Created by dllo on 16/9/9.
 * 每日推荐(复用)
 */
public class DailyFragment extends AbsFragment {
    public static DailyFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DailyFragment fragment = new DailyFragment();
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
