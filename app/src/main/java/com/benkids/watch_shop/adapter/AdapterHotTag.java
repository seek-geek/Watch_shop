package com.benkids.watch_shop.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.benkids.watch_shop.R;
import com.benkids.watch_shop.model.HotTagEntity;
import com.benkids.watch_shop.utils.VolleyUtil;

/**
 * Created by joney on 2015/11/26.
 */
public class AdapterHotTag extends AbsBaseAdapter<HotTagEntity>{
    public AdapterHotTag(Context context) {
        super(context, R.layout.item_table);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, HotTagEntity data) {
        ImageView imageView = (ImageView) viewHolder.getView(R.id.iv_item);
        VolleyUtil.requestImage(data.getImg(),imageView,R.drawable.no_brand_logo,R.drawable.no_brand_logo);
        TextView textView = (TextView) viewHolder.getView(R.id.tv_item);
        textView.setText(data.getTitle());

    }
}
