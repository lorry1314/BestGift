package com.wangshiqi.bestgift.ui.fragment.gift;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.DailyRvBean;
import com.wangshiqi.bestgift.model.net.IVolleyResult;
import com.wangshiqi.bestgift.model.net.VolleyInstance;
import com.wangshiqi.bestgift.ui.adapter.Top100RvAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * TOP100(复用)
 */
public class TOPFragment extends AbsFragment implements IVolleyResult {

    private ImageView topIv;
    private RecyclerView topRv;
    private Top100RvAdapter topRvAdapter;

    public static TOPFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);
        TOPFragment fragment = new TOPFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_top;
    }

    @Override
    protected void initView() {
        topRv = byView(R.id.top_rv);
        topIv = byView(R.id.top_header_iv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("url");
        topRvAdapter = new Top100RvAdapter(context);
        topRv.setAdapter(topRvAdapter);
        GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        topRv.setLayoutManager(manager);
        VolleyInstance.getInstance().startRequest(string, this);
    }

    @Override
    public void success(String resultStr) {
        Gson gson = new Gson();
        DailyRvBean bean = gson.fromJson(resultStr,DailyRvBean.class);
        List<DailyRvBean.DataBean.ItemsBean> datas =  bean.getData().getItems();
        topRvAdapter.setDatas(datas);
        Picasso.with(context).load(bean.getData().getCover_image()).into(topIv);
    }

    @Override
    public void failure() {

    }
}
