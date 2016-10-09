package com.wangshiqi.bestgift.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.wangshiqi.bestgift.R;

/**
 * Created by dllo on 16/10/8.
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView closeBtn;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        closeBtn = byView(R.id.login_close_btn);
    }

    @Override
    protected void initDatas() {
        closeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_close_btn:
                finish();
                break;
        }
    }
}
