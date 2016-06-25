package com.example.zhengsuren.olddriver.Network;

import android.os.Handler;

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
 * Created by zhengsuren on 16/4/13.
 */
public class RegistThread extends Thread {

    private String url;
    private String username;
    private String email;
    private String password;
    private String realname;
    private String phone;
    private String carTravelCode;
    private String carNum;
    private Handler mHandler = new Handler();
    private onResponseListener listener;
    private static boolean result = false;



    public RegistThread(String url, String username, String email, String password, String realname, String phone,
                        String carTravelCode, String carNum, Handler handler,onResponseListener listener){
        this.url = url;
        this.username = username;
        this.password = password;
        this.email = email;
        this.realname = realname;
        this.phone = phone;
        this.carTravelCode = carTravelCode;
        this.carNum = carNum;
        this.mHandler = handler;
        this.listener = listener;
    }

    private void request()
    {
        url = url + "?name=" + username + "&pwd=" + password + "&email=" + email + "&realName=" + realname +
                "&phone=" + phone + "&carTravelCode=" + carTravelCode + "&carCode=" + carNum + "&city=shanghai" + "&alipay=123";

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

            parseJson(sb.toString(),listener);

            if (!result)
            {
                //注册失败
                mHandler.sendEmptyMessage(1);
                listener.onfailure("email is exist");
            }
            else if (result)
            {
                //注册成功
                mHandler.sendEmptyMessage(0);
                return;
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listener.onfailure("can not access to the server");
    }

    private void parseJson(String json,onResponseListener listener){
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
                    if (check_type.equals("add"))
                    {
                        result = true;
                        listener.onSuccess();
                        return ;
                    }
                }

                listener.onfailure("the email already exist");
                return ;
            }

            listener.onfailure("data format error");
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        result = false;
    }

    public void doGet(){
        request();
    }

    @Override
    public void run() {
        doGet();
    }

    public interface onResponseListener
    {
        void onSuccess();

        void onfailure(String reason);
    }
}
