package com.example.zhengsuren.olddriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by zhengsuren on 16/5/29.
 */
public class LogoffThread extends Thread
{
    private String userID;
    public LogoffThread(String id)
    {
        this.userID = id;
    }

    private void callBack(String userId)
    {
        String url = "http://139.129.132.60/api/exitwithuserid?id=" + userId;

        try {
            URL HttpURL = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) HttpURL.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            System.out.println("退出成功～～～～～～～！！");

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
        callBack(userID);
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
