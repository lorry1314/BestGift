package com.wangshiqi.bestgift.ui.fragment.category;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.adapter.CategoryAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.ArrayList;
import java.util.List;

/**

 - Created by dllo on 16/9/8.
 */
public class FragmentCategory extends AbsFragment {
    private ViewPager categoryVp;
    private TabLayout categoryTb;
    private CategoryAdapter categoryAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_category;
    }
    @Override
    protected void initView() {
        categoryTb = byView(R.id.category_tb);
        categoryVp = byView(R.id.category_vp);
    }
    @Override
    protected void initDatas() {
        List<Fragment> datas = new ArrayList<>();
        datas.add(new FragmentCategoryStrategy());
        datas.add(new FragmentCategorySingle());
        categoryAdapter = new CategoryAdapter(getChildFragmentManager(), datas);
        categoryVp.setAdapter(categoryAdapter);
        categoryTb.setupWithViewPager(categoryVp);

        categoryTb.getTabAt(0).setText("攻略");
        categoryTb.getTabAt(1).setText("单品");
    }
}
