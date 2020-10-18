package com.cjz.viewpager_tablayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cjz.viewpager_tablayout.adapter.FragAdapter;
import com.cjz.viewpager_tablayout.fragment.Demo1Fragment;
import com.cjz.viewpager_tablayout.fragment.DemoFragment;
import com.cjz.viewpager_tablayout.fragment.MainFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    private List<Fragment> fragmentList;
    private void initViewPager(){
        fragmentList = new ArrayList<>();
        for (int i = 0; i < pagesList.size(); i++) {
            if (i==pagesList.size()-1){
                fragmentList.add(new DemoFragment());
            } else {
                fragmentList.add(new MainFragment());
            }
        }
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(fragAdapter);
        //mViewPager.setOffscreenPageLimit(1);  //默认缓存当前标签页左右各一页
        //mViewPager.setOffscreenPageLimit(fragmentList.size()-1);  //一开始直接加载所有标签页
    }

    private void initTabLayout(){
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < fragmentList.size(); i++) {
            TabLayout.Tab tabAt = mTabLayout.getTabAt(i);
            if (tabAt!=null){
                tabAt.setCustomView(R.layout.main_tab_item);
                //TabLayout 去掉点击效果（水波效果）
            }
        }
        View customView = mTabLayout.getTabAt(0).getCustomView();
        TextView tv_tab = customView.findViewById(R.id.tv_tab);
        tv_tab.setBackgroundResource(R.drawable.main_tab_bg2);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView1 = tab.getCustomView();
                if (customView1!=null){
                    TextView tv_tab = customView1.findViewById(R.id.tv_tab);
                    tv_tab.setBackgroundResource(R.drawable.main_tab_bg2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView1 = tab.getCustomView();
                if (customView1!=null){
                    TextView tv_tab = customView1.findViewById(R.id.tv_tab);
                    tv_tab.setBackgroundResource(R.drawable.main_tab_bg);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private List<Integer> pagesList;
    private void getData(){
        pagesList = new ArrayList<>();
        Random random = new Random();
        int pages = random.nextInt(5) + 2;
        for (int i = 0; i < pages; i++) {
            pagesList.add(i);
        }
        initViewPager();
        initTabLayout();
    }

}