package com.wangshiqi.bestgift.ui.fragment.homepage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.net.NetUrl;
import com.wangshiqi.bestgift.ui.adapter.HomepageAdapter;
import com.wangshiqi.bestgift.ui.adapter.HomepagePopAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;
import com.wangshiqi.bestgift.utils.ScreanSizeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * - Created by dllo on 16/9/8.
 * 首页
 */
public class HomepageFragment extends AbsFragment implements View.OnClickListener {
    private ViewPager homepageVp;
    private TabLayout homepageTb;
    private HomepageAdapter homepageAdapter;
    private ImageView downIv, popIv;
    private LinearLayout homepageRootView;
    private RecyclerView popRv;
    private HomepagePopAdapter homepagePopAdapter;

    public static HomepageFragment newInstance() {
        Bundle args = new Bundle();
        HomepageFragment fragment = new HomepageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        homepageVp = byView(R.id.homepage_vp);
        homepageTb = byView(R.id.homepage_tb);
        downIv = byView(R.id.homepage_downIv);
        homepageRootView = byView(R.id.homepage_rootview);
    }

    @Override
    protected void initDatas() {
        addFragments();
        homepageVp.setAdapter(homepageAdapter);
        homepageTb.setupWithViewPager(homepageVp);
        homepageTbSet(); // 设置TabLayout标签数据
        downIv.setOnClickListener(this);
    }

    private void addFragments() {
        List<Fragment> datas = new ArrayList<>();
        datas.add(SelectionFragment.newInstance());
        datas.add(GiftForGirlFragment.newInstance(NetUrl.GIVEGILRFRIEND));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.BOARD_SHOPPING));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.IDEA_FOR_LIFE));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.GIVE_BOYFRIEND));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.GIVE_PARENT));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.GIVE_COLLEGUE));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.GIVE_BABY));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.DESIGN_FEELS));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.ART_WIND));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.THE_EXIOC));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.FAN_SENCISE));
        datas.add(GiftForGirlFragment.newInstance(NetUrl.KAWAYI));
        homepageAdapter = new HomepageAdapter(getChildFragmentManager(), datas);
    }

    private void homepageTbSet() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage_downIv:
                showWindow(); // popwindow弹出方法
                break;
        }
    }

    private void showWindow() {
        final PopupWindow pw = new PopupWindow(context);
        pw.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pw.setHeight(ScreanSizeUtil.getScreenHeight(context) * 4 / 10);
        View v = LayoutInflater.from(context).inflate(R.layout.homepage_popup, null);
        popRv = (RecyclerView) v.findViewById(R.id.pop_rv);
        pw.setFocusable(true);
        pw.setContentView(v);
        pw.showAsDropDown(homepageRootView);
        homepagePopAdapter = new HomepagePopAdapter(context);
        List<String> datas = new ArrayList<>();
        datas.add("精选");
        datas.add("送女票");
        datas.add("海淘");
        datas.add("创意生活");
        datas.add("送基友");
        datas.add("送爸妈");
        datas.add("送同事");
        datas.add("送宝贝");
        datas.add("设计感");
        datas.add("文艺风");
        datas.add("奇葩搞怪");
        datas.add("科技范");
        datas.add("萌萌哒");
        homepagePopAdapter.setDatas(datas);

        popRv.setAdapter(homepagePopAdapter);
        GridLayoutManager sgm = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        popRv.setLayoutManager(sgm);

        popIv = (ImageView) v.findViewById(R.id.pop_iv);
        popIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });

    }
}
