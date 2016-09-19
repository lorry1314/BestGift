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
import com.wangshiqi.bestgift.model.bean.DailyRvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/14.
 */
public class DailyRvAdapter extends RecyclerView.Adapter<DailyRvAdapter.ViewHolder> {
    private Context context;
    private List<DailyRvBean.DataBean.ItemsBean> datas;

    public DailyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<DailyRvBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DailyRvBean.DataBean.ItemsBean bean = datas.get(position);
        Picasso.with(context).load(bean.getCover_image_url()).into(holder.dailyIv);
        holder.dailyName.setText(bean.getName());
        holder.dailyDescription.setText(bean.getShort_description());
        holder.dailyPrice.setText("￥ " + subZeroAndDot(bean.getPrice()));
    }

    // 去掉多余的0 和 小数点
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是"."则去掉
        }
        return s;
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView dailyIv;
        TextView dailyName, dailyDescription, dailyPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            dailyIv = (ImageView) itemView.findViewById(R.id.daily_item_iv);
            dailyDescription = (TextView) itemView.findViewById(R.id.daily_item_short_description);
            dailyName = (TextView) itemView.findViewById(R.id.daily_item_name);
            dailyPrice = (TextView) itemView.findViewById(R.id.daily_item_price);
        }
    }
}
