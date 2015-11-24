package com.benkids.watch_shop.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
/**
 * Created by Administrator on 2015/11/24.
 */
public class FragmentUtils {
    public static void hideFragment(Fragment fg,FragmentManager manager){
        if(fg != null) {
            manager.beginTransaction().hide(fg).commit();
        }
    }
    public static Fragment showFragment(Fragment fg,FragmentManager manager){
        if(fg != null) {
            manager.beginTransaction().show(fg).commit();
            return fg;
        }
        return null;
    }
}
