package com.benkids.watch_shop.customview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.benkids.watch_shop.R;
import com.benkids.watch_shop.model.SelectWatchEntity;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class SelectWatchView extends FrameLayout implements VolleyUtil.OnRequest{
    public SelectWatchView(Context context) {
        super(context);
        initView();
    }

    public SelectWatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.custom_select_watch, this, true);
    }
    public void loadJsonData(){
        VolleyUtil.requestString(Constants.URL.HOME_SELECT_WATCH_URL, this);
    }
    @Override
    public void response(String url, String response) {
        List<SelectWatchEntity> list = JSONUtil.parseSelectWatchJson(response);
        initSelectWatchImg(list);
    }
    public void initSelectWatchImg(List<SelectWatchEntity> list){
        ImageView[] ivs = new ImageView[list.size()];
        Button[] btns = new Button[list.size()];
        ivs[0] = (ImageView) this.findViewById(R.id.iv_man_watch);
        ivs[1] = (ImageView) this.findViewById(R.id.iv_women_watch);
        btns[0] = (Button) this.findViewById(R.id.btn_man_watch);
        btns[1] = (Button) this.findViewById(R.id.btn_women_watch);
        for(int i = 0;i < list.size();i ++){
            VolleyUtil.requestImage(list.get(i).getImage(),ivs[i],R.mipmap.img_select_watch_default,R.mipmap.img_select_watch_default);
            btns[i].setText(list.get(i).getTitle());
        }
        selectWatchListener(ivs);
    }
    public void selectWatchListener(ImageView[] ivs){
        for(int i = 0;i < ivs.length;i ++){
            ivs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "亲,暂时没有数据哦", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void errorResponse(String url, VolleyError error) {

    }
}
