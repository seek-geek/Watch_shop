package com.benkids.watch_shop.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.benkids.watch_shop.R;
import com.benkids.watch_shop.model.HotbrandsEntity;
import com.benkids.watch_shop.utils.VolleyUtil;

/**
 * Created by joney on 2015/11/25.
 */
public class Adapter_pinpao extends AbsBaseAdapter<HotbrandsEntity> {
    public Adapter_pinpao(Context context) {
        super(context, R.layout.item_table);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, HotbrandsEntity data) {
        ImageView imageView = (ImageView) viewHolder.getView(R.id.iv_item);
        VolleyUtil.requestImage(data.getLog0(),imageView,R.drawable.no_brand_logo,R.drawable.no_brand_logo);
        TextView textView = (TextView) viewHolder.getView(R.id.tv_item);
        textView.setText(data.getName());

    }
}
