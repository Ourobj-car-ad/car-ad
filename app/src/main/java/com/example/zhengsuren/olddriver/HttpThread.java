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
public class HttpThread extends Thread {
    //调用用户登录接口，若用户输入的用户名和密码合法则向主线程返回服务器中的该用户信息
    //登录界面和用户信息显示界面调用了该线程

    private String url;
    private String username;
    private String password;
    private Handler mHandler;
    private Context context;

    public HttpThread(String username,String password,Handler handler,Context context){
        this.username = username;
        this.password = password;
        this.mHandler = handler;
        this.context = context;
    }

    private UserInfo request()
    {
        //拼接api的url地址并接收服务器返回的数据
        url = "http://139.129.132.60/api/login" + "?email=" + username + "&pwd=" + password;
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
            final String result = sb.toString();

            return parseJson(result);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    private UserInfo parseJson(String json){
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
                    UserInfo user = new UserInfo();
                    String id = data.getString("id");
                    String username = data.getString("name");
                    String email = data.getString("email");
                    String phone = data.getString("phone");
                    String carTraNum = data.getString("car_travel_code");
                    String driverId = data.getString("identity_number");
                    String realname = data.getString("real_name");
                    String alipay = data.getString("alipay");
                    String carnum = data.getString("car_code");
                    Double earnings = data.getDouble("earnings");

                    user.setId(id);
                    user.setAlipay(alipay);
                    user.setCarnum(carnum);
                    user.setEarnings(earnings);
                    user.setCartranum(carTraNum);
                    user.setEmail(email);
                    user.setRealname(realname);
                    user.setPhone(phone);
                    user.setUsername(username);
                    user.setDriverId(driverId);
                    user.setError(0);

                    return user;
                }

                return null;
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    public double doGet() throws IOException {
        UserInfo mUserInfo = request();

        if (mUserInfo == null)
        {
            //耗时操作，完成之后发送消息给Handler，完成UI更新；
            mHandler.sendEmptyMessage(1);
            //需要数据传递，用下面方法；
            Message msg = new Message();
            msg.obj = mUserInfo;//可以是基本类型，可以是对象，可以是List、map等；
            mHandler.sendMessage(msg);
            return 1;
        }
        else
        {
            //耗时操作，完成之后发送消息给Handler，完成UI更新；
            mHandler.sendEmptyMessage(0);
            //需要数据传递，用下面方法；
            Message msg = new Message();
            msg.obj = mUserInfo;//可以是基本类型，可以是对象，可以是List、map等；
            mHandler.sendMessage(msg);
            return 0;
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
