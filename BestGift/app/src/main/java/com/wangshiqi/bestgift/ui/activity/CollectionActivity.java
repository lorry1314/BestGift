package com.wangshiqi.bestgift.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.LiteOrmBean;
import com.wangshiqi.bestgift.model.db.LiteOrmInstance;
import com.wangshiqi.bestgift.ui.adapter.CollectionRvAdapter;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 收藏详情
 */
public class CollectionActivity extends AbsBaseActivity {
    private RecyclerView collectionRv;
    private CollectionRvAdapter dailyRvAdapter;
    private ImageView collectionBackIv;

    @Override
    protected int setLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initViews() {
        collectionRv = byView(R.id.collection_rv);
        collectionBackIv = byView(R.id.collection_back_iv);
    }

    @Override
    protected void initDatas() {
        dailyRvAdapter = new CollectionRvAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        collectionRv.setLayoutManager(manager);
        List<LiteOrmBean> bean = LiteOrmInstance.getLiteOrmInstance().getQueryAll(LiteOrmBean.class);
        dailyRvAdapter.setDatas(bean);
        collectionRv.setAdapter(dailyRvAdapter);
        collectionBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
