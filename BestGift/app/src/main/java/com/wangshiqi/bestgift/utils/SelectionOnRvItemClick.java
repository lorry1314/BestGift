package com.wangshiqi.bestgift.utils;

import com.wangshiqi.bestgift.model.bean.SelectionRvBean;

/**
 * Created by dllo on 16/9/26.
 */
public interface SelectionOnRvItemClick {
    void onRvItemClickListener(int positon, SelectionRvBean.DataBean.SecondaryBannersBean data);
}
