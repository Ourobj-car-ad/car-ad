package com.example.zhengsuren.olddriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by zhengsuren on 16/5/18.
 */
public class CallBackThread extends Thread {
    private String adId,advertiserId,userId;

    public CallBackThread(String adId,String advertiserId,String userId)
    {
        this.adId = adId;
        this.advertiserId = advertiserId;
        this.userId = userId;
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

            System.out.println("数据回传成功～～～～～～～！！");

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
        callBack(adId,advertiserId,userId);
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
