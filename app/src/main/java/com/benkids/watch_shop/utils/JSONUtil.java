package com.benkids.watch_shop.utils;

import com.benkids.watch_shop.model.AdvertisementEntity;
import com.benkids.watch_shop.model.HotTagEntity;
import com.benkids.watch_shop.model.HotbrandsEntity;
import com.benkids.watch_shop.model.HomeDiscountEntity;
import com.benkids.watch_shop.model.HomeMenuEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 2015/11/16.
 */
public class JSONUtil {
    /**
     * 解析首页头部广告条数据
     * @param json
     * @return
     */
    public static List<AdvertisementEntity> parseAdJson(String json){
        List<AdvertisementEntity> list = new ArrayList<AdvertisementEntity>();
        AdvertisementEntity entity = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("data");
            for(int i = 0;i < array.length(); i ++){
                entity = new AdvertisementEntity();
                object = array.getJSONObject(i);
                entity.setAd_href(object.getString("ad_href"));
                entity.setAd_id(object.getString("ad_id"));
                entity.setAd_image(object.getString("ad_image"));
                list.add(entity);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解析hotBrands一级其他四个fragment中的数据
     * @param json
     * @return
     */
    public static List<HotbrandsEntity> parseHotJson(String json){
        List<HotbrandsEntity> list = new ArrayList<>();
        HotbrandsEntity entity = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("data");
            for(int i = 0;i < array.length(); i ++) {
                entity = new HotbrandsEntity();
                object = array.getJSONObject(i);
                entity.setId(object.getInt("brand_level_id"));
                entity.setLog0(object.getString("brand_logo"));
                entity.setName(object.getString("brand_name"));
                list.add(entity);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
    /**
     * 解析hottag中的数据
     * @param json
     * @return
     */

    public static List<HotTagEntity> parseHotTag(String json){
        List<HotTagEntity> list = new ArrayList<>();
        HotTagEntity entity = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("data");
            for(int i = 0;i < array.length(); i ++) {
                entity = new HotTagEntity();
                object = array.getJSONObject(i);
                entity.setTitle(object.getString("tag_title"));
                entity.setImg(object.getString("tag_image"));
                list.add(entity);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析首页菜单数据
     * @param json
     * @return
     */
    public static List<HomeMenuEntity> parseMenuJson(String json){
        List<HomeMenuEntity> list = new ArrayList<HomeMenuEntity>();
        HomeMenuEntity homeMenu = null;
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("data");
            for(int i = 0;i < array.length(); i ++){
                homeMenu = new HomeMenuEntity();
                object = array.getJSONObject(i);
                homeMenu.setImage(object.getString("image"));
                homeMenu.setName(object.getString("name"));
                list.add(homeMenu);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析首页菜单数据
     * @param json
     * @return
     */
    public static List<HomeDiscountEntity> parseDiscountJson(String json){
        List<HomeDiscountEntity> list = new ArrayList<HomeDiscountEntity>();
        HomeDiscountEntity discountEntity = null;
        try {
            JSONObject object = new JSONObject(json);
            object = object.getJSONObject("data");
            JSONArray array = object.getJSONArray("goods");
            for (int i = 0;i < array.length();i ++){
                discountEntity = new HomeDiscountEntity();
                JSONObject dataJson = array.getJSONObject(i);
                discountEntity.setImage(dataJson.getString("image"));
                discountEntity.setBegin_time(dataJson.getString("begin_time"));
                discountEntity.setEnd_time(dataJson.getString("end_time"));
                discountEntity.setOnline_price(dataJson.getString("online_price"));
                discountEntity.setShow_title(dataJson.getString("show_title"));
                discountEntity.setWb_url(dataJson.getString("wb_url"));
                list.add(discountEntity);
            }
            object = object.getJSONObject("banner");
            HomeDiscountEntity.head_image = object.getString("image");
            HomeDiscountEntity.href = object.getString("href");
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
