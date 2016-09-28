package com.wangshiqi.bestgift.ui.fragment.gift;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.net.NetUrl;
import com.wangshiqi.bestgift.ui.adapter.GiftAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * - Created by dllo on 16/9/8.
 * 榜单页面
 */
public class GiftFragment extends AbsFragment {
    private TabLayout giftTabLayout;
    private ViewPager giftViewPager;
    private GiftAdapter giftAdapter;
    private String[] titles;

    public static GiftFragment newInstance() {

        Bundle args = new Bundle();

        GiftFragment fragment = new GiftFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        datas.add(DailyFragment.newInstance(NetUrl.URLDAILY));
        datas.add(TOPFragment.newInstance(NetUrl.TOP100));
        datas.add(TOPFragment.newInstance(NetUrl.ORIGINAL));
        datas.add(TOPFragment.newInstance(NetUrl.NEWSTAR));

        giftAdapter = new GiftAdapter(getChildFragmentManager(), datas);
        giftViewPager.setAdapter(giftAdapter);
        giftTabLayout.setupWithViewPager(giftViewPager);
        titles = getResources().getStringArray(R.array.gift_tab);
        for (int i = 0; i < 4; i++) {
            giftTabLayout.getTabAt(i).setText(titles[i]);
        }
    }
}
