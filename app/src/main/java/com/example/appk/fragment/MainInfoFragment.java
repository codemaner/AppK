package com.example.appk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appk.R;
import com.example.appk.adapter.FixedPagerAdapter;
import com.example.appk.common.DefineView;
import com.example.appk.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那个谁 on 2018/1/20.
 * 奥特曼打小怪兽
 * 作用：
 */

public class MainInfoFragment extends BaseFragment implements DefineView {
    private View mView;
    private TabLayout tab_layout;
    private ViewPager info_viewpager;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<Fragment> fragments;
    private String[] titles=new String[]{"全部","前沿科技","人工智能","物联网","大公司","文娱","体育","汽车","餐饮","城市","金融","企业服务"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView==null){
            mView = inflater.inflate(R.layout.main_info_fragment_layout,container,false);
            initView();
            initValidata();
            initListener();
            bindData();
        }
        return mView;
    }

    @Override
    public void initView() {
        tab_layout=(TabLayout)mView.findViewById(R.id.tab_layout);
        info_viewpager=(ViewPager)mView.findViewById(R.id.info_viewpager);
    }

    @Override
    public void initValidata() {
        fixedPagerAdapter=new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setTitles(titles);
        fragments=new ArrayList<Fragment>();
        for(int i=0;i<titles.length;i++){
            fragments.add(PageFragment.newInstance(titles[i]));
        }

        fixedPagerAdapter.setFragments(fragments);

        //绑定
        info_viewpager.setAdapter(fixedPagerAdapter);
        tab_layout.setupWithViewPager(info_viewpager);
        //设置模式
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
