package com.wangshiqi.bestgift.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.LiteOrmBean;
import com.wangshiqi.bestgift.model.db.LiteOrmInstance;

/**
 * Created by dllo on 16/9/29.
 */
public class GiftDetailActivity extends AbsBaseActivity {
    private WebView giftDetailWb;
    private Button goToTaobao;
    private ImageView collectIv;

    private boolean isCollect = false;
    @Override
    protected int setLayout() {
        return R.layout.activity_giftdetail;
    }

    @Override
    protected void initViews() {
        giftDetailWb = byView(R.id.giftdetail_wb);
        goToTaobao = byView(R.id.taobao_btn);
        collectIv = byView(R.id.gift_detail_collect);
    }

    @Override
    protected void initDatas() {
        giftDetailWb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        Intent intent = getIntent();
        final String imgUrl = intent.getStringExtra("imgUrl");
        final String name = intent.getStringExtra("name");
        final String price = intent.getStringExtra("price");
        final String description = intent.getStringExtra("description");
        final String url = intent.getStringExtra("url");
        final String taobaoUrl = intent.getStringExtra("taobaoUrl");
        giftDetailWb.loadUrl(url);
        goToTaobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("taobao", taobaoUrl);
                goTo(GiftDetailActivity.this, TaobaoShoppingActivity.class, bundle);
            }
        });
        collectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollect == false) {
                    collectIv.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                    LiteOrmBean bean = new LiteOrmBean(name, description, price, imgUrl, url, taobaoUrl);
                    LiteOrmInstance.getLiteOrmInstance().insert(bean);
                    Toast.makeText(GiftDetailActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                    isCollect = true;
                }else {
                    collectIv.setImageResource(R.mipmap.ic_action_compact_favourite_normal);
                    LiteOrmInstance.getLiteOrmInstance().deleteByName(name);
                    Toast.makeText(GiftDetailActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    isCollect = false;
                }
            }
        });
        if (LiteOrmInstance.getLiteOrmInstance().queryByName(name).size() != 0) {
            collectIv.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
            isCollect = true;
        }
    }




}
