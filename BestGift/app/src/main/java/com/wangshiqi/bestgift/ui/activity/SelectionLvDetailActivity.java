package com.wangshiqi.bestgift.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.SelecitonLvDetailBean;
import com.wangshiqi.bestgift.model.net.IVolleyResult;
import com.wangshiqi.bestgift.model.net.NetUrl;
import com.wangshiqi.bestgift.model.net.VolleyInstance;

/**
 * Created by dllo on 16/9/26.
 * 精选ListView行布局详情(后面复用)
 */
public class SelectionLvDetailActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView detailLvWeb;
    private ImageView backIv;
    private TextView favouriteTv;
    private TextView shareTv;
    private TextView commentTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_selection_detail;
    }

    @Override
    protected void initViews() {
        detailLvWeb = byView(R.id.detail_lv_web);
        backIv = byView(R.id.back_iv);
        favouriteTv = byView(R.id.favourite_tv);
        shareTv = byView(R.id.share_tv);
        commentTv = byView(R.id.comment_tv);
    }

    @Override
    protected void initDatas() {
        backIv.setOnClickListener(this);
        detailLvWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }
        });
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        final String url = NetUrl.LVDETAIL + id;
        VolleyInstance.getInstance().startRequest(url, new IVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                SelecitonLvDetailBean detailBean = gson.fromJson(resultStr, SelecitonLvDetailBean.class);
//                String bean = detailBean.getData().getContent_html();
//                detailLvWeb.loadData(bean, "text/html; charset=UTF-8", null);
                detailLvWeb.loadUrl(detailBean.getData().getUrl());
                favouriteTv.setText(detailBean.getData().getLikes_count() + "");
                shareTv.setText(detailBean.getData().getShares_count() + "");
                commentTv.setText(detailBean.getData().getComments_count() + "");
            }

            @Override
            public void failure() {

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                finish();
                break;
        }
    }
}
