package com.example.appk.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appk.R;
import com.example.appk.common.DefineView;
import com.example.appk.entity.CategoriesBean;
import com.example.appk.entity.NewsBean;
import com.example.appk.fragment.base.BaseFragment;
import com.example.appk.utils.OkhttpManger;
import com.example.cwidgetutil.adapter.helper.BaseAdapterHelper;
import com.example.cwidgetutil.adapter.helper.QuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

/**
 * Created by 那个谁 on 2018/1/20.
 * 奥特曼打小怪兽
 * 作用：
 */

public class PageFragment extends BaseFragment implements DefineView {
    private View mView;
    private static final String KEY = "EXTRA";
    private CategoriesBean mCategoriesBean;
    private FrameLayout page_framelayout;
    private LinearLayout loading, empty, error;
    private ListView page_listview;
    ArrayList<NewsBean> mNewsBeen;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String[] masks;
    private int[] mask_colors;
    private QuickAdapter<NewsBean> quickadapter;
    private ImageLoader mImageLoader;
    private DisplayImageOptions mDisplayImageOptions;


    public static PageFragment newInstance(CategoriesBean extra) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, extra);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategoriesBean = (CategoriesBean) bundle.getSerializable(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.page_fragment_layout, container, false);
            initView();
            initValidata();
            initListener();
            bindData();
        }
        return mView;
    }

    @Override
    public void initView() {
        page_listview = mView.findViewById(R.id.page_listview);
        page_framelayout = (FrameLayout) mView.findViewById(R.id.page_framelayout);
        loading = (LinearLayout) mView.findViewById(R.id.loading);
        empty = (LinearLayout) mView.findViewById(R.id.empty);
        error = (LinearLayout) mView.findViewById(R.id.error);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public void initValidata() {

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getContext());
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(configuration);
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.defaultbg)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();


        //设置swipeRefreshLayout的进度条的背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.color_white);
        //进度条的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置进度条的偏移量
        swipeRefreshLayout.setProgressViewOffset(false, 0, 50);
        masks = new String[]{"早期项目", "创投新闻", "创投人说", "注意力经济", "体育", "物联网", "金融", "城市", "大公司", "汽车"};
        mask_colors = new int[]{R.color.mask_tags_1, R.color.mask_tags_2,
                R.color.mask_tags_3, R.color.mask_tags_4, R.color.mask_tags_5,
                R.color.mask_tags_6, R.color.mask_tags_7, R.color.mask_tags_8,
                R.color.mask_tags_9, R.color.mask_tags_10, R.color.mask_tags_11, R.color.mask_tags_12};
        page_listview.setVisibility(View.GONE);
        page_framelayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        OkhttpManger.getAsync(mCategoriesBean.getHref(), new OkhttpManger.DataCallBack() {
            @Override
            public void requestFailure(Request request, Exception e) {

            }

            @Override
            public void requestSuccess(String result) {
                //Json的解析类对象
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                JsonArray jsonArray = parser.parse(result).getAsJsonArray();
                Gson gson = new Gson();
                mNewsBeen = new ArrayList<>();
                for (JsonElement user : jsonArray) {
                    //使用GSON，直接转成Bean对象
                    NewsBean newsBean = gson.fromJson(user, NewsBean.class);
                    mNewsBeen.add(newsBean);
                }
                setData();
            }
        });
    }


    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        Toast.makeText(getActivity(), "下拉刷新成功", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });

    }

    @Override
    public void bindData() {
        quickadapter = new QuickAdapter<NewsBean>(getActivity(), R.layout.item_home_news_layout, mNewsBeen) {
            @Override
            protected void convert(BaseAdapterHelper helper, NewsBean item) {
                String mask = item.getMask();
                helper.setText(R.id.item_news_tv_type, item.getMask())
                        .setText(R.id.item_news_tv_name, item.getAvthor())
                        .setText(R.id.item_news_tv_time, item.getTime())
                        .setText(R.id.item_news_tv_content, item.getContent())
                        .setText(R.id.item_news_tv_title, item.getTitle())
                        .setImageUrl(R.id.item_news_img_icon, item.getImgurl())
                        .setImageUrl(R.id.item_news_tv_img, item.getImgurl());

                //加载图片
                mImageLoader.displayImage(item.getImgurl(), (ImageView) helper.getView(R.id.item_news_tv_img), mDisplayImageOptions);
                mImageLoader.displayImage(item.getImgurl(), (ImageView) helper.getView(R.id.item_news_img_icon), mDisplayImageOptions);

                int index = 0;
                for (int i = 0; i < masks.length; i++) {
                    if (masks[i].equals(mask)) {
                        index = i;
                        break;
                    }
                }
                TextView tv_type = (TextView) helper.getView(R.id.item_news_tv_type);
                tv_type.setTextColor(getActivity().getResources().getColor(mask_colors[index]));
                helper.getView(R.id.item_news_tv_arrow).setBackgroundColor(getActivity().getResources().getColor(mask_colors[index]));
            }

        };

        page_listview.setAdapter(quickadapter);


    }

    public void setData() {
        if (mNewsBeen != null) {
            page_listview.setVisibility(View.VISIBLE);
            page_framelayout.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            error.setVisibility(View.GONE);
            bindData();
        } else {
            page_listview.setVisibility(View.GONE);
            page_framelayout.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
            error.setVisibility(View.GONE);
        }
    }
}
