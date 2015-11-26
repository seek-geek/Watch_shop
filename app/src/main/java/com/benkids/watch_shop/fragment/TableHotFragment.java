package com.benkids.watch_shop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.adapter.AdapterHotTag;
import com.benkids.watch_shop.adapter.Adapter_pinpao;
import com.benkids.watch_shop.model.HotTagEntity;
import com.benkids.watch_shop.model.HotbrandsEntity;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.List;

/**
 * Created by joney on 2015/11/23.
 */
public class TableHotFragment extends Fragment implements VolleyUtil.OnRequest {
    private GridView gv_brands;
    private GridView gv_tag;
    private Adapter_pinpao adapter;
    private AdapterHotTag adapterHotTag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_hot, null);
        init(view);
        loadmsg();
        return view;
    }
    //下载数据
    private void loadmsg() {
        VolleyUtil.requestString(Constants.URL.TABLE_HOTBRANDS_URL,this);
        VolleyUtil.requestString(Constants.URL.TABLE_HOTTag_URL,this);
    }

    private void init(View view) {
        gv_brands = (GridView) view.findViewById(R.id.gv_brands);
        gv_tag = (GridView) view.findViewById(R.id.gv_tag);
        //适配器适配
        adapter = new Adapter_pinpao(getActivity());
        gv_brands.setAdapter(adapter);

        adapterHotTag = new AdapterHotTag(getActivity());
        gv_tag.setAdapter(adapterHotTag);



    }


    //下载数据成功后执行的操作
    @Override
    public void response(String url, String response) {
        if(url.equals(Constants.URL.TABLE_HOTBRANDS_URL)){
            List<HotbrandsEntity> data = JSONUtil.parseHotJson(response);
            adapter.setDatas(data);
        }
        if (url.equals(Constants.URL.TABLE_HOTTag_URL)){
            List<HotTagEntity> data2 = JSONUtil.parseHotTag(response);
            adapterHotTag.setDatas(data2);
        }
    }

    @Override
    public void errorResponse(String url, VolleyError error) {
    }
}
