package com.wangshiqi.bestgift.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.adapter.HomepageAdapter;

import java.util.ArrayList;
import java.util.List;

/**

 - Created by dllo on 16/9/8.
 */
public class FragmentHomepage extends AbsFragment {
    private ViewPager homepageVp;
    private TabLayout homepageTb;
    private HomepageAdapter homepageAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_homepage;
    }
    @Override
    protected void initView() {
       homepageVp = byView(R.id.homepage_vp);
        homepageTb = byView(R.id.homepage_tb);
    }
    @Override
    protected void initDatas() {
        List<Fragment> datas = new ArrayList<>();
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        datas.add(new FragmentCollection());
        homepageAdapter = new HomepageAdapter(getChildFragmentManager(), datas);
        homepageVp.setAdapter(homepageAdapter);
        homepageTb.setupWithViewPager(homepageVp);

        homepageTb.getTabAt(0).setText("精选");
        homepageTb.getTabAt(1).setText("送女票");
        homepageTb.getTabAt(2).setText("海淘");
        homepageTb.getTabAt(3).setText("创意生活");
        homepageTb.getTabAt(4).setText("送基友");
        homepageTb.getTabAt(5).setText("送爸妈");
        homepageTb.getTabAt(6).setText("送同事");
        homepageTb.getTabAt(7).setText("送宝贝");
        homepageTb.getTabAt(8).setText("设计感");
        homepageTb.getTabAt(9).setText("文艺风");
        homepageTb.getTabAt(10).setText("奇葩搞怪");
        homepageTb.getTabAt(11).setText("科技范");
        homepageTb.getTabAt(12).setText("萌萌哒");
        homepageTb.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
