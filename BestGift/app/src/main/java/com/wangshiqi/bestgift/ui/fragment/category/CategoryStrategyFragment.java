package com.wangshiqi.bestgift.ui.fragment.category;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.CategoryStrategyBean;
import com.wangshiqi.bestgift.model.net.NetUrl;
import com.wangshiqi.bestgift.model.net.VolleyInstance;
import com.wangshiqi.bestgift.model.net.VolleyResult;
import com.wangshiqi.bestgift.ui.adapter.CategoryStrategyRvAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 攻略
 */
public class CategoryStrategyFragment extends AbsFragment {
    private RecyclerView strategyRecyclerView;
    private CategoryStrategyRvAdapter strategyRvAdapter;
    public static CategoryStrategyFragment newInstance() {

        Bundle args = new Bundle();

        CategoryStrategyFragment fragment = new CategoryStrategyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_category_strategy;
    }

    @Override
    protected void initView() {
        strategyRecyclerView = byView(R.id.strategy_rv);
    }

    @Override
    protected void initDatas() {
        strategyRvAdapter = new CategoryStrategyRvAdapter(context);
        strategyRecyclerView.setAdapter(strategyRvAdapter);
        GridLayoutManager manager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);
        strategyRecyclerView.setLayoutManager(manager);
        VolleyInstance.getInstance().startRequest(NetUrl.COLUMN, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                CategoryStrategyBean bean = gson.fromJson(resultStr, CategoryStrategyBean.class);
                List<CategoryStrategyBean.DataBean.ColumnsBean> datas = bean.getData().getColumns();
                strategyRvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }
}
