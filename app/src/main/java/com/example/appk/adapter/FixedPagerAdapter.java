package com.example.appk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.appk.entity.CategoriesBean;

import java.util.List;

/**
 * Created by 那个谁 on 2018/1/20.
 * 奥特曼打小怪兽
 * 作用：fragment适配器
 */

public class FixedPagerAdapter extends FragmentStatePagerAdapter {

    private List<CategoriesBean> mCategoriesBeen;

    public void setCategoriesBeen(List<CategoriesBean> categoriesBeen) {
        mCategoriesBeen = categoriesBeen;
    }

    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {

        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoriesBeen.get(position % mCategoriesBeen.size()).getTitle();
    }
}
