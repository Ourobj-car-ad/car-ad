package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/4/27.
 */
public class AdsInfo {
    //广告信息类
    private String id,update_time,content,advertiser_id;

    private double ads_per_time;

    private int ads_times;

    public String getId() {
        return id;
    }

    public String getAdvertiser_id() {
        return advertiser_id;
    }

    public void setAdvertiser_id(String advertiser_id) {
        this.advertiser_id = advertiser_id;
    }

    public double getAds_per_time() {
        return ads_per_time;
    }

    public void setAds_per_time(double ads_per_time) {
        this.ads_per_time = ads_per_time;
    }

    public int getAds_times() {
        return ads_times;
    }

    public void setAds_times(int ads_times) {
        this.ads_times = ads_times;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
