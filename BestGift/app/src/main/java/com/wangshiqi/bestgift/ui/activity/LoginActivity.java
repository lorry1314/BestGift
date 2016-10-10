package com.wangshiqi.bestgift.ui.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wangshiqi.bestgift.R;

/**
 * Created by dllo on 16/10/8.
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView closeBtn;
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
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);

    }

    @Override
    protected void initDatas() {
        closeBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", false);
//        editor.putString()
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_close_btn:
                finish();
                break;
            case R.id.login_btn:
                String userNameValue = userName.getText().toString();
                String passWordValue = passWord.getText().toString();
                break;
        }
    }
}
