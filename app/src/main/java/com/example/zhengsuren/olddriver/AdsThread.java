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
    private int id;
    private Handler handler = new Handler();
    private Context context;

    public AdsThread(String url,int id,Handler handler,Context context)
    {
        this.url = url;
        this.id = id;
        this.handler = handler;
        this.context = context;
    }

    private AdsInfo request() {
        url = url + "?id=" + id ;
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
            System.out.println("result:~~~ " + sb.toString());

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

                ads.setContent(content);
                ads.setId(id);
                ads.setUpdate_time(update_time);

                System.out.println("the ads content is:~~~~~~~~" + content);
                return ads;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void doGet() throws IOException
    {
         AdsInfo adsInfo = request();
         if (adsInfo != null)
         {
             //耗时操作，完成之后发送消息给Handler，完成UI更新；
             handler.sendEmptyMessage(0);
             //需要数据传递，用下面方法；
             Message msg =new Message();
             msg.obj = adsInfo;//可以是基本类型，可以是对象，可以是List、map等；
             handler.sendMessage(msg);
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
