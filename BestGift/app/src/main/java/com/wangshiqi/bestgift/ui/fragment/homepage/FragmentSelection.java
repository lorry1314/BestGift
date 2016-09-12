package com.wangshiqi.bestgift.ui.fragment.homepage;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.RotateBean;
import com.wangshiqi.bestgift.ui.adapter.SelectionVpAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class FragmentSelection extends AbsFragment {
    private static final int TIME = 3000;
    private ViewPager viewPager;
    private LinearLayout pointLl;
    private List<RotateBean> datas;
    private SelectionVpAdapter vpAdapter;

    public static FragmentSelection newInstance() {

        Bundle args = new Bundle();

        FragmentSelection fragment = new FragmentSelection();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_selection;
    }

    @Override
    protected void initView() {
        viewPager = byView(R.id.selection_vp);
        pointLl = byView(R.id.rotate_poiont_container);
    }

    @Override
    protected void initDatas() {
        buildDatas();
        vpAdapter = new SelectionVpAdapter(datas, context);
        viewPager.setAdapter(vpAdapter);
        // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
        viewPager.setCurrentItem(datas.size() * 100);
        // 开始轮播
        handler = new Handler();
        startRotate();
        // 添加轮播标识点
        addPoints();
        // 随着轮播改变标识点
        changePoints();
    }

    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有标识点设置为白色
                    for (int i = 0; i < datas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.dispoint);
                    }
                    // 设置当前位置标识点为灰色
                    ImageView iv = (ImageView) pointLl.getChildAt(position % datas.size());
                    iv.setImageResource(R.mipmap.point);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addPoints() {
        // 有多少张图片加载多少个标识点
        for (int i = 0; i < datas.size(); i++) {
            ImageView poiontIv = new ImageView(context);
            poiontIv.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
            poiontIv.setLayoutParams(params);
            // 设置第0页标识点为灰色
            if (i == 0) {
                poiontIv.setImageResource(R.mipmap.point);
            }else {
                poiontIv.setImageResource(R.mipmap.dispoint);
            }
            pointLl.addView(poiontIv);
        }
    }

    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }

    private void buildDatas() {
        datas = new ArrayList<>();
        datas.add(new RotateBean(R.mipmap.arg1));
        datas.add(new RotateBean(R.mipmap.arg2));
        datas.add(new RotateBean(R.mipmap.arg3));
        datas.add(new RotateBean(R.mipmap.arg4));
        datas.add(new RotateBean(R.mipmap.arg5));
        datas.add(new RotateBean(R.mipmap.arg6));
    }


}
