package com.wangshiqi.bestgift.ui.fragment.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wangshiqi.bestgift.R;
import com.wangshiqi.bestgift.model.bean.GiftForGilrBean;
import com.wangshiqi.bestgift.model.bean.RotateImgBean;
import com.wangshiqi.bestgift.model.bean.SelectionRvBean;
import com.wangshiqi.bestgift.model.net.IVolleyResult;
import com.wangshiqi.bestgift.model.net.NetUrl;
import com.wangshiqi.bestgift.model.net.VolleyInstance;
import com.wangshiqi.bestgift.ui.activity.SelectionLvDetailActivity;
import com.wangshiqi.bestgift.view.ImageShower;
import com.wangshiqi.bestgift.ui.adapter.GiftForGirlAdapter;
import com.wangshiqi.bestgift.ui.adapter.SelectionRvAdapter;
import com.wangshiqi.bestgift.ui.adapter.SelectionVpAdapter;
import com.wangshiqi.bestgift.ui.fragment.AbsFragment;
import com.wangshiqi.bestgift.utils.SelectionOnRvItemClick;
import com.wangshiqi.bestgift.view.ReFlashListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 精选页面
 */
public class SelectionFragment extends AbsFragment implements ReFlashListView.IReflashListener {
    private static final int TIME = 3000;
    private ViewPager viewPager;
    private LinearLayout pointLl;
    private List<RotateImgBean.DataBean.BannersBean> datas;
    private SelectionVpAdapter vpAdapter;

    private RecyclerView recyclerView;
    private SelectionRvAdapter selectionRvAdapter;

    private TextView timeTv;

    private ReFlashListView selectionLv;
    private GiftForGirlAdapter selectionLvAdapter;


    public static SelectionFragment newInstance() {

        Bundle args = new Bundle();

        SelectionFragment fragment = new SelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_selection;
    }

    @Override
    protected void initView() {
        selectionLv = byView(R.id.selection_lv);


    }

    @Override
    protected void initDatas() {
        View view = LayoutInflater.from(context).inflate(R.layout.selection_headview, null);
        viewPager = (ViewPager) view.findViewById(R.id.selection_vp);
        pointLl = (LinearLayout) view.findViewById(R.id.rotate_poiont_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.selection_rv);
        timeTv = (TextView) view.findViewById(R.id.selection_tv);
        selectionLv.addHeaderView(view);
        // 轮播图
        startRoll();
        // 横向recyclerview
        selectionRecyclerView();
        // 时间
        timeAndUpdate();
        // ListView
        listView();
    }

    private void listView() {
        selectionLvAdapter = new GiftForGirlAdapter(context);
        VolleyInstance.getInstance().startRequest(NetUrl.URLLV, new IVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                GiftForGilrBean bean = gson.fromJson(resultStr, GiftForGilrBean.class);
                final List<GiftForGilrBean.DataBean.ItemsBean> datas = bean.getData().getItems();
                selectionLvAdapter.setDatas(datas);
                selectionLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, SelectionLvDetailActivity.class);
                        intent.putExtra("ID", datas.get(position - 2).getId() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void failure() {

            }
        });
        selectionLv.setAdapter(selectionLvAdapter);
        selectionLv.setInterface(this);

    }

    private void selectionRecyclerView() {
        selectionRvAdapter = new SelectionRvAdapter(context);
        VolleyInstance.getInstance().startRequest(NetUrl.URLRV, new IVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                SelectionRvBean selectionRvBean = gson.fromJson(resultStr, SelectionRvBean.class);
                List<SelectionRvBean.DataBean.SecondaryBannersBean> rvDatas = selectionRvBean.getData().getSecondary_banners();
                selectionRvAdapter.setDatas(rvDatas);
            }

            @Override
            public void failure() {

            }
        });
        recyclerView.setAdapter(selectionRvAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        selectionRvAdapter.setSelectionOnRvItemClick(new SelectionOnRvItemClick() {
            @Override
            public void onRvItemClickListener(int positon, SelectionRvBean.DataBean.SecondaryBannersBean data) {
                Intent intent = new Intent(context, ImageShower.class);
                intent.putExtra("position", positon);
                startActivity(intent);
            }
        });

    }

    private void timeAndUpdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        Calendar c = Calendar.getInstance();
        String week = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(week)) {
            week = "星期日";
        } else if ("2".equals(week)) {
            week = "星期一";
        } else if ("3".equals(week)) {
            week = "星期二";
        } else if ("4".equals(week)) {
            week = "星期三";
        } else if ("5".equals(week)) {
            week = "星期四";
        } else if ("6".equals(week)) {
            week = "星期五";
        } else if ("7".equals(week)) {
            week = "星期六";
        }
        timeTv.setText(str + " " + week);
    }

    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有标识点设置为透明色
                    for (int i = 0; i < datas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.btn_check_normal);
                        pointIv.setAlpha(0.2f);
                    }
                    // 设置当前位置标识点为白
                    ImageView iv = (ImageView) pointLl.getChildAt(position % datas.size());
                    iv.setImageResource(R.mipmap.btn_check);
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
            poiontIv.setPadding(8, 8, 8, 8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(35, 35);
            poiontIv.setLayoutParams(params);
            // 设置第0页标识点为灰色
            if (i == 0) {
                poiontIv.setImageResource(R.mipmap.btn_check);
            } else {
                poiontIv.setImageResource(R.mipmap.btn_check_normal);
                poiontIv.setAlpha(0.2f);
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
        isRotate = true;
    }



    private void startRoll() {
        VolleyInstance.getInstance().startRequest(NetUrl.IMGURL, new IVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                RotateImgBean rotateImgBean = gson.fromJson(resultStr, RotateImgBean.class);
                datas = rotateImgBean.getData().getBanners();
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

            @Override
            public void failure() {

            }
        });

    }


    @Override
    public void onReflash() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                selectionLv.reflshComplete();
            }
        }, 2000);
        selectionLvAdapter.notifyDataSetChanged();
    }
}
