package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.SingleBean;

import java.util.List;

/**
 * Created by dllo on 16/9/21.
 */
public class SingleRightLvAdapter extends BaseAdapter {
    private Context context;
    private List<SingleBean.DataBean.CategoriesBean> datas;
    private SingleRightGridAdapter gridAdapter;
    private int selectIndex;

    public SingleRightLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SingleBean.DataBean.CategoriesBean> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_right, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (datas.get(position).getName().equals("热门分类")) {
            viewHolder.linearLayout.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.linearLayout.setVisibility(View.VISIBLE);
            viewHolder.textView.setText(datas.get(position).getName());
        }
        gridAdapter = new SingleRightGridAdapter(context);
        gridAdapter.setDatas(datas.get(position).getSubcategories());
        viewHolder.gridView.setAdapter(gridAdapter);

        return convertView;
    }
    public void setIndex(int index) {
        selectIndex = index;
    }

    class ViewHolder {
        GridView gridView;
        TextView textView;
        LinearLayout linearLayout;
        public ViewHolder(View view) {
            gridView = (GridView) view.findViewById(R.id.item_right_gv);
            textView = (TextView) view.findViewById(R.id.item_right_tv);
            linearLayout = (LinearLayout) view.findViewById(R.id.item_single_right_ll);
        }
    }
}
