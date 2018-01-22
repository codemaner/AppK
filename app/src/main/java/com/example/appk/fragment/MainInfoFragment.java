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
import com.example.appk.entity.CategoriesBean;
import com.example.appk.fragment.base.BaseFragment;
import com.example.appk.ui.MainActivity;
import com.example.appk.utils.CategoryDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那个谁 on 2018/1/20.
 * 奥特曼打小怪兽
 * 作用：
 */

public class MainInfoFragment extends BaseFragment implements DefineView, ViewPager.OnPageChangeListener {
    private View mView;
    private TabLayout tab_layout;
    private ViewPager info_viewpager;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<Fragment> fragments;
    private static List<CategoriesBean> categoriesBeans= CategoryDataUtils.getCategoryBeans();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.main_info_fragment_layout, container, false);
            initView();
            initValidata();
            initListener();
            bindData();
        }
        return mView;
    }

    @Override
    public void initView() {
        tab_layout = (TabLayout) mView.findViewById(R.id.tab_layout);
        info_viewpager = (ViewPager) mView.findViewById(R.id.info_viewpager);
    }

    @Override
    public void initValidata() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setCategoriesBeen(categoriesBeans);
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < categoriesBeans.size(); i++) {
            BaseFragment fragment=null;
            if(i==0){
                fragment= HomeFragment.newInstance(categoriesBeans.get(i));
            }else{
                fragment= PageFragment.newInstance(categoriesBeans.get(i));
            }
            fragments.add(fragment);
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
        //设置viewpager监听
        info_viewpager.addOnPageChangeListener(this);

    }

    @Override
    public void bindData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //判断滑动
        if (position == 0) {
            ((MainActivity) getActivity()).getDrag_layout().setIsDrag(true);
        } else {
            ((MainActivity) getActivity()).getDrag_layout().setIsDrag(false);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
