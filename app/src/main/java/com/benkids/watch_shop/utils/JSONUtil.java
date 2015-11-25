package com.benkids.watch_shop.utils;

import com.benkids.watch_shop.model.AdvertisementEntity;
import com.benkids.watch_shop.model.HotbrandsEntity;

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
}
