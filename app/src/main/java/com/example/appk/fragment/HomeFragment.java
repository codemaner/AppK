package com.example.appk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appk.R;
import com.example.appk.common.Config;
import com.example.appk.common.DefineView;
import com.example.appk.entity.Aheader;
import com.example.appk.entity.CategoriesBean;
import com.example.appk.entity.HomeNews;
import com.example.appk.fragment.base.BaseFragment;
import com.example.appk.utils.OkhttpManger;
import com.example.cwidgetutil.adapter.helper.BaseAdapterHelper;
import com.example.cwidgetutil.adapter.helper.QuickAdapter;
import com.example.cwidgetutil.adapter.helper.cwidgetutils.AutoGallery;
import com.example.cwidgetutil.adapter.helper.cwidgetutils.FlowIndicator;
import com.example.cwidgetutil.adapter.helper.cwidgetutils.PullToRefreshListView;
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

public class HomeFragment extends BaseFragment implements DefineView {
    private View mView;
    private static final String KEY = "EXTRA";
    private CategoriesBean categoriesBean;
    private PullToRefreshListView home_listview;
    ArrayList<HomeNews> userBeanList = null;
    ArrayList<Aheader> ahderList = null;
    private String[] masks;
    private int[] mask_colors;
    private FrameLayout home_framelayout;
    private LinearLayout loading, empty, error;
    private QuickAdapter<HomeNews> quickadapter;
    private View headView;
    private LayoutInflater mInflater;
    private AutoGallery headline_image_gallery;
    private FlowIndicator headline_circle_indicator;
    private int gallerySelectedPositon = 0;//Gallery索引
    private int circleSelectedPosition = 0; // 默认指示器的圆圈的位置为第一
    private ImageLoader mImageLoader;
    private DisplayImageOptions mDisplayImageOptions;

    public static HomeFragment newInstance(CategoriesBean extra) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, extra);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            categoriesBean = (CategoriesBean) bundle.getSerializable(KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.home_fragment_layout, container, false);
            mInflater = LayoutInflater.from(getActivity());
            headView = mInflater.inflate(R.layout.gallery_indicator_layout, null);
            initView();
            initValidata();
            initListener();
        }
        return mView;
    }

    @Override
    public void initView() {
        home_listview = (PullToRefreshListView) mView.findViewById(R.id.home_listview);
        home_framelayout = (FrameLayout) mView.findViewById(R.id.home_framelayout);
        home_listview.addHeaderView(headView);
        loading = (LinearLayout) mView.findViewById(R.id.loading);
        empty = (LinearLayout) mView.findViewById(R.id.empty);
        error = (LinearLayout) mView.findViewById(R.id.error);
        //获取AutoGallery和FlowIndicator控件
        headline_image_gallery = (AutoGallery) headView.findViewById(R.id.headline_image_gallery);
        headline_circle_indicator = (FlowIndicator) headView.findViewById(R.id.headline_circle_indicator);
    }

    @Override
    public void initValidata() {
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(getContext());
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(configuration);
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.defaultbg)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        masks = new String[]{"早期项目", "创投新闻", "创投人说", "注意力经济", "体育", "物联网", "金融", "城市", "大公司", "汽车"};
        mask_colors = new int[]{R.color.mask_tags_1, R.color.mask_tags_2,
                R.color.mask_tags_3, R.color.mask_tags_4, R.color.mask_tags_5,
                R.color.mask_tags_6, R.color.mask_tags_7, R.color.mask_tags_8,
                R.color.mask_tags_9, R.color.mask_tags_10, R.color.mask_tags_11, R.color.mask_tags_12};
        home_listview.setVisibility(View.GONE);
        home_framelayout.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);

        /**
         * 获取news
         */
        OkhttpManger.getAsync(Config.CRAWLER_URL, new OkhttpManger.DataCallBack() {
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
                userBeanList = new ArrayList<>();
                //加强for循环遍历JsonArray
                for (JsonElement user : jsonArray) {
                    //使用GSON，直接转成Bean对象
                    HomeNews userBean = gson.fromJson(user, HomeNews.class);
                    userBeanList.add(userBean);
                }
                setData();
            }
        });

        /**
         *获取轮播
         */
        OkhttpManger.getAsync(Config.HEADER_URL, new OkhttpManger.DataCallBack() {
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
                ahderList = new ArrayList<>();
                for (JsonElement user : jsonArray) {
                    //使用GSON，直接转成Bean对象
                    Aheader aheadBean = gson.fromJson(user, Aheader.class);
                    ahderList.add(aheadBean);
                }
                setData();
            }
        });

    }


    public void setData() {
        if (userBeanList != null && ahderList != null) {
            home_listview.setVisibility(View.VISIBLE);
            home_framelayout.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            error.setVisibility(View.GONE);
            bindData();
        } else {
            home_listview.setVisibility(View.GONE);
            home_framelayout.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
            error.setVisibility(View.GONE);
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

        int topSize = ahderList.size();
        //设置指示器
        headline_circle_indicator.setCount(topSize);
        headline_circle_indicator.setSeletion(circleSelectedPosition);
        //设置画廊Gallery
        headline_image_gallery.setLength(topSize);
        gallerySelectedPositon = topSize * 50 + circleSelectedPosition;
        headline_image_gallery.setSelection(gallerySelectedPositon);
        headline_image_gallery.setDelayMillis(4000);
        headline_image_gallery.start();
        headline_image_gallery.setAdapter(new GalleryAdapter());
        headline_image_gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                circleSelectedPosition = position;
                gallerySelectedPositon = circleSelectedPosition % ahderList.size();
                headline_circle_indicator.setSeletion(gallerySelectedPositon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        headline_image_gallery.setAdapter(new GalleryAdapter());

        quickadapter = new QuickAdapter<HomeNews>(getActivity(), R.layout.item_home_news_layout, userBeanList) {
            @Override
            protected void convert(BaseAdapterHelper helper, HomeNews item) {
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

        home_listview.setAdapter(quickadapter);

    }

    /**
     * AutoGallery的自定义Adapter
     */
    class GalleryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return ahderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder _Holder = null;
            if (convertView == null) {
                _Holder = new Holder();
                convertView = mInflater.inflate(R.layout.item_gallery_layout, null);
                _Holder.item_head_gallery_img = (ImageView) convertView.findViewById(R.id.item_head_gallery_img);
                convertView.setTag(_Holder);
            } else {
                _Holder = (Holder) convertView.getTag();
            }
            //显示数据
            //显示数据
            mImageLoader.displayImage(ahderList.get(position%ahderList.size()).getImgurl(), _Holder.item_head_gallery_img, mDisplayImageOptions);

            return convertView;
        }
    }

    private static class Holder {
        ImageView item_head_gallery_img;
    }
}
