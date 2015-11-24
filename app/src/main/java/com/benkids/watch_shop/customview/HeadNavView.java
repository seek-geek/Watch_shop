package com.benkids.watch_shop.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

import java.util.List;

/**
 *
 */
public class HeadNavView extends FrameLayout implements VolleyUtil.OnRequest, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImgNavView inv;


    public HeadNavView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custem_navview, this, true);
        viewPager = (ViewPager) this.findViewById(R.id.vp_head);
        viewPager.setOnPageChangeListener(this);

        inv = (ImgNavView) findViewById(R.id.inv_id);
    }

    /**
     *
     * @param cityid
     */
    public void loadData(){
        /*if(cityid > 0){
            String url = String.format(Constants.URL.HOME_HEAD_URL, cityid);
            VolleyUtil.requestString(url, this);
        }*/

        VolleyUtil.requestString(Constants.URL.AD_IMAGE_URL,this);
    }


    /**
     * @param url
     * @param response
     */
    @Override
    public void response(String url, String response) {
        /*List<HeadNavEntity> datas = JSONUtil.getHeadNavByJSON(response);
        inv.setCount(datas.size());
        viewPagerAdapter = new ViewPagerAdapter(fm, datas);
        viewPager.setAdapter(viewPagerAdapter);*/
        List<AdvertisementEntity> list = JSONUtil.parseAdJson(response);
        inv.setCount(list.size());
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        downloadImage(list);
    }
    @Override
    public void errorResponse(String url, VolleyError error) {

    }
    public void downloadImage(List<AdvertisementEntity> list){
        for(int i = 0;i < list.size(); i ++){

        }
    }
    /**
     * FragmentStatePagerAdapter
     * FragmentPagerAdapter
     */
    class ViewPagerAdapter extends PagerAdapter{
    private List<ImageView> list = null;
        public ViewPagerAdapter(){
        }
        public void setData(List<ImageView> list){
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
        /*private List<HeadNavEntity> datas;

        public ViewPagerAdapter(FragmentManager fm, List<HeadNavEntity> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            HeadNavFragment headNavFragment = HeadNavFragment.getInstance(datas.get(position));
            return headNavFragment;
        }

        @Override
        public int getCount() {
            return datas.size();
        }*/
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
