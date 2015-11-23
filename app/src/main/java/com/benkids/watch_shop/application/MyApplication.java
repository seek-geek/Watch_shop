package com.benkids.watch_shop.application;

import android.app.Application;
import com.benkids.watch_shop.utils.VolleyUtil;

/**
 * Created by Administrator on 2015/11/23.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.initVolley(this);
    }
}
