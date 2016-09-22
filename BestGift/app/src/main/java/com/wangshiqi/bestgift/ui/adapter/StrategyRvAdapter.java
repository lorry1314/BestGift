package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.StrategyBean;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 * 分类-攻略-下面三个RecyclerView适配器
 */
public class StrategyRvAdapter extends RecyclerView.Adapter<StrategyRvAdapter.StrategyViewHolder> {
    private Context context;
    private List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> datas;

    public StrategyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public StrategyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_strategy_rv, parent, false);
        StrategyViewHolder strategyViewHolder = new StrategyViewHolder(view);
        return strategyViewHolder;
    }

    @Override
    public void onBindViewHolder(StrategyViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getCover_image_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class StrategyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public StrategyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_category_strategy_iv);
        }
    }
}
