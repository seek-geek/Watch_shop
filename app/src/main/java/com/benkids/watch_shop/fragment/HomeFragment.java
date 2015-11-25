package com.benkids.watch_shop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.benkids.watch_shop.R;
import com.benkids.watch_shop.customview.HeadNavView;

/**
 * Created by Administrator on 2015/11/23.
 */
public class HomeFragment extends Fragment {
    private HeadNavView headNavView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        return view;
    }
    public void initView(View view){
        headNavView = (HeadNavView) view.findViewById(R.id.hnv_home_fg);
        loadJsonData();
    }
    public void loadJsonData(){
        headNavView.loadData();
    }
}
