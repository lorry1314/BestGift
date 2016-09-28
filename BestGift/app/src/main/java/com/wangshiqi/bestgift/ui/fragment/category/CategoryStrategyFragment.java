package com.wangshiqi.bestgift.ui.fragment.category;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.ColomnBean;
import com.wangshiqi.bestgift.model.bean.StrategyBean;
import com.wangshiqi.bestgift.model.net.NetUrl;
import com.wangshiqi.bestgift.model.net.VolleyInstance;
import com.wangshiqi.bestgift.model.net.IVolleyResult;
import com.wangshiqi.bestgift.ui.adapter.ColomnRvAdapter;
import com.wangshiqi.bestgift.ui.adapter.StrategyRvAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;
import com.wangshiqi.bestgift.utils.SpanSizeLookup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 攻略
 */
public class CategoryStrategyFragment extends AbsFragment {
    private RecyclerView colomnRecyclerView, categoryRv, styleRv, targetRv;
    private ColomnRvAdapter colomnRvAdapter;
    private StrategyRvAdapter categoryAdapter, styleAdapter, targetAdapter;
    private TextView strategyCategoryTv, strategyStyleTv, strategyTargetTv;

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
        colomnRecyclerView = byView(R.id.colomn_rv);
        categoryRv = byView(R.id.category_rv);
        styleRv = byView(R.id.style_rv);
        targetRv = byView(R.id.target_rv);
        strategyCategoryTv = byView(R.id.strategy_category_tv);
        strategyStyleTv = byView(R.id.strategy_style_tv);
        strategyTargetTv = byView(R.id.strategy_target_tv);
    }

    @Override
    protected void initDatas() {
        colomnData(); // 攻略上面横向Rv
        strategyData(); // 下面三个网格Rv
    }

    private void strategyData() {
        categoryAdapter = new StrategyRvAdapter(context);
        categoryRv.setAdapter(categoryAdapter);
        styleAdapter = new StrategyRvAdapter(context);
        styleRv.setAdapter(styleAdapter);
        targetAdapter = new StrategyRvAdapter(context);
        targetRv.setAdapter(targetAdapter);
        GridLayoutManager glm1 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        GridLayoutManager glm2 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        GridLayoutManager glm3 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        categoryRv.setLayoutManager(glm1);
        styleRv.setLayoutManager(glm2);
        targetRv.setLayoutManager(glm3);
        VolleyInstance.getInstance().startRequest(NetUrl.STRATEGY, new IVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                StrategyBean bean = gson.fromJson(resultStr, StrategyBean.class);
                List<StrategyBean.DataBean.ChannelGroupsBean> datas = bean.getData().getChannel_groups();
                if (datas.get(0).getName().equals("品类")) {
                    List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> categoryBean = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        categoryBean.add(bean.getData().getChannel_groups().get(0).getChannels().get(i));
                    }
                    categoryAdapter.setDatas(categoryBean);
                    strategyCategoryTv.setText(datas.get(0).getName());
                }
                if (datas.get(1).getName().equals("风格")) {
                    List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> styleBean = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        styleBean.add(bean.getData().getChannel_groups().get(1).getChannels().get(i));
                    }
                    styleAdapter.setDatas(styleBean);
                    strategyStyleTv.setText(datas.get(1).getName());
                }

                if (datas.get(2).getName().equals("对象")) {
                    List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> targetBean = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        targetBean.add(bean.getData().getChannel_groups().get(2).getChannels().get(i));
                    }
                    targetAdapter.setDatas(targetBean);
                    strategyTargetTv.setText(datas.get(2).getName());
                }

            }

            @Override
            public void failure() {

            }
        });
    }

    private void colomnData() {
        colomnRvAdapter = new ColomnRvAdapter(context);
        colomnRecyclerView.setAdapter(colomnRvAdapter);
        GridLayoutManager manager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);
        manager.setSpanSizeLookup(new SpanSizeLookup(manager));
        colomnRecyclerView.setLayoutManager(manager);
        VolleyInstance.getInstance().startRequest(NetUrl.COLUMN, new IVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ColomnBean bean = gson.fromJson(resultStr, ColomnBean.class);
                List<ColomnBean.DataBean.ColumnsBean> datas = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    datas.add(bean.getData().getColumns().get(i));
                }
                colomnRvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }
}
