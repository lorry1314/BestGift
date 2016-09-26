package com.wangshiqi.bestgift.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.net.NetUrl;

/**
 * Created by dllo on 16/9/26.
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
        String url = NetUrl.LVDETAIL + id;
        detailLvWeb.loadUrl(url);
        String likesCount = intent.getStringExtra("like");
        favouriteTv.setText(likesCount);
        shareTv.setText(likesCount);
        commentTv.setText(likesCount);
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
