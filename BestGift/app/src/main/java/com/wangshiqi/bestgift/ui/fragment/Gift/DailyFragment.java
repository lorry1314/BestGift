package com.wangshiqi.bestgift.ui.fragment.gift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.DailyRvBean;
import com.wangshiqi.bestgift.model.net.IVolleyResult;
import com.wangshiqi.bestgift.model.net.VolleyInstance;
import com.wangshiqi.bestgift.ui.activity.InfoActivity;
import com.wangshiqi.bestgift.ui.adapter.DailyRvAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;
import com.wangshiqi.bestgift.utils.GiftOnRvItemClick;
import com.wangshiqi.bestgift.view.GiftRecycleView;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 每日推荐(复用)
 */
public class DailyFragment extends AbsFragment implements IVolleyResult {

    private GiftRecycleView dailyRv;
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
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("url");
        dailyRvAdapter = new DailyRvAdapter(context);
        GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        dailyRv.setLayoutManager(manager);
        dailyRv.setAdapter(dailyRvAdapter);
        VolleyInstance.getInstance().startRequest(string, this);
        dailyRvAdapter.setGiftOnRvItemClick(new GiftOnRvItemClick() {
            @Override
            public void onRvItemClickListener(int positon, DailyRvBean.DataBean.ItemsBean data) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("position", positon);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(String resultStr) {
        Gson gson = new Gson();
        DailyRvBean bean = gson.fromJson(resultStr,DailyRvBean.class);
        String imgUrl = bean.getData().getCover_image();
        List<DailyRvBean.DataBean.ItemsBean> datas =  bean.getData().getItems();
        dailyRvAdapter.setDatas(datas, imgUrl);
    }

    @Override
    public void failure() {

    }

}
