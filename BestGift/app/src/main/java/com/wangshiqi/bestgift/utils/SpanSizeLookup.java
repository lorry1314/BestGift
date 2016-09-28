package com.wangshiqi.bestgift.utils;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by dllo on 16/9/20.
 * RecyclerView加头尾工具类
 */
public class SpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private final GridLayoutManager layoutManager;

    public SpanSizeLookup(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }
    @Override
    public int getSpanSize(int position) {
        position = position == layoutManager.getItemCount() ? layoutManager.getSpanCount() : 1;
        return position;
    }
}
