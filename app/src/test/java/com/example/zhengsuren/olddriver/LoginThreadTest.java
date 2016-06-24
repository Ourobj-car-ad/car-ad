package com.example.zhengsuren.olddriver;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by zhengsuren on 16/6/24.
 */
public class LoginThreadTest {

    private LoginThread loginThread;
    private Handler handler;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDoGet() throws Exception {

    }

    @Test
    public void testThread() {

        handler = new Handler();

        //final CountDownLatch signal = new CountDownLatch(1);

        loginThread = new LoginThread("abc@qq.com", "123456", handler, new LoginThread.onResponseListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onfailure(String reason) {
                fail(reason);
            }

            @Override
            public void onExcute() {

            }

        }){
            @Override
            public void run() {
                loginThread.doGet();
            }
        };

        loginThread.start();

        try {
            loginThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}