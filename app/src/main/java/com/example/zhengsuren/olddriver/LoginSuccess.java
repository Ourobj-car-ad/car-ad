package com.example.zhengsuren.olddriver;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zhengsuren on 16/4/5.
 */
public class LoginSuccess extends AppCompatActivity {

    public SlidingMenu mLeftMenu;
    private Context mContext;
    private boolean flag = false;
    private TextView textView;
    private ImageButton bt1,bt5,bt6;
    //private String adsUrl = "http://139.129.132.60/api/getbytype";
    private static String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        mContext = this;
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);

        bt1 = (ImageButton) findViewById(R.id.id_img1);
        bt5 = (ImageButton) findViewById(R.id.id_img5);
        bt6 = (ImageButton) findViewById(R.id.id_img6);
        textView = (TextView) findViewById(R.id.textView2);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();

        final String id = bundle.getString("id");
        final String url = bundle.getString("url");
        final String email = bundle.getString("email");
        final String pwd = bundle.getString("pwd");
        String earnings = bundle.getString("earnings");

        textView.setText("  "+earnings+" RMB");

        this.id = id; //将局部变量的值传给静态全局变量

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PersonActivity.class);

                //用Bundle携带数据
                Bundle bundle=new Bundle();

                bundle.putString("url", url);
                bundle.putString("email",email);
                bundle.putString("pwd",pwd);
                intent.putExtras(bundle);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Map.class);
                Bundle bundle=new Bundle();

                //传递name参数为tinyphp
                bundle.putString("url", url);
                bundle.putString("id",id);
                bundle.putString("email",email);
                bundle.putString("pwd", pwd);
                intent.putExtras(bundle);
                //设定flag不让页面数据反复传递，防止用户退出重进时的数据混乱
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SettingActivity.class);

                startActivity(intent);
            }
        });
    }

    public void toggleMenu(View view){
        mLeftMenu.toggle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new LogoffThread(id).start();
    }
}
