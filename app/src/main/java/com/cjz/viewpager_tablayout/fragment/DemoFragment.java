package com.cjz.viewpager_tablayout.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjz.viewpager_tablayout.R;
import com.cjz.viewpager_tablayout.adapter.FragAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DemoFragment extends Fragment {

    public DemoFragment() {
        // Required empty public constructor
    }

    public static DemoFragment newInstance() { ;
        return new DemoFragment();
    }

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demo, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
    }

    private List<Fragment> fragmentList;
    private void initViewPager(){
        fragmentList = new ArrayList<>();
        for (Integer page : pagesList) {
            fragmentList.add(new Demo1Fragment());
        }
        FragAdapter fragAdapter = new FragAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(fragAdapter);
    }

    private void initTabLayout(){
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < fragmentList.size(); i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
            if (tabAt!=null){
                tabAt.setCustomView(R.layout.main_tab_item);
            }
        }
        View customView = tabLayout.getTabAt(0).getCustomView();
        TextView tv_tab = customView.findViewById(R.id.tv_tab);
        tv_tab.setBackgroundResource(R.drawable.main_tab_bg2);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    private boolean isNotInit = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (viewPager!=null){
            viewPager.removeAllViews();
        }
        if (tabLayout!=null){
            tabLayout.removeAllTabs();
        }
        if (isVisibleToUser){
            //若主标签为3个及以上，直接点击最后一项，会报错。java.lang.IllegalStateException: Fragment DemoFragment ... has not been attached yet.
            //因为那个标签页没有初始化，这时候不能在setUserVisibleHint操作UI，要在onResume回调方法里操作才行。
            //因为setUserVisibleHint是早于onCreateView之前执行的，所以会报空指针
            if (viewPager!=null&&tabLayout!=null){
                getData();
            } else {
                isNotInit = true;
            }
        }
    }

    //所以等初始化完回调之后进行UI操作
    @Override
    public void onResume() {
        super.onResume();
        if (isNotInit){
            getData();
        }
    }

    //onHiddenChanged在显示或者隐藏的时候都会被调用
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){

        }
    }
}