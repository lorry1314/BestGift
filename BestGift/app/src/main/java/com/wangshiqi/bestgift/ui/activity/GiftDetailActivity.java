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

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/9/29.
 */
public class GiftDetailActivity extends AbsBaseActivity {
    private WebView giftDetailWb;
    private Button goToTaobao;
    private ImageView collectIv, backIv;

    private boolean isCollect = false;
    private ImageView shareIv;

    @Override
    protected int setLayout() {
        return R.layout.activity_giftdetail;
    }

    @Override
    protected void initViews() {
        giftDetailWb = byView(R.id.giftdetail_wb);
        goToTaobao = byView(R.id.taobao_btn);
        collectIv = byView(R.id.gift_detail_collect);
        backIv = byView(R.id.giftdetail_back_iv);
        shareIv = byView(R.id.gift_detail_share);
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
                    Toast.makeText(GiftDetailActivity.this, "喜欢成功", Toast.LENGTH_SHORT).show();
                    isCollect = true;
                }else {
                    collectIv.setImageResource(R.mipmap.ic_action_compact_favourite_normal);
                    LiteOrmInstance.getLiteOrmInstance().deleteByName(name);
                    Toast.makeText(GiftDetailActivity.this, "取消喜欢成功", Toast.LENGTH_SHORT).show();
                    isCollect = false;
                }
            }
        });
        if (LiteOrmInstance.getLiteOrmInstance().queryByName(name).size() != 0) {
            collectIv.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
            isCollect = true;
        }
        backIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }


    private void share() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.baidu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

}
