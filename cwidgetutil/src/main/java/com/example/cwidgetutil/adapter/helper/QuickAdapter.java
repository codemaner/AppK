package com.example.cwidgetutil.adapter.helper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static com.example.cwidgetutil.adapter.helper.BaseAdapterHelper.get;

/**
 * Created by 那个谁 on 2018/1/21.
 * 奥特曼打小怪兽
 * 作用：
 */

public abstract class QuickAdapter <T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    /**
     * Create a QuickAdapter.
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public QuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public QuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    protected BaseAdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
        return get(context, convertView, parent, layoutResId, position);
    }

}
