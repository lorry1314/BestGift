package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.GiftForGilrBean;
import com.wangshiqi.bestgift.utils.ScreanSizeUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/14.
 */
public class GiftForGirlAdapter extends BaseAdapter {
    private Context context;
    private List<GiftForGilrBean.DataBean.ItemsBean> datas;

    public GiftForGirlAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<GiftForGilrBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null && datas.size() > 0 ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_girl_lv, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GiftForGilrBean.DataBean.ItemsBean bean = (GiftForGilrBean.DataBean.ItemsBean) getItem(position);
        if (null != bean.getColumn() && null != bean.getColumn().getCategory()) {
            viewHolder.categoryTv.setText(bean.getColumn().getCategory());
            viewHolder.categoryTv.setVisibility(View.VISIBLE);
            viewHolder.columnTitleTv.setText(bean.getColumn().getTitle());
            viewHolder.columnTitleTv.setVisibility(View.VISIBLE);
            viewHolder.nickNameTv.setText(bean.getAuthor().getNickname());
            viewHolder.titleTv.setText(bean.getTitle());
            viewHolder.likesCountTv.setText(bean.getLikes_count() + "");
            Picasso.with(context).load(bean.getAuthor().getAvatar_url()).into(viewHolder.authorAvatorImg);
            viewHolder.coverImg.setMinimumWidth(ScreanSizeUtil.getScreeanWidth(context));
            Picasso.with(context).load(bean.getCover_image_url()).into(viewHolder.coverImg);
        }else {
            viewHolder.categoryTv.setVisibility(View.INVISIBLE);
            viewHolder.columnTitleTv.setVisibility(View.INVISIBLE);
            viewHolder.nickNameTv.setText(bean.getAuthor().getNickname());
            viewHolder.titleTv.setText(bean.getTitle());
            viewHolder.likesCountTv.setText(bean.getLikes_count() + "");
            Picasso.with(context).load(bean.getAuthor().getAvatar_url()).into(viewHolder.authorAvatorImg);
            viewHolder.coverImg.setMinimumWidth(ScreanSizeUtil.getScreeanWidth(context));
            Picasso.with(context).load(bean.getCover_image_url()).into(viewHolder.coverImg);
        }

        return convertView;
    }

    class ViewHolder {
        TextView categoryTv, columnTitleTv, nickNameTv, titleTv, likesCountTv;
        ImageView coverImg;
        CircleImageView authorAvatorImg;

        public ViewHolder(View view) {
            categoryTv = (TextView) view.findViewById(R.id.list_category_tv);
            columnTitleTv = (TextView) view.findViewById(R.id.list_column_title_tv);
            nickNameTv = (TextView) view.findViewById(R.id.list_nickname_tv);
            titleTv = (TextView) view.findViewById(R.id.list_title_tv);
            likesCountTv = (TextView) view.findViewById(R.id.list_likescount_tv);
            authorAvatorImg = (CircleImageView) view.findViewById(R.id.list_author_avatar_img);
            coverImg = (ImageView) view.findViewById(R.id.list_cover_img);
        }
    }
}
