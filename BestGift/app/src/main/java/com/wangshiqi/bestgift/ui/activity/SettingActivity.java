package com.wangshiqi.bestgift.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.utils.DataCleanManager;

/**
 * Created by dllo on 16/10/11.
 */
public class SettingActivity extends AbsBaseActivity {

    private RelativeLayout setRl;
    private TextView cleanTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        setRl = byView(R.id.setting_rl);
        cleanTv = byView(R.id.clean_cache_tv);
    }

    @Override
    protected void initDatas() {
        try {
            cleanTv.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    DataCleanManager.clearAllCache(SettingActivity.this);
                    DataCleanManager.getTotalCacheSize(SettingActivity.this);
                    cleanTv.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                    Toast.makeText(SettingActivity.this, "缓存清除成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
