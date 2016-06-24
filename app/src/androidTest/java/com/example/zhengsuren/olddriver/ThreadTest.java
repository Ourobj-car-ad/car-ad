package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/6/24.
 */
import java.util.concurrent.CountDownLatch;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.test.InstrumentationTestCase;
import android.util.Log;

import static org.junit.Assert.fail;

public class ThreadTest extends InstrumentationTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // 异步test
   /* public void testLoginThread() throws Throwable{
        // create  a signal to let us know when our task is done.
        Looper.prepare();
        Handler handler = new Handler();

        //final CountDownLatch signal = new CountDownLatch(1);

        LoginThread loginThread = new LoginThread("abc@qq.com", "123456", handler, new LoginThread.onResponseListener() {
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
    }*/

    public void testRegistThread() throws Throwable{
        // create  a signal to let us know when our task is done.
        Looper.prepare();
        Handler handler = new Handler();

        //final CountDownLatch signal = new CountDownLatch(1);

        String test = "test002";

        RegistThread registThread = new RegistThread("http://139.129.132.60/api/sign", test, test, test, test, test, test, test,
                handler, new RegistThread.onResponseListener() {
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
    }

}