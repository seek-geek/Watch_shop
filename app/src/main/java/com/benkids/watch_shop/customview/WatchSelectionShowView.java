package com.benkids.watch_shop.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.activity.DetailsActivity;
import com.benkids.watch_shop.model.HomeSelectionShowEntity;
import com.benkids.watch_shop.model.HomeWatchListEntity;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public class WatchSelectionShowView extends LinearLayout implements VolleyUtil.OnRequest {
    private LinearLayout show_watch_01;
    private LinearLayout show_watch_02;
    private LinearLayout show_watch_name;
    private LinearLayout show_watch_price;
    private LinearLayout show_watch_list;
    private Activity manageActivity;
    private Fragment manageFragment;
    private List<HomeSelectionShowEntity> selection_list;
    private List<HomeWatchListEntity> selectionlist_list;
    public WatchSelectionShowView(Context context) {
        super(context);
        initView();
    }

    public WatchSelectionShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.custom_watch_show,this,true);
        show_watch_01 = (LinearLayout) this.findViewById(R.id.linear_show_watch_01);
        show_watch_02 = (LinearLayout) this.findViewById(R.id.linear_show_watch_02);
        show_watch_name = (LinearLayout) this.findViewById(R.id.linear_show_watch_name);
        show_watch_price = (LinearLayout) this.findViewById(R.id.linear_show_watch_price);
        show_watch_list = (LinearLayout) this.findViewById(R.id.linear_show_watch_list);
    }
    public void initViewManage(Activity ac,Fragment fg){
        manageActivity = ac;
        manageFragment = fg;
    }
    public void loadJsonData(){
        VolleyUtil.requestString(Constants.URL.HOME_SHOW_WATCH_URL, this);
        VolleyUtil.requestString(Constants.URL.HOME_SHOW_WATCH_LIST_URL,this);
    }
    @Override
    public void response(String url, String response) {
        if(url == Constants.URL.HOME_SHOW_WATCH_URL) {
            List<HomeSelectionShowEntity> list = JSONUtil.parseSelectionShowJson(response);
            loadSelectionShowWatch(list);
        }
        if(url == Constants.URL.HOME_SHOW_WATCH_LIST_URL){
            List<HomeWatchListEntity> list = JSONUtil.parseWatchListJson(response);
            loadSelectionWatchList(list);
        }
    }
    public void loadSelectionShowWatch(List<HomeSelectionShowEntity> list){
        for(int i = 0;i < list.size();i ++) {
           if(i < 2){
               VolleyUtil.requestImage(list.get(i).getImage(), ((ImageView) show_watch_01.getChildAt(i)), R.mipmap.img_discount_default, R.mipmap.img_discount_default);
           }else{
               VolleyUtil.requestImage(list.get(i).getImage(),((ImageView)show_watch_02.getChildAt(i - 2)),R.mipmap.img_discount_default,R.mipmap.img_discount_default);
               ((TextView)show_watch_name.getChildAt(i - 2)).setText(list.get(i).getBrand_name());
               ((TextView)show_watch_price.getChildAt(i - 2)).setText("￥"+list.get(i).getGoods_price());
           }
        }
        selectionWatchListener(list);
    }
    public void selectionWatchListener(List<HomeSelectionShowEntity> list){
        selection_list = list;
        ImageView[] ivs = new ImageView[list.size()];
        for(int i = 0;i < list.size();i ++){
            if(i < list.size() / 2) {
                ivs[i] = ((ImageView) show_watch_01.getChildAt(i));
                ivs[i].setTag(i);
                ivs[i].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = (int) v.getTag();
                        Toast.makeText(getContext(), "亲,暂时没有数据哦", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                ivs[i] = ((ImageView) show_watch_02.getChildAt(i - 2));
                ivs[i].setTag(i);
                ivs[i].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = (int) v.getTag();
                        Intent intent = new Intent(manageActivity, DetailsActivity.class);
                        intent.putExtra("URL", selection_list.get(index).getHref());
                        manageFragment.startActivity(intent);
                    }
                });
            }

        }
    }
    public void loadSelectionWatchList(List<HomeWatchListEntity> list){
        show_watch_list.removeAllViews();
        ImageView[] ivs = new ImageView[list.size()];
        for(int i = 0;i < list.size();i ++){
            ivs[i] = new ImageView(getContext());
            ivs[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
            VolleyUtil.requestImage(list.get(i).getImage(), ivs[i], R.mipmap.img_head_discount_default,R.mipmap.img_discount_default);
            show_watch_list.addView(ivs[i]);
        }
        selectionWatchListListener(list,ivs);
    }
    public void selectionWatchListListener(List<HomeWatchListEntity> list,ImageView[] ivs){
        selectionlist_list = list;
        for(int i = 0;i < ivs.length;i ++){
            ivs[i].setTag(i);
            ivs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int)v.getTag();
                    Intent intent = new Intent(manageActivity,DetailsActivity.class);
                    intent.putExtra("URL",selectionlist_list.get(index).getHref());
                    manageFragment.startActivity(intent);
                }
            });
        }
    }
    @Override
    public void errorResponse(String url, VolleyError error) {

    }
}
