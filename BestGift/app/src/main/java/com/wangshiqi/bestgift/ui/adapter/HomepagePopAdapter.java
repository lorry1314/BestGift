package com.wangshiqi.bestgift.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wangshiqi.bestgift.R;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class HomepagePopAdapter extends RecyclerView.Adapter<HomepagePopAdapter.MyViewHolder> {
    private List<String> datas;
    private Context context;

    public HomepagePopAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popwindow, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.popBtn.setText(datas.get(position));

    }


    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private Button popBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            popBtn = (Button) itemView.findViewById(R.id.item_pop_btn);
        }
    }
}
