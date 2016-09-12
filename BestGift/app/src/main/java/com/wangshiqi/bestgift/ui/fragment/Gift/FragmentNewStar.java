package com.wangshiqi.bestgift.ui.fragment.Gift;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.adapter.GiftAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.ArrayList;
import java.util.List;

/**

 - Created by dllo on 16/9/8.
 */
public class FragmentNewStar extends AbsFragment {
    private TabLayout giftTabLayout;
    private ViewPager giftViewPager;
    private GiftAdapter giftAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_newstar;
    }
    @Override
    protected void initView() {
        giftTabLayout = byView(R.id.gift_tb);
        giftViewPager = byView(R.id.gift_vp);
    }
    @Override
    protected void initDatas() {
        List<Fragment> datas = new ArrayList<>();
    }
}
