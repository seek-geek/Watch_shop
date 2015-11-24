package com.benkids.watch_shop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.benkids.watch_shop.R;
import com.benkids.watch_shop.fragment.HomeFragment;
import com.benkids.watch_shop.fragment.Select_table;

public class MainActivity extends FragmentActivity {
    private RadioGroup tab_rg;
    private HomeFragment fg_home = null;
    private Select_table select_table = null;

    Fragment lastFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        radioGroupListener();
    }

    /**
     * 初始化控件
     */
    public void initView(){
        tab_rg = (RadioGroup) findViewById(R.id.rg_tab);
    }

    /**
     * 判断选择了哪个RadioButton
     */
    public void radioGroupListener(){
        tab_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideFragment(lastFragment);
                switch (checkedId) {
                    case R.id.rb_home:
                        getHomeFragmentInstance();
                        showFragment(fg_home);
                        break;
                    case R.id.rb_choose_watch:
                        getTableFragmentInstance();
                        showFragment(select_table);
                        break;
                    case R.id.rb_shop:
                        break;
                    case R.id.rb_mine:
                        break;
                }
            }
        });
    }
    //home
    public void getHomeFragmentInstance(){
       if(fg_home == null){
           fg_home = new HomeFragment();
           addFragment(fg_home);
       }
    }
    // 选表fragment判断
    public void getTableFragmentInstance(){
        if(select_table == null){
            select_table = new Select_table();
            addFragment(select_table);
        }
    }

    public void hideFragment(Fragment fg){
        if(fg != null) {
            getSupportFragmentManager().beginTransaction().hide(fg).commit();
        }
    }
    public void showFragment(Fragment fg){
        if(fg != null) {
            getSupportFragmentManager().beginTransaction().show(fg).commit();
            lastFragment = fg;
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
        Toast.makeText(this,"addFragment()",Toast.LENGTH_SHORT).show();
    }
}