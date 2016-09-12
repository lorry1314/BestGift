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
public class FragmentGift extends AbsFragment {
    private TabLayout giftTabLayout;
    private ViewPager giftViewPager;
    private GiftAdapter giftAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_gift;
    }
    @Override
    protected void initView() {
        giftTabLayout = byView(R.id.gift_tb);
        giftViewPager = byView(R.id.gift_vp);
    }
    @Override
    protected void initDatas() {
        List<Fragment> datas = new ArrayList<>();
        datas.add(FragmentDaily.newInstance());
        datas.add(FragmentDaily.newInstance());
        datas.add(FragmentDaily.newInstance());
        datas.add(FragmentDaily.newInstance());

        giftAdapter = new GiftAdapter(getChildFragmentManager(), datas);
        giftViewPager.setAdapter(giftAdapter);
        giftTabLayout.setupWithViewPager(giftViewPager);
        giftTabLayout.getTabAt(0).setText("每日推荐");
        giftTabLayout.getTabAt(1).setText("TOP100");
        giftTabLayout.getTabAt(2).setText("独立原创");
        giftTabLayout.getTabAt(3).setText("新星榜");
    }
}
