package com.example.zhengsuren.olddriver;

import android.app.AlertDialog;
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
import java.net.URL;

/**
 * Created by zhengsuren on 16/4/13.
 */
public class HttpThread2 extends Thread {

    private String url;
    private String username;
    private String email;
    private String password;
    private String realname;
    private String phone;
    private String carTravelCode;
    private String carNum;
    private Handler mHandler = new Handler();


    public HttpThread2(String url,String username,String email,String password,String realname,String phone,
                       String carTravelCode,String carNum,Handler handler){
        this.url = url;
        this.username = username;
        this.password = password;
        this.email = email;
        this.realname = realname;
        this.phone = phone;
        this.carTravelCode = carTravelCode;
        this.carNum = carNum;
        this.mHandler = handler;
    }

    private boolean parseJson(String json){
        //解析从服务器返回的用户数据
        try {
            JSONObject object = new JSONObject(json);
            int errno = object.getInt("errno");
            String errmsg = object.getString("errmsg");
            if (0 == errno && errmsg.isEmpty())
            {
                JSONObject data = object.getJSONObject("data");
                if (data.length() != 0)
                {
                    String check_type = data.getString("type");
                    if (check_type == "add")
                    {
                        return true;
                    }
                }
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return false;
    }

    public void doGet() throws IOException {

        url = url + "?name=" + username + "&pwd=" + password + "&email=" + email + "&realName=" + realname +
                "&phone=" + phone + "&carTravelCode=" + carTravelCode + "&carCode=" + carNum + "&city=shanghai" + "&alipay=123";

        boolean result = false;

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

            result = parseJson(sb.toString());

            System.out.println("result:~~~ "+sb.toString() + " ---" + result);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        if (!result)
        {
            //注册失败
            mHandler.sendEmptyMessage(1);
            //需要数据传递，用下面方法；
            Message msg = new Message();
            mHandler.sendMessage(msg);
        }
        else if (result)
        {
            //注册成功
            mHandler.sendEmptyMessage(0);
            //需要数据传递，用下面方法；
            Message msg = new Message();
            mHandler.sendMessage(msg);
        }
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
