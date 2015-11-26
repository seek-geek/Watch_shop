package com.benkids.watch_shop.model;

/**
 * Created by Administrator on 2015/11/25.
 */
public class HomeDiscountEntity {
    private String begin_time;
    private String end_time;
    private String wb_url;
    private String show_title;
    private String image;
    private String online_price;
    public static String head_image;
    public static String href;
//
    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getWb_url() {
        return wb_url;
    }

    public void setWb_url(String wb_url) {
        this.wb_url = wb_url;
    }

    public String getShow_title() {
        return show_title;
    }

    public void setShow_title(String show_title) {
        this.show_title = show_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOnline_price() {
        return online_price;
    }

    public void setOnline_price(String online_price) {
        this.online_price = online_price;
    }
}
