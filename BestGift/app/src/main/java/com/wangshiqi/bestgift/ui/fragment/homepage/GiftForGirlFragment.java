package com.wangshiqi.bestgift.ui.fragment.homepage;

import android.os.Bundle;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * Created by dllo on 16/9/9.
 * 送女票(后面开始复用)
 */
public class GiftForGirlFragment extends AbsFragment {
    public static GiftForGirlFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GiftForGirlFragment fragment = new GiftForGirlFragment();
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
