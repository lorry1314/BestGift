package com.wangshiqi.bestgift.ui.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.ui.fragment.category.CategoryFragment;
import com.wangshiqi.bestgift.ui.fragment.gift.GiftFragment;
import com.wangshiqi.bestgift.ui.fragment.homepage.HomepageFragment;
import com.wangshiqi.bestgift.ui.fragment.profile.ProfileFragment;

public class MainActivity extends AbsBaseActivity {
    private RadioGroup radioGroup;

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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.homepage_rb:
                        transaction.replace(R.id.replace_view, HomepageFragment.newInstance());
                        break;
                    case R.id.gift_rb:
                        transaction.replace(R.id.replace_view, GiftFragment.newInstance());
                        break;
                    case R.id.category_rb:
                        transaction.replace(R.id.replace_view, CategoryFragment.newInstance());
                        break;
                    case R.id.profile_rb:
                        transaction.replace(R.id.replace_view, ProfileFragment.newInstance());
                        break;
                }
                transaction.commit();
            }
        });
        radioGroup.check(R.id.homepage_rb);
    }


    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    /**
     * 物理返回键按俩下退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出" + getResources().getString(R.string.app_name),
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("name");
            editor.remove("icon");
            editor.commit();
            finish();
            System.exit(0);
        }
    }

}
