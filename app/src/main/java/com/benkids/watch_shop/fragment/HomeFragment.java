package com.benkids.watch_shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.activity.SearchActivity;
import com.benkids.watch_shop.customview.AboutWatch;
import com.benkids.watch_shop.customview.MenuDiscountView;
import com.benkids.watch_shop.customview.HeadNavView;
import com.benkids.watch_shop.customview.SelectWatchView;
import com.benkids.watch_shop.customview.WatchSelectionShowView;

/**
 * Created by Administrator on 2015/11/23.
 */
public class HomeFragment extends Fragment{
    private HeadNavView headNavView;
    private View view;
    private MenuDiscountView discountView;
    private SelectWatchView selectWatchView;
    private WatchSelectionShowView selectionShowView;
    private AboutWatch aboutWatch;
    private SwipeRefreshLayout refreshLayout;
    private Button searchButton;
    private Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        initView();
        return view;
    }
    public void initView(){
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        headNavView = (HeadNavView) view.findViewById(R.id.hnv_home_fg);
        headNavView.initViewManage(getActivity(),this);
        headNavView.initSwipeRefreshLayout(refreshLayout);
        discountView = (MenuDiscountView) view.findViewById(R.id.discount_home_fg);
        discountView.initViewManage(getActivity(),this);
        discountView.initHandler(handler);
        selectWatchView = (SelectWatchView) view.findViewById(R.id.select_watch_home_fg);
        selectionShowView = (WatchSelectionShowView) view.findViewById(R.id.watch_selection_show);
        selectionShowView.initViewManage(getActivity(),this);
        aboutWatch = (AboutWatch) view.findViewById(R.id.about_watch_home);
        aboutWatch.initViewManage(getActivity(),this);
        searchButton = (Button) view.findViewById(R.id.bt_home_search);
        loadJsonData();
        swipeRefreshLayoutListener();
        searchButtonListener();
    }
    public void swipeRefreshLayoutListener(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                loadJsonData();
            }
        });
    }
    public void loadJsonData(){
        headNavView.loadData();
        discountView.loadData();
        selectWatchView.loadJsonData();
        selectionShowView.loadJsonData();
        aboutWatch.loadJsonData();
    }
    public void initHandler(Handler handler){
        this.handler = handler;
    }
    public void searchButtonListener(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                HomeFragment.this.startActivity(intent);
            }
        });
    }
}
