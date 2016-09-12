package com.wangshiqi.bestgift.ui.fragment.profile;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * - Created by dllo on 16/9/8.
 */
public class FragmentProfile extends AbsFragment {


    private RadioGroup radioGroup;
    private FragmentProfileSingle fragmentProfileSingle;
    private FragmentProfileStrategy fragmentProfileStrategy;

    @Override
    protected int setLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        radioGroup = byView(R.id.profile_rg);
    }

    @Override
    protected void initDatas() {
        fragmentProfileSingle = new FragmentProfileSingle();
        fragmentProfileStrategy = new FragmentProfileStrategy();
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
    }
}
