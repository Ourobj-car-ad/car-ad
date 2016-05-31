package com.example.zhengsuren.olddriver;

import android.app.Activity;
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
public class LoginSuccess extends Activity {

    public SlidingMenu mLeftMenu;
    private Context mContext;
    private boolean flag = false;
    private TextView textView;
    private ImageButton bt1,bt5,bt6;
    private static String id,email,pwd;

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

        id = bundle.getString("id");
        email = bundle.getString("email");
        pwd = bundle.getString("pwd");
        String earnings = bundle.getString("earnings");

        System.out.println("The user email is @@@~~!!!" + email);

        textView.setText("  ¥ "+earnings);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PersonActivity.class);

                //用Bundle携带数据
                Bundle bundle2=new Bundle();
                bundle2.putString("email",email);
                bundle2.putString("pwd",pwd);
                intent.putExtras(bundle2);

                //设定flag不让页面数据反复传递，防止用户退出重进时的数据混乱
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Map.class);

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

    //重写saveInstanceState方法，实现数据的临时保存
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("id",id);
        outState.putString("email",email);
        outState.putString("pwd",pwd);

        System.out.println("The user email is @@@~~!!!" + email);
    }
}
