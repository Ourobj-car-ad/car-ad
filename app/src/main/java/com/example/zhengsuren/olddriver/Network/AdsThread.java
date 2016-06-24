package com.example.zhengsuren.olddriver.Network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.zhengsuren.olddriver.AdsInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by zhengsuren on 16/4/27.
 */
public class AdsThread extends Thread {

    //调用了获得广告的api，实现将服务器返回的广告信息解析，并将解析后的数据返回给主线程（LoginSuccess）

    private String url;
    private String id;
    private String types;
    private onResponseListener listener;

    public AdsThread(String url,String id,String types,onResponseListener listener)
    {
        this.url = url;
        this.id = id;
        this.types = types;
        this.listener = listener;
    }

    private AdsInfo request() {
        //获得广告
        url = url + "?id=" + id + "&types=" + types;
        try {
            URL HttpURL = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) HttpURL.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }

            String result = sb.toString();

            return parseJson(result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listener.onfailure("can not access to the server!");

        return null;
    }

    private AdsInfo parseJson(String json)
    {
        try
        {
            JSONObject object = new JSONObject(json);
            int errno = object.getInt("errno");
            String errmsg = object.getString("errmsg");
            if (0 == errno && errmsg.isEmpty())
            {
                JSONObject data = object.getJSONObject("data");
                if (data.length() != 0)
                {
                    AdsInfo ads = new AdsInfo();
                    String id = data.getString("id");
                    String update_time = data.getString("title");
                    String content = data.getString("content");
                    String advertiser_id = data.getString("advertiser_id");
                    int ads_lenth = content.length();
                    double ads_per_time = ads_lenth/10.0;
                    int ads_times = (int) (600/ads_per_time);

                    ads.setContent(content);
                    ads.setId(id);
                    ads.setUpdate_time(update_time);
                    ads.setAds_per_time(ads_per_time);//以显示屏可以一次显示10个字符为标准，每播放10个字符需要1秒
                    ads.setAds_times(ads_times);//计算10分钟内广告播放的次数
                    ads.setAdvertiser_id(advertiser_id);

                    listener.onSuccess();
                    return ads;
                }

                listener.onfailure("data format error");
                return null;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void doGet() throws IOException
    {
         request();
    }

    @Override
    public void run() {
        try {
            doGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface onResponseListener
    {
        void onSuccess();

        void onfailure(String reason);
    }
}
