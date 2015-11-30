package com.benkids.watch_shop.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.activity.DetailsActivity;
import com.benkids.watch_shop.model.HomeAboutWatch;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public class AboutWatch extends LinearLayout implements VolleyUtil.OnRequest{
    private RelativeLayout rl_entity_store;
    private RelativeLayout rl_maintain_watch;
    private RelativeLayout rl_recommend_watch;
    private List<HomeAboutWatch> about_list;
    private Activity manageActivity;
    private Fragment manageFragment;
    public AboutWatch(Context context) {
        super(context);
        initView();
    }

    public AboutWatch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.custom_about_watch,this,true);
        rl_entity_store = (RelativeLayout) this.findViewById(R.id.rl_entity_store);
        rl_maintain_watch = (RelativeLayout) this.findViewById(R.id.rl_maintain_watch);
        rl_recommend_watch = (RelativeLayout) this.findViewById(R.id.rl_recommend_watch);
    }
    public void initViewManage(Activity ac,Fragment fg){
        manageActivity = ac;
        manageFragment = fg;
    }
    public void loadJsonData(){
        VolleyUtil.requestString(Constants.URL.HOME_ABOUT_WATCH_URL, this);
    }
    @Override
    public void response(String url, String response) {
        List<HomeAboutWatch> list = JSONUtil.parseAboutWatchJson(response);
        loadAboutWatchData(list);
    }
    public void loadAboutWatchData(List<HomeAboutWatch> list){
        ImageView[] ivs = new ImageView[list.size()];
        Button[] btns = new Button[list.size() + 1];
        ivs[0] = (ImageView) this.findViewById(R.id.iv_entity_store);
        ivs[1] = (ImageView) this.findViewById(R.id.iv_maintain_watch);
        ivs[2] = (ImageView) this.findViewById(R.id.iv_recommend_watch);
        btns[0] = (Button) this.findViewById(R.id.btn_entity_store_title);
        btns[1] = (Button) this.findViewById(R.id.btn_entity_store_subtitle);
        //btns[0].getBackground().setAlpha(100);
       // btns[1].getBackground().setAlpha(200);
        btns[2] = (Button) this.findViewById(R.id.btn_maintain_watch);
        btns[3] = (Button) this.findViewById(R.id.btn_recommend_watch);
        for(int i = 0;i < list.size();i ++){
            VolleyUtil.requestImage(list.get(i).getImage(),ivs[i],R.mipmap.img_discount_default,R.mipmap.img_discount_default);
            if(i == 0){
                btns[i].setText(list.get(i).getTitle());
                btns[i + 1].setText(list.get(i).getSubtitle());
            }else{
                btns[i + 1].setText(list.get(i).getTitle()+"  "+list.get(i).getSubtitle());
            }
        }
        aboutWatchListener(list,ivs);
    }
    public void aboutWatchListener(List<HomeAboutWatch> list,ImageView[] ivs){
        about_list = list;
        for(int i = 0;i < ivs.length;i ++){
            ivs[i].setTag(i);
            ivs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int)v.getTag();
                    Intent intent = new Intent(manageActivity, DetailsActivity.class);
                    intent.putExtra("URL",about_list.get(index).getHref());
                    manageFragment.startActivity(intent);
                }
            });
        }
    }
    @Override
    public void errorResponse(String url, VolleyError error) {

    }
}
