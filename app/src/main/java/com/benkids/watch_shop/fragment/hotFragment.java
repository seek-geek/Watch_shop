package com.benkids.watch_shop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.benkids.watch_shop.R;

/**
 * Created by joney on 2015/11/23.
 */
public class hotFragment extends Fragment{
    private GridView gv_brands;
    private GridView gv_tag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_hot,null);
        init(view);

        return view;
    }

    private void init(View view) {
        gv_brands = (GridView) view.findViewById(R.id.gv_brands);
        gv_tag = (GridView) view.findViewById(R.id.gv_tag);
    }
}
