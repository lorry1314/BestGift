package com.wangshiqi.bestgift.ui.fragment.category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.activity.SearchActivity;
import com.wangshiqi.bestgift.ui.adapter.CategoryAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.ArrayList;
import java.util.List;

/**

 - Created by dllo on 16/9/8.
 * 分类界面
 */
public class CategoryFragment extends AbsFragment {
    private ViewPager categoryVp;
    private TabLayout categoryTb;
    private CategoryAdapter categoryAdapter;
    private RelativeLayout searchRl;

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_category;
    }
    @Override
    protected void initView() {
        categoryTb = byView(R.id.category_tb);
        categoryVp = byView(R.id.category_vp);
        searchRl = byView(R.id.search_rl);
    }
    @Override
    protected void initDatas() {
        List<Fragment> datas = new ArrayList<>();
        datas.add(new CategoryStrategyFragment());
        datas.add(new CategorySingleFragment());
        categoryAdapter = new CategoryAdapter(getChildFragmentManager(), datas);
        categoryVp.setAdapter(categoryAdapter);
        categoryTb.setupWithViewPager(categoryVp);
        String [] titles = getResources().getStringArray(R.array.category_tab);
        for (int i = 0; i < 2; i++) {
            categoryTb.getTabAt(i).setText(titles[i]);
        }
        searchRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(SearchActivity.class);
            }
        });
    }
}
