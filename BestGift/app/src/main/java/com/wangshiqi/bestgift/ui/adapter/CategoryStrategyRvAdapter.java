package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.CategoryStrategyBean;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 */
public class CategoryStrategyRvAdapter extends RecyclerView.Adapter<CategoryStrategyRvAdapter.CategoryStrategyViewHolder> {

    private Context context;
    private List<CategoryStrategyBean.DataBean.ColumnsBean> datas;

    public CategoryStrategyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CategoryStrategyBean.DataBean.ColumnsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public CategoryStrategyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_strategy_rv, parent, false);
        CategoryStrategyViewHolder viewHolder = new CategoryStrategyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryStrategyViewHolder holder, int position) {
        CategoryStrategyBean.DataBean.ColumnsBean bean = datas.get(position);
        Picasso.with(context).load(bean.getBanner_image_url()).into(holder.imageView);
        holder.titileTv.setText(bean.getTitle());
        if (bean.getSubtitle() != null) {
            holder.subTitleTv.setText(bean.getSubtitle());
        }else {
            holder.subTitleTv.setText("");
        }
        holder.authorTv.setText(bean.getAuthor());
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class CategoryStrategyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titileTv, authorTv, subTitleTv;
        public CategoryStrategyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_strategy_iv);
            titileTv = (TextView) itemView.findViewById(R.id.item_strategy_title_tv);
            subTitleTv = (TextView) itemView.findViewById(R.id.item_strategy_subTitle_tv);
            authorTv = (TextView) itemView.findViewById(R.id.item_strategy_author_tv);
        }
    }
}
