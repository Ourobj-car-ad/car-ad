package com.example.zhengsuren.olddriver;

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


    public HttpThread2(String url,String username,String email,String password,String realname,String phone,
                       String carTravelCode,String carNum){
        this.url = url;
        this.username = username;
        this.password = password;
        this.email = email;
        this.realname = realname;
        this.phone = phone;
        this.carTravelCode = carTravelCode;
        this.carNum = carNum;
    }

    public void doGet() throws IOException {
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

            System.out.println("result:~~~ "+sb.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
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
