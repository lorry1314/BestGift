package com.wangshiqi.bestgift.utils;

import com.wangshiqi.bestgift.model.bean.DailyRvBean;

/**
 * Created by dllo on 16/9/26.
 */
public interface GiftOnRvItemClick {
    void onRvItemClickListener(int positon, DailyRvBean.DataBean.ItemsBean data);
}
