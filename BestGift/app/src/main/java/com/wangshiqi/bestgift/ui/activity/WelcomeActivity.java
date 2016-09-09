package com.wangshiqi.bestgift.ui.activity;

import android.os.AsyncTask;

import com.wangshiqi.bestgift.R;

/**
 * Created by dllo on 16/9/9.
 */
public class WelcomeActivity extends AbsBaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

        new TimeTask().execute(0);

    }

    public class TimeTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {
            // 开始倒计时
            int all = params[0];
            int index = 3;
            while (index > all) {
                index--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            goTo(WelcomeActivity.this, MainActivity.class);
        }
    }
}
