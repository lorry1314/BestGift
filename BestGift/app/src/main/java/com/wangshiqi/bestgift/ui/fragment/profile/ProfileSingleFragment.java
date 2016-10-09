package com.wangshiqi.bestgift.ui.fragment.profile;

import android.view.View;
import android.widget.ImageView;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.activity.CollectionActivity;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

/**
 * Created by dllo on 16/9/10.
 * 单品
 */
public class ProfileSingleFragment extends AbsFragment {

    private ImageView singleIv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_profile_single;
    }

    @Override
    protected void initView() {
        singleIv = byView(R.id.profile_single_iv);
    }

    @Override
    protected void initDatas() {
        singleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(CollectionActivity.class);
            }
        });
    }
}
