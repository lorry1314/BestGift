package com.wangshiqi.bestgift.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.wangshiqi.bestgift.R;

/**
 * Created by dllo on 16/9/27.
 * 搜索详情
 */
public class SearchActivity extends AbsBaseActivity {

    private TextView cancel;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        cancel = byView(R.id.cancel_tv);
    }

    @Override
    protected void initDatas() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
