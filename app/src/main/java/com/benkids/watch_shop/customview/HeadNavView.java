package com.benkids.watch_shop.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.model.AdvertisementEntity;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class HeadNavView extends FrameLayout implements VolleyUtil.OnRequest, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImgNavView inv;
    List<AdvertisementEntity> list;


    public HeadNavView(Context context) {
        super(context);
        init();
    }

    public HeadNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custem_adview, this, true);
        viewPager = (ViewPager) this.findViewById(R.id.vp_ad);
        viewPager.setOnPageChangeListener(this);
        inv = (ImgNavView) findViewById(R.id.inv_id);
    }
    /**
     *
     */
    public void loadData() {
        VolleyUtil.requestString(Constants.URL.AD_IMAGE_URL, this);
    }


    /**
     * @param url
     * @param response
     */
    @Override
    public void response(String url, String response) {

        list = JSONUtil.parseAdJson(response);
        inv.setCount(list.size());
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        downloadImage(list);
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }

    public void downloadImage(List<AdvertisementEntity> list) {
        List<ImageView> images = new ArrayList<ImageView>();
        ImageView iv = null;
        for (int i = 0; i < list.size(); i++) {
            iv = new ImageView(getContext());
            VolleyUtil.requestImage(list.get(i).getAd_image(), iv, R.mipmap.ad_home_default_image, R.mipmap.ad_home_default_image);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            images.add(iv);
        }
        viewPagerAdapter.setData(images);
        headNavViewCarousel();
    }

    public void headNavViewCarousel(){
        final Handler handler = new Handler(){
            int imageCount = list.size();
            int currentIndex = 0;
            public void handleMessage(Message msg){
                if(imageCount <= currentIndex){
                    currentIndex = 0;
                }else {
                    currentIndex ++;
                }

                if(0 == currentIndex){
                    viewPager.setCurrentItem(currentIndex,false);
                }else {
                    viewPager.setCurrentItem(currentIndex);
                }
            }
        };
        new Timer().schedule(new TimerTask() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 3000, 3000);
    }
    /**
     * FragmentStatePagerAdapter
     * FragmentPagerAdapter
     */
    class ViewPagerAdapter extends PagerAdapter {
        private List<ImageView> list = new ArrayList<ImageView>();

        public ViewPagerAdapter() {
            super();
        }

        public void setData(List<ImageView> list) {
            this.list = list;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        inv.selectIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
