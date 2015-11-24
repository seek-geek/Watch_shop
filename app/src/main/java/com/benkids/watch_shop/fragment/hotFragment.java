package com.benkids.watch_shop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benkids.watch_shop.R;

/**
 * Created by joney on 2015/11/23.
 */
public class hotFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_hot,null);
        return view;
    }
}
