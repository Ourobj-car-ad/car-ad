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

public class ParseJsonTest extends InstrumentationTestCase {

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
    }

    public void testRegistThread() throws Throwable{
        // create  a signal to let us know when our task is done.
        Handler handler = new Handler();

        //final CountDownLatch signal = new CountDownLatch(1);

        String test = "test005";

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

    public void testAdsThread() throws Throwable{
        // create  a signal to let us know when our task is done.


        //final CountDownLatch signal = new CountDownLatch(1);

        String test = "abc";

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

    public List<String> read(String key) throws IOException {
        List<String> resultSet = new ArrayList<String>();

        File inputWorkbook = new File("/Users/zhengsuren/Desktop/unit_test.xlsx");
        if(inputWorkbook.exists()){
            Workbook w;
            try {
                w = Workbook.getWorkbook(inputWorkbook);
                // Get the first sheet
                Sheet sheet = w.getSheet(0);
                // Loop over column and lines
                for (int j = 0; j < sheet.getRows(); j++) {
                    Cell cell = sheet.getCell(0, j);
                    if(cell.getContents().equalsIgnoreCase(key)){
                        for (int i = 0; i < sheet.getColumns(); i++) {
                            Cell cel = sheet.getCell(i, j);
                            resultSet.add(cel.getContents());
                        }
                    }
                    continue;
                }
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            resultSet.add("File not found..!");
        }
        if(resultSet.size()==0){
            resultSet.add("Data not found..!");
        }
        return resultSet;
    }

    public void readExcel() {
        try {
            InputStream is = new FileInputStream("/Users/zhengsuren/test.xls");
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
    }
}