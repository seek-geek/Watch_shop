package com.benkids.watch_shop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.customview.HeadNavView;
import com.benkids.watch_shop.model.HomeDiscountEntity;
import com.benkids.watch_shop.model.HomeMenuEntity;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public class HomeFragment extends Fragment implements VolleyUtil.OnRequest{
    private HeadNavView headNavView;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        initView();
        return view;
    }
    public void initView(){
        headNavView = (HeadNavView) view.findViewById(R.id.hnv_home_fg);
        loadJsonData();
    }
    public void loadJsonData(){
        headNavView.loadData();
        VolleyUtil.requestString(Constants.URL.HOME_MENU_URL, this);
        VolleyUtil.requestString(Constants.URL.HOME_DISCOUNT_URL, this);
    }

    @Override
    public void response(String url, String response) {
        if(url == Constants.URL.HOME_MENU_URL) {
            List<HomeMenuEntity> list = JSONUtil.parseMenuJson(response);
            initHomeMenu(list);
        }
        if(url == Constants.URL.HOME_DISCOUNT_URL){
            List<HomeDiscountEntity> list = JSONUtil.parseDiscountJson(response);
            initDiscount(list);
        }
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }
    public void initHomeMenu(List<HomeMenuEntity> list){
        LinearLayout menu01 = (LinearLayout) view.findViewById(R.id.linear_home_menu_01);
        LinearLayout menu02 = (LinearLayout) view.findViewById(R.id.linear_home_menu_02);
        for(int i = 0;i < list.size();i ++){
            if(i <= 3){
                ((Button)menu01.getChildAt(i)).setText(list.get(i).getName());
            }else {
                ((Button)menu02.getChildAt(i - 4)).setText(list.get(i).getName());
            }
        }
    }
    public void initDiscount(List<HomeDiscountEntity> list){
        ImageView iv = (ImageView) view.findViewById(R.id.iv_head_discount);
        VolleyUtil.requestImage(HomeDiscountEntity.head_image,iv,R.mipmap.ad_home_default_img,R.mipmap.ad_home_default_img);
    }
}
