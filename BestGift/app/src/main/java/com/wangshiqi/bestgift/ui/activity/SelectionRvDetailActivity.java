package com.wangshiqi.bestgift.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.SelectionRvBean;
import com.wangshiqi.bestgift.model.net.IVolleyResult;
import com.wangshiqi.bestgift.model.net.NetUrl;
import com.wangshiqi.bestgift.model.net.VolleyInstance;

import java.util.List;

/**
 * 横向Rv详情 图片放大
 */
public class SelectionRvDetailActivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_rv_detail);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView = (ImageView) findViewById(R.id.selection_rv_detail_iv);
        VolleyInstance.getInstance().startRequest(NetUrl.URLRV, new IVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                SelectionRvBean selectionRvBean = gson.fromJson(resultStr, SelectionRvBean.class);
                List<SelectionRvBean.DataBean.SecondaryBannersBean> rvDatas = selectionRvBean.getData().getSecondary_banners();
                Intent intent = getIntent();
                int p = intent.getIntExtra("position", 0);
                SelectionRvBean.DataBean.SecondaryBannersBean bean = rvDatas.get(p);
                Picasso.with(SelectionRvDetailActivity.this).load(bean.getImage_url()).into(imageView);
            }

            @Override
            public void failure() {

            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}
