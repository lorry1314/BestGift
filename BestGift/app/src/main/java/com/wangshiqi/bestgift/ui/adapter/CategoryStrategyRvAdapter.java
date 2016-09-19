package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.CategoryStrategyBean;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 */
public class CategoryStrategyRvAdapter extends RecyclerView.Adapter<CategoryStrategyRvAdapter.CategoryStrategyViewHolder> {

    private Context context;
    private List<CategoryStrategyBean.DataBean.ColumnsBean> datas;

    @Override
    public CategoryStrategyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CategoryStrategyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CategoryStrategyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView timeTv, descriptionTv;
        public CategoryStrategyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_strategy_iv);
            timeTv = (TextView) itemView.findViewById(R.id.item_strategy_time_tv);
            descriptionTv = (TextView) itemView.findViewById(R.id.item_strategy_description_tv);
        }
    }
}
