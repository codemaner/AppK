package com.example.appk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appk.R;
import com.example.appk.common.DefineView;
import com.example.appk.fragment.base.BaseFragment;

/**
 * Created by 那个谁 on 2018/1/20.
 * 奥特曼打小怪兽
 * 作用：
 */

public class PageFragment extends BaseFragment implements DefineView {
    private View mView;
    private static final String KEY="EXTRA";
    private String message;
    private TextView tv_page;

    public static PageFragment newInstance(String extra){
        Bundle bundle=new Bundle();
        bundle.putString(KEY, extra);
        PageFragment fragment=new PageFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null) {
            message = bundle.getString(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.page_fragment_layout,container,false);
            initView();
            initValidata();
            initListener();
            bindData();
        }
        return mView;
    }

    @Override
    public void initView() {
        tv_page = mView.findViewById(R.id.tv_page);
        if (message != null){
            tv_page.setText(message);
        }
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
