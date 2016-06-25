package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/6/25.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;


public class LoginSuccessActivityTest
        extends ActivityInstrumentationTestCase2<LoginSuccess> {

    public LoginSuccessActivityTest() {
        super(LoginSuccess.class);
    }

    private LoginSuccess mActivity;
    private TextView mview;
    private Context context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("username","abc@qq.com");
        bundle.putString("password","123456");
        bundle.putInt("earnings",10);
        i.putExtras(bundle);
        setActivityIntent(i);
        mActivity = getActivity();
        mview = (TextView) mActivity.findViewById(R.id.textView2);
    }

    @UiThreadTest
    public void testlogin_success_activity(){
        String earnings = "10";
        readExcel();
        assertEquals("New Text",mview.getText().toString());
    }

    public void readExcel() {
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
    }

}