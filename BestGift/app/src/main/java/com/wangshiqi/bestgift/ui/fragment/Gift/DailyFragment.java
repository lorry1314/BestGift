package com.wangshiqi.bestgift.ui.fragment.gift;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.DailyRvBean;
import com.wangshiqi.bestgift.model.net.VolleyInstance;
import com.wangshiqi.bestgift.model.net.VolleyResult;
import com.wangshiqi.bestgift.ui.adapter.DailyRvAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 每日推荐(复用)
 */
public class DailyFragment extends AbsFragment implements VolleyResult {

    private ImageView dailyIv;
    private RecyclerView dailyRv;
    private DailyRvAdapter dailyRvAdapter;

    public static DailyFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);
        DailyFragment fragment = new DailyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {
        dailyRv = byView(R.id.daily_rv);
        dailyIv = byView(R.id.daily_header_iv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("url");
        dailyRvAdapter = new DailyRvAdapter(context);
        dailyRv.setAdapter(dailyRvAdapter);
        GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        dailyRv.setLayoutManager(manager);
        VolleyInstance.getInstance().startRequest(string, this);
    }

    @Override
    public void success(String resultStr) {
        Gson gson = new Gson();
        DailyRvBean bean = gson.fromJson(resultStr,DailyRvBean.class);
        List<DailyRvBean.DataBean.ItemsBean> datas =  bean.getData().getItems();
        dailyRvAdapter.setDatas(datas);
        Picasso.with(context).load(bean.getData().getCover_image()).into(dailyIv);
    }

    @Override
    public void failure() {

    }
}
