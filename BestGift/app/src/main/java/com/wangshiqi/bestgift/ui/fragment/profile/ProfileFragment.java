package com.wangshiqi.bestgift.ui.fragment.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.activity.LoginActivity;
import com.wangshiqi.bestgift.ui.activity.SettingActivity;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * - Created by dllo on 16/9/8.
 * 个人界面
 */
public class ProfileFragment extends AbsFragment implements View.OnClickListener {


    private RadioGroup radioGroup;
    private ProfileSingleFragment fragmentProfileSingle;
    private ProfileStrategyFragment fragmentProfileStrategy;
    private ImageView boyIv, msgIv;
    private ImageView setIv;
    private TextView loginTv;
    private SharedPreferences sharedPreferences;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        radioGroup = byView(R.id.profile_rg);
        boyIv = byView(R.id.boy);
        msgIv = byView(R.id.message);
        setIv = byView(R.id.setting);
        loginTv = byView(R.id.profile_login_tv);

    }

    @Override
    protected void initDatas() {
        fragmentProfileSingle = new ProfileSingleFragment();
        fragmentProfileStrategy = new ProfileStrategyFragment();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.profile_single:
                        transaction.replace(R.id.profile_fl, fragmentProfileSingle);

                        break;
                    case R.id.profile_strategy:
                        transaction.replace(R.id.profile_fl, fragmentProfileStrategy);
                        break;
                }
                transaction.commit();
            }
        });
        radioGroup.check(R.id.profile_single);
        boyIv.setOnClickListener(this);
        msgIv.setOnClickListener(this);
        setIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boy:
                goTo(LoginActivity.class);
                break;
            case R.id.message:
                goTo(LoginActivity.class);
                break;
            case R.id.setting:
                goTo(SettingActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String icon = sharedPreferences.getString("icon", "");
        if (!icon.isEmpty()) {
            Picasso.with(context).load(icon).into(boyIv);
            loginTv.setText(name);
        }
    }


}
