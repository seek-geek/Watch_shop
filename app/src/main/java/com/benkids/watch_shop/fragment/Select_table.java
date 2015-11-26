package com.benkids.watch_shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.benkids.watch_shop.R;
import com.benkids.watch_shop.utils.FragmentUtils;

/**
 * Created by joney on 2015/11/23.
 */
public class Select_table extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg_select;
    private TableHotFragment hotFragment = null;
    private TableOtherFragment tableOtherFragment;
    Fragment lastFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choosetable, null);
        init(view);
        initFragment();

        rg_select.setOnCheckedChangeListener(this);

        return view;
    }

    private void init(View view) {
        rg_select = (RadioGroup) view.findViewById(R.id.rg_select);

    }

    private void initFragment() {
        tableOtherFragment = new TableOtherFragment();
        addFragment(tableOtherFragment);
        FragmentUtils.hideFragment(tableOtherFragment, getFragmentManager());
        hotFragment = new TableHotFragment();
        addFragment(hotFragment);
        lastFragment = hotFragment;
    }


    public void addFragment(Fragment fg) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.framlayout, fg);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentUtils.hideFragment(lastFragment, getFragmentManager());
        if(i == R.id.rb_hot){
            lastFragment = FragmentUtils.showFragment(hotFragment, getFragmentManager());
        }else{
            tableOtherFragment.setFragmentData(i);
            lastFragment = FragmentUtils.showFragment(tableOtherFragment,getFragmentManager());

        }

        }

}
