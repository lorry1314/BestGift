package com.wangshiqi.bestgift.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.net.NetUrl;

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
        String url = NetUrl.LVDETAIL + id;
        detailLvWeb.loadUrl(url);
        String likesCount = intent.getStringExtra("like");
        favouriteTv.setText(likesCount);
        webSet(); // 设置WebView加载网页的属性
    }

    private void webSet() {
        WebSettings set = detailLvWeb.getSettings();
//        // 让WebView能够执行javaScript
        set.setJavaScriptEnabled(true);
//        // 让JavaScript可以自动打开windows
        set.setJavaScriptCanOpenWindowsAutomatically(true);
        // 支持缩放(适配到当前屏幕)
        set.setSupportZoom(false);
        // 将图片调整到合适的大小
        set.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        set.setDisplayZoomControls(true);
        // 设置默认字体大小
        set.setDefaultFontSize(12);
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
