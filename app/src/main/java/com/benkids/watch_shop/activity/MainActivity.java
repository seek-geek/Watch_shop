package com.benkids.watch_shop.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.benkids.watch_shop.R;
import com.benkids.watch_shop.fragment.HomeFragment;
import com.benkids.watch_shop.fragment.Select_table;
import com.benkids.watch_shop.utils.FragmentUtils;

public class MainActivity extends FragmentActivity {
    private RadioGroup tab_rg;
    private HomeFragment fg_home = null;
    private Select_table select_table = null;
    private Fragment lastFragment = null;
    private Handler checkedHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        radioGroupListener();
    }
    /**
     * 初始化控件
     */
    public void initView(){
        tab_rg = (RadioGroup) findViewById(R.id.rg_tab);
    }
    public void initFragment(){
        select_table = new Select_table();
        addFragment(select_table);
        fg_home = new HomeFragment();
        lastFragment = fg_home;
        addFragment(fg_home);
        initHandler();
    }

    /**
     * 判断选择了哪个RadioButton
     */
    public void radioGroupListener() {
        tab_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectFragment(checkedId);
            }
        });
    }
    public void selectFragment(int checkedId){
        FragmentUtils.hideFragment(lastFragment,getSupportFragmentManager());
        switch (checkedId) {
            case R.id.rb_home:
               lastFragment = FragmentUtils.showFragment(fg_home,getSupportFragmentManager());
                break;
            case R.id.rb_choose_watch:
                lastFragment = FragmentUtils.showFragment(select_table,getSupportFragmentManager());
            break;
            case R.id.rb_shop:
                break;
            case R.id.rb_mine:
                break;
        }
    }

    /**
     * 添加fragment的方法
     * @param fg
     */
    public void addFragment(Fragment fg){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_fg_layouts, fg);
        transaction.commit();
    }
    public void initHandler() {
        checkedHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.arg1 == 1001) {
                    tab_rg.getChildAt(1).performClick();
                }
            }
        };
        fg_home.initHandler(checkedHandler);
    }
}