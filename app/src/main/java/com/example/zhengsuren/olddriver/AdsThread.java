package com.example.zhengsuren.olddriver;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

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
    private Handler handler;
    private Context context;

    public AdsThread(String url,String id,Handler handler,Context context,String types)
    {
        this.url = url;
        this.id = id;
        this.handler = handler;
        this.context = context;
        this.types = types;
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

             //   System.out.println("the ads content is:~~~~~~~~" + content);
             //   System.out.println("the ads lenth is:~~~~~~~~~~" + ads_lenth);
             //   System.out.println("ads per time is:~~~~~~~~~" + ads.getAds_per_time());
              //  System.out.println("ads play " + ads.getAds_times() + " times in 10min!");

                return ads;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void callBack(String adId,String advertiserId,String userId)
    {
        String url = "http://139.129.132.60/api/addorder?price=5000&regionInfo=test" + "&adId=" + adId +
                "&advertiserId=" + advertiserId + "&userId=" + userId;

        try {
            URL HttpURL = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) HttpURL.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
