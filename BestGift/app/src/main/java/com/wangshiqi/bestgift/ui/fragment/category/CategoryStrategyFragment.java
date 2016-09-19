package com.wangshiqi.bestgift.ui.fragment.category;

import android.support.v7.widget.RecyclerView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * Created by dllo on 16/9/10.
 * 攻略
 */
public class CategoryStrategyFragment extends AbsFragment {
    private RecyclerView recyclerView;
    @Override
    protected int setLayout() {
        return R.layout.fragment_category_strategy;
    }

    @Override
    protected void initView() {
        recyclerView = byView(R.id.strategy_rv);

    }

    @Override
    protected void initDatas() {

    }
}
