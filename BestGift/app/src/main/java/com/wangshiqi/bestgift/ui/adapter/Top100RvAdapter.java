package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.DailyRvBean;
import com.wangshiqi.bestgift.utils.ScreanSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/14.
 */
public class Top100RvAdapter extends RecyclerView.Adapter<Top100RvAdapter.ViewHolder> {
    private Context context;
    private List<DailyRvBean.DataBean.ItemsBean> datas;

    public Top100RvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<DailyRvBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top100_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DailyRvBean.DataBean.ItemsBean bean = datas.get(position);
        /**
         * 设置TOP加数字斜体, 数字相对放大
         */
        SpannableString span = new SpannableString("TOP" + bean.getOrder());
        span.setSpan(new StyleSpan(Typeface.ITALIC), 0, String.valueOf(bean.getOrder()).length() + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new RelativeSizeSpan(1.2f), 3, String.valueOf(bean.getOrder()).length() + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /**
         * 前三设置标签不同
         */
        if (position < 3) {
            Picasso.with(context).load(bean.getCover_image_url()).config(Bitmap.Config.RGB_565).into(holder.topIv);
            holder.topName.setText(bean.getName());
            holder.topDescription.setText(bean.getShort_description());
            holder.topPrice.setText("￥ " + subZeroAndDot(bean.getPrice()));
            holder.topTv1.setText(span);
            holder.topTv2.setVisibility(View.INVISIBLE);
        }else {
            Picasso.with(context).load(bean.getCover_image_url()).resize(ScreanSizeUtil.getScreeanWidth(context) * 190 / 768, ScreanSizeUtil.getScreenHeight(context) * 150 / 1280).config(Bitmap.Config.RGB_565).into(holder.topIv);
            holder.topName.setText(bean.getName());
            holder.topDescription.setText(bean.getShort_description());
            holder.topPrice.setText("￥ " + subZeroAndDot(bean.getPrice()));
            holder.topTv2.setText(span);
            holder.topTv1.setVisibility(View.INVISIBLE);
        }
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

        ImageView topIv;
        TextView topName, topDescription, topPrice, topTv1, topTv2;

        public ViewHolder(View itemView) {
            super(itemView);
            topIv = (ImageView) itemView.findViewById(R.id.top_item_iv);
            topDescription = (TextView) itemView.findViewById(R.id.top_item_short_description);
            topName = (TextView) itemView.findViewById(R.id.top_item_name);
            topPrice = (TextView) itemView.findViewById(R.id.top_item_price);
            topTv1 = (TextView) itemView.findViewById(R.id.top_item_tv1);
            topTv2 = (TextView) itemView.findViewById(R.id.top_item_tv2);
        }
    }
}
