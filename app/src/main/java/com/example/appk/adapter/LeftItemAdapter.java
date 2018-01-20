package com.example.appk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appk.R;
import com.example.appk.entity.LeftItemMenu;
import com.example.appk.ui.MainActivity;
import com.example.appk.utils.MenuDataUtils;

import java.util.List;

/**
 * Created by 那个谁 on 2018/1/19.
 * 奥特曼打小怪兽
 * 作用：左侧菜单adapter
 */

public class LeftItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<LeftItemMenu> mItemMenus ;

    public LeftItemAdapter(MainActivity cxt) {
        mInflater = LayoutInflater.from(cxt);
        mItemMenus = MenuDataUtils.getItemMenus();
    }

    @Override
    public int getCount() {
        return mItemMenus!=null?mItemMenus.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mItemMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null){
            holder = new Holder();
            view = mInflater.inflate(R.layout.item_left_menu_layout,null);
            holder.item_left_view_img = (ImageView) view.findViewById(R.id.item_left_view_img);
            holder.item_left_view_title =(TextView) view.findViewById(R.id.item_left_view_title);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
        holder.item_left_view_img.setImageResource(mItemMenus.get(position).getLeftIcon());
        holder.item_left_view_title.setText(mItemMenus.get(position).getTitle());
        return view;
    }

    public static class Holder{
        ImageView item_left_view_img;
        TextView item_left_view_title;
    }
}
