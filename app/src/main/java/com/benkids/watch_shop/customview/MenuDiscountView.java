package com.benkids.watch_shop.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
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
import com.benkids.watch_shop.model.HomeDiscountEntity;
import com.benkids.watch_shop.model.HomeMenuEntity;
import com.benkids.watch_shop.utils.Constants;
import com.benkids.watch_shop.utils.JSONUtil;
import com.benkids.watch_shop.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class MenuDiscountView extends LinearLayout implements VolleyUtil.OnRequest {
    private ImageView iv_head;
    private LinearLayout menu_iv01;
    private LinearLayout menu_iv02;
    private LinearLayout menu_tv01;
    private LinearLayout menu_tv02;
    private int menuIndex = 0;
    private Activity manageActivity;
    private Fragment manageFragment;
    private List<HomeMenuEntity> menu_list;
    private List<HomeDiscountEntity> discount_list;
    private Handler checkedHandler;

    public MenuDiscountView(Context context) {
        super(context);
        initView();
    }
    public MenuDiscountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public void initViewManage(Activity ac,Fragment fg){
        manageActivity = ac;
        manageFragment = fg;
    }
    public void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.custom_menu_discountview,this,true);
        menu_iv01 = (LinearLayout) this.findViewById(R.id.linear_menu_iv_01);
        menu_iv02 = (LinearLayout) this.findViewById(R.id.linear_menu_iv_02);
        menu_tv01 = (LinearLayout) this.findViewById(R.id.linear_menu_tv_01);
        menu_tv02 = (LinearLayout) this.findViewById(R.id.linear_menu_tv_02);
        iv_head = (ImageView) this.findViewById(R.id.iv_head_discount);
    }
    public void initHandler(Handler handler){
        checkedHandler = handler;
    }
    /**
     * 加载界面的数据
     */
    public void loadData(){
        VolleyUtil.requestString(Constants.URL.HOME_DISCOUNT_URL, this);
        VolleyUtil.requestString(Constants.URL.HOME_MENU_URL, this);
    }
    @Override
    public void response(String url, String response) {
        if(url == Constants.URL.HOME_MENU_URL) {
            List<HomeMenuEntity> list = JSONUtil.parseMenuJson(response);
            loadMenuData(list);
        }
        if(url == Constants.URL.HOME_DISCOUNT_URL) {
            List<HomeDiscountEntity> list = JSONUtil.parseDiscountJson(response);
            loadDiscountData(list);
        }
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }

    /**
     * 加载手表打折部分的界面数据
     * @param list
     */
    public void loadDiscountData(List<HomeDiscountEntity> list){
        ImageView[] ivs = new ImageView[list.size()];
        Button[] btns = new Button[list.size()];
        TextView[] tvs = new TextView[list.size()];
        VolleyUtil.requestImage(HomeDiscountEntity.head_image, iv_head, R.mipmap.img_head_discount_default, R.mipmap.img_head_discount_default);
        ivs[0] = (ImageView) this.findViewById(R.id.iv_watch_discount_01);
        ivs[1] = (ImageView) this.findViewById(R.id.iv_watch_discount_02);
        ivs[2] = (ImageView) this.findViewById(R.id.iv_watch_discount_03);
        btns[0] = (Button) this.findViewById(R.id.btn_discount_01);
        btns[1] = (Button) this.findViewById(R.id.btn_discount_02);
        btns[2] = (Button) this.findViewById(R.id.btn_discount_03);
        tvs[0] = (TextView) this.findViewById(R.id.tv_price_01);
        tvs[1] = (TextView) this.findViewById(R.id.tv_price_02);
        tvs[2] = (TextView) this.findViewById(R.id.tv_price_03);
        for(int i = 0;i < list.size();i ++){
            VolleyUtil.requestImage(list.get(i).getImage(),ivs[i],R.mipmap.img_discount_default,R.mipmap.img_discount_default);
            btns[i].setText(list.get(i).getShow_title());
            tvs[i].setText("￥"+list.get(i).getOnline_price());
        }
        homeDiscountListener(list,ivs);
    }
    public void homeDiscountListener(List<HomeDiscountEntity> list,ImageView[] ivs){
        discount_list = list;
        iv_head.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manageActivity,DetailsActivity.class);
                intent.putExtra("URL", HomeDiscountEntity.href);
                manageFragment.startActivity(intent);
            }
        });
        for(int i = 0;i < ivs.length;i ++){
            ivs[i].setTag(i);
            ivs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int) v.getTag();
                    Intent intent = new Intent(manageActivity,DetailsActivity.class);
                    intent.putExtra("URL",discount_list.get(index).getWb_url());
                    manageFragment.startActivity(intent);
                }
            });
        }
    }
    /**
     * 加载首页菜单部分文字
     * @param list
     */
    public void loadMenuData(List<HomeMenuEntity> list){
        for(int i = 0;i < list.size();i ++){
            if(i < list.size() / 2){
                VolleyUtil.requestImage(list.get(i).getImage(), ((ImageView) menu_iv01.getChildAt(i)), R.mipmap.img_menu_default, R.mipmap.img_menu_default);
                ((TextView)menu_tv01.getChildAt(i)).setText(list.get(i).getName());
            }else{
                VolleyUtil.requestImage(list.get(i).getImage(),((ImageView)menu_iv02.getChildAt(i - 4)),R.mipmap.img_menu_default,R.mipmap.img_menu_default);
                ((TextView)menu_tv02.getChildAt(i - 4)).setText(list.get(i).getName());
            }
        }
        homeMenuListener(list);
    }

    /**
     * 首页菜单监听器
     * @param list
     */
    public void homeMenuListener(List<HomeMenuEntity> list){
        menu_list = list;
        for(int i = 0;i < list.size();i ++){
            menuIndex = i;
            if(i < list.size() / 2) {
                ((ImageView) menu_iv01.getChildAt(i)).setOnClickListener(new OnClickListener() {
                    int index = menuIndex;

                    @Override
                    public void onClick(View v) {
                        if(index == 0){
                            Message message = new Message();
                            message.arg1 = 1001;
                            checkedHandler.sendMessage(message);
                        }else {
                            Intent intent = new Intent(manageActivity, DetailsActivity.class);
                            intent.putExtra("URL",menu_list.get(index).getHref());
                            manageFragment.startActivity(intent);
                        }
                    }
                });
            }else{
                ((ImageView) menu_iv02.getChildAt(i - 4)).setOnClickListener(new OnClickListener() {
                    int index = menuIndex;
                    @Override
                    public void onClick(View v) {
                        if(index == 7) {
                            Intent intent = new Intent(manageActivity, DetailsActivity.class);
                            intent.putExtra("URL",menu_list.get(index).getHref());
                            manageFragment.startActivity(intent);
                        }else{
                            Toast.makeText(getContext(),"亲,暂时没有数据哦",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}
