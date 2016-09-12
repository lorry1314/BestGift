package com.wangshiqi.bestgift.ui.fragment.homepage;

import android.os.Bundle;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class FragmentGiftForGirl extends AbsFragment {
    public static FragmentGiftForGirl newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentGiftForGirl fragment = new FragmentGiftForGirl();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_forgirl;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
