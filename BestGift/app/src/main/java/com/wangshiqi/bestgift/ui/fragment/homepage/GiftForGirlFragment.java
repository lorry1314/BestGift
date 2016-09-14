package com.wangshiqi.bestgift.ui.fragment.homepage;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.GiftForGilrBean;
import com.wangshiqi.bestgift.model.net.VolleyInstance;
import com.wangshiqi.bestgift.model.net.VolleyResult;
import com.wangshiqi.bestgift.ui.adapter.GiftForGirlAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 送女票(后面开始复用)
 */
public class GiftForGirlFragment extends AbsFragment implements VolleyResult {

    private ListView girlLv;
    private GiftForGirlAdapter giftForGirlAdapter;



    public static GiftForGirlFragment newInstance(String url) {
        
        Bundle args = new Bundle();
        args.putString("url", url);
        GiftForGirlFragment fragment = new GiftForGirlFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_forgirl;
    }

    @Override
    protected void initView() {
        girlLv = byView(R.id.girl_lv);
    }

    @Override
    protected void initDatas() {

        Bundle bundle = getArguments();
        String string = bundle.getString("url");

        giftForGirlAdapter = new GiftForGirlAdapter(context);
        VolleyInstance.getInstance().startRequest(string, this);
        girlLv.setAdapter(giftForGirlAdapter);
    }

    @Override
    public void success(String resultStr) {
        Gson gson = new Gson();
        GiftForGilrBean bean = gson.fromJson(resultStr, GiftForGilrBean.class);
        List<GiftForGilrBean.DataBean.ItemsBean> datas = bean.getData().getItems();
        giftForGirlAdapter.setDatas(datas);
    }

    @Override
    public void failure() {

    }
}
