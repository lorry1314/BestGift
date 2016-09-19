package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.RotateImgBean;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 */
public class SelectionVpAdapter extends PagerAdapter {
    private List<RotateImgBean.DataBean.BannersBean> datas;
    private Context context;
    private LayoutInflater inflater;




    public SelectionVpAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public SelectionVpAdapter(List<RotateImgBean.DataBean.BannersBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<RotateImgBean.DataBean.BannersBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // 为了让ViewPager到最后一页不会像翻书一样回到第一页
        // 设置页数为int最大值,这样向下滑动永远都是下一页
        return datas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       // position是int最大值所以这里可能是几百甚至上千, 因此取余避免数组越界
        int newPosition = position % datas.size();
        View convertView = inflater.inflate(R.layout.item_selection_vp, container, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_rotate_iv);
        RotateImgBean.DataBean.BannersBean bean = datas.get(newPosition);
        Picasso.with(context).load(bean.getImage_url()).into(imageView);
        container.addView(convertView);
        return convertView;
    }
}
