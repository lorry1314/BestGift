package com.wangshiqi.bestgift.ui.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wangshiqi.bestgift.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/10/8.
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView closeBtn, loginQQ;
    private Button loginBtn;
    private EditText userName, passWord;
    private SharedPreferences sp;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        loginBtn = byView(R.id.login_btn);
        closeBtn = byView(R.id.login_close_btn);
        userName = byView(R.id.login_user_et);
        passWord = byView(R.id.login_password_et);
        loginQQ = byView(R.id.login_qq);
    }

    @Override
    protected void initDatas() {
        closeBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        loginQQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_close_btn:
                finish();
                break;
            case R.id.login_btn:
                break;
            case R.id.login_qq:
                login();
                break;
        }
    }

    private void login() {
        // 获取第三方平台
        Platform platform = ShareSDK.getPlatform(this, QQ.NAME);
        // 授权
        platform.authorize();
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(LoginActivity.this, "完成", Toast.LENGTH_SHORT).show();
                PlatformDb db = platform.getDb();
                String name = db.getUserName();
                String icon = db.getUserIcon();
//                finish();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(LoginActivity.this, "错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(LoginActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
