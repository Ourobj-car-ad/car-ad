package com.example.zhengsuren.olddriver.unit;

/**
 * Created by zhengsuren on 16/6/24.
 */

import android.os.Handler;
import android.os.Looper;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.example.zhengsuren.olddriver.Network.AdsThread;
import com.example.zhengsuren.olddriver.Network.LoginThread;
import com.example.zhengsuren.olddriver.Network.RegistThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import static org.hamcrest.Matchers.equalTo;


import static org.junit.Assert.fail;

public class ThreadTest extends InstrumentationTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // 异步test
    public void testLoginThread() throws Throwable{
        // create  a signal to let us know when our task is done.
        Looper.prepare();
        Handler handler = new Handler();

        //final CountDownLatch signal = new CountDownLatch(1);
        String username = "abc@qq.com";
        String password = "123456";

        LoginThread loginThread = new LoginThread( username, password, handler, new LoginThread.onResponseListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onfailure(String reason) {
                Log.i("reason",reason);
                fail(reason);
            }

            @Override
            public void onExcute() {

            }
        });

        loginThread.start();

        try {
            loginThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   /* public void testRegistThread() throws Throwable{
        // create  a signal to let us know when our task is done.
        Handler handler = new Handler();

        //final CountDownLatch signal = new CountDownLatch(1);

        String username = "130";
        String password = "130";
        String email = "abc1@qq.com";
        String realname = "130";
        String phone = "13568682718";
        String carTravalCode = "000007";
        String carNum = "000007";


        RegistThread registThread = new RegistThread("http://139.129.132.60/api/sign", username, email, password,
                realname, phone, carTravalCode, carNum, handler, new RegistThread.onResponseListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onfailure(String reason) {
                Log.i("reason",reason);
                fail(reason);
            }
        });

        registThread.start();

        try {
            registThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public void testAdsThread() throws Throwable{
        // create  a signal to let us know when our task is done.


        //final CountDownLatch signal = new CountDownLatch(1);

        int test = 19;

        AdsThread adsThread = new AdsThread("http://139.129.132.60/api/getbytype",test,"ha",new AdsThread.onResponseListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onfailure(String reason) {
                Log.i("reason",reason);
                fail(reason);
            }
        });

        adsThread.start();

        try {
            adsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}