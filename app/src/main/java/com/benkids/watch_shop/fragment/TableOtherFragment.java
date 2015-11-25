package com.benkids.watch_shop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.adapter.Adapter_pinpao;
import com.benkids.watch_shop.model.HotbrandsEntity;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joney on 2015/11/24.
 */
public class TableOtherFragment extends Fragment implements VolleyUtil.OnRequest {
    private TextView textView;
    private GridView gridView;

    private List<HotbrandsEntity> dingji = new ArrayList<>();
    private List<HotbrandsEntity> shehua = new ArrayList<>();
    private List<HotbrandsEntity> qingshe = new ArrayList<>();
    private List<HotbrandsEntity> shishang = new ArrayList<>();
   // private int keys;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_other, null);
        init(view);
        loadData(Constants.URL.tableotherUrl);
        return view;
    }
    private void init(View view) {
        textView = (TextView) view.findViewById(R.id.tv_title);
        gridView = (GridView) view.findViewById(R.id.gd_content);
    }
    public void setFragmentData(int key){
    changeFragment(key);
    }
    public void changeFragment(int key){
        switch (key){
            case R.id.rb_dingji:
                textView.setText("顶级品牌");
                initGridView(dingji);
                break;
            case R.id.rb_shehua:
                textView.setText("奢华品牌");
                initGridView(shehua);
                break;
            case R.id.rb_qingshe:
                textView.setText("轻奢品牌");
                initGridView(qingshe);
                break;
            case R.id.rb_shishang:
                textView.setText("时尚品牌");
                initGridView(shishang);
                break;
            default:
                break;
        }
    }
    public void initGridView(List<HotbrandsEntity> data){
        Adapter_pinpao adapter = new Adapter_pinpao(getActivity());
        gridView.setAdapter(adapter);
        adapter.setDatas(data);
    }
    //下载数据

    public void loadData(String url){
        VolleyUtil.requestString(url,this);
    }

    @Override
    public void response(String url, String response) {
        List<HotbrandsEntity> datas = JSONUtil.parseHotJson(response);
        for (int i = 0;i < datas.size();i ++){
            if(datas.get(i).getId() == 0){
                HotbrandsEntity hotbrandsEntity = new HotbrandsEntity();
                hotbrandsEntity.setName(datas.get(i).getName());
                hotbrandsEntity.setLog0(datas.get(i).getLog0());
                dingji.add(hotbrandsEntity);
            }else if(datas.get(i).getId() == 1){
                HotbrandsEntity hotbrandsEntity = new HotbrandsEntity();
                hotbrandsEntity.setName(datas.get(i).getName());
                hotbrandsEntity.setLog0(datas.get(i).getLog0());
                shehua.add(hotbrandsEntity);
            }else if(datas.get(i).getId() == 2){
                HotbrandsEntity hotbrandsEntity = new HotbrandsEntity();
                hotbrandsEntity.setName(datas.get(i).getName());
                hotbrandsEntity.setLog0(datas.get(i).getLog0());
                qingshe.add(hotbrandsEntity);
            } else if(datas.get(i).getId() == 3){
                HotbrandsEntity hotbrandsEntity = new HotbrandsEntity();
                hotbrandsEntity.setName(datas.get(i).getName());
                hotbrandsEntity.setLog0(datas.get(i).getLog0());
                shishang.add(hotbrandsEntity);
            }


        }
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }
}
