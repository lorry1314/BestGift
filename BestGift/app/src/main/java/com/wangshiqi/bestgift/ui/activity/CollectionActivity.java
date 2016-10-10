package com.wangshiqi.bestgift.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.LiteOrmBean;
import com.wangshiqi.bestgift.model.db.LiteOrmInstance;
import com.wangshiqi.bestgift.ui.adapter.CollectionRvAdapter;
import com.wangshiqi.bestgift.utils.GiftOnRvItemClick;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 收藏详情
 */
public class CollectionActivity extends AbsBaseActivity {
    private RecyclerView collectionRv;
    private CollectionRvAdapter collectionRvAdapter;
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
        collectionBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        collectionRvAdapter = new CollectionRvAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        collectionRv.setLayoutManager(manager);
        final List<LiteOrmBean> bean = LiteOrmInstance.getLiteOrmInstance().getQueryAll(LiteOrmBean.class);
        collectionRvAdapter.setDatas(bean);
        collectionRv.setAdapter(collectionRvAdapter);
        collectionRvAdapter.setGiftOnRvItemClick(new GiftOnRvItemClick() {
            @Override
            public void onRvItemClickListener(int positon, Object o) {
                Intent intent = new Intent(CollectionActivity.this, GiftDetailActivity.class);
                intent.putExtra("url", bean.get(positon).getWebUrl());
                intent.putExtra("name", bean.get(positon).getName());
                intent.putExtra("taobaoUrl", bean.get(positon).getTaobaoUrl());
                startActivity(intent);
            }
        });
    }
}
