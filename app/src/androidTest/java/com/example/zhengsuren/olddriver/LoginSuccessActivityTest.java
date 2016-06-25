package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/6/25.
 */


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.zhengsuren.olddriver.Network.LoginThread;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import jxl.Sheet;
import jxl.Workbook;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class LoginSuccessActivityTest
        extends ActivityInstrumentationTestCase2<LoginSuccess> {

    public LoginSuccessActivityTest() {
        super(LoginSuccess.class);
    }

    private LoginSuccess mActivity;
    private TextView mview;
    private Context context;
    private String email = "abc@qq.com";
    private String pwd = "123456";
    private Handler handler;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("username",email);
        bundle.putString("password",pwd);
        bundle.putInt("earnings",10);
        i.putExtras(bundle);
        setActivityIntent(i);
        mActivity = getActivity();
        mview = (TextView) mActivity.findViewById(R.id.textView2);
    }

    @UiThreadTest
    public void testlogin_success_activity(){
        //assertEquals("New Text",mview.getText().toString());
        Looper.prepare();
        handler = new Handler();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                new LoginThread(email, pwd, handler, new LoginThread.onResponseListener() {
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

                }).start();
            }
        };

        Timer timer = new Timer(true);
        timer.schedule(task,0,10000);
    }

    /*public void readExcel() {
        try {
            context = mActivity.getApplicationContext();
            InputStream is = new FileInputStream(context.getFilesDir() + "/res/test.xls");
            //Workbook book = Workbook.getWorkbook(new File("mnt/sdcard/test.xls"));
            Workbook book = Workbook.getWorkbook(is);
            int num = book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            StringBuffer txt = null;
            txt.append("the name of sheet is " + sheet.getName() + "\n");
            txt.append("total rows is " + Rows + "\n");
            txt.append("total cols is " + Cols + "\n");
            for (int i = 0; i < Cols; ++i) {
                for (int j = 0; j < Rows; ++j) {
                    // getCell(Col,Row)获得单元格的值
                    txt.append("contents:" + sheet.getCell(i, j).getContents() + "\n");
                }
            }

            System.out.println("Excel result:~~~~~~~~"+txt.toString());
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

}