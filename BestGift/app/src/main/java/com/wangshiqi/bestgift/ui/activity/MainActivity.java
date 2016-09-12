package com.wangshiqi.bestgift.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.Gift.FragmentGift;
import com.wangshiqi.bestgift.ui.fragment.category.FragmentCategory;
import com.wangshiqi.bestgift.ui.fragment.homepage.FragmentHomepage;
import com.wangshiqi.bestgift.ui.fragment.profile.FragmentProfile;

public class MainActivity extends AbsBaseActivity {

    private RadioGroup radioGroup;
    private FragmentHomepage fragmentHomepage;
    private FragmentGift fragmentGift;
    private FragmentCategory fragmentCategory;
    private FragmentProfile fragmentProfile;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        radioGroup = byView(R.id.main_radio_group);

    }

    @Override
    protected void initDatas() {
        fragmentHomepage = new FragmentHomepage();
        fragmentGift = new FragmentGift();
        fragmentCategory = new FragmentCategory();
        fragmentProfile = new FragmentProfile();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.homepage_rb:
                        transaction.replace(R.id.replace_view, fragmentHomepage);
                        break;
                    case R.id.gift_rb:
                        transaction.replace(R.id.replace_view, fragmentGift);
                        break;
                    case R.id.category_rb:
                        transaction.replace(R.id.replace_view, fragmentCategory);
                        break;
                    case R.id.profile_rb:
                        transaction.replace(R.id.replace_view, fragmentProfile);
                        break;
                }
                transaction.commit();
            }
        });
        radioGroup.check(R.id.homepage_rb);
    }
}
