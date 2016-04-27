package com.example.zhengsuren.olddriver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by zhengsuren on 16/4/5.
 */
public class LoginSuccess extends AppCompatActivity {

    public SlidingMenu mLeftMenu;
    private Context mContext;
    private RelativeLayout mRL,rl1,rl2,rl3,rl4,rl5;
    private boolean flag = false;
    private ImageButton bt1;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        mContext = this;
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        mRL = (RelativeLayout) findViewById(R.id.RL);
        rl1 = (RelativeLayout) findViewById(R.id.rl_ad1);
        rl2 = (RelativeLayout) findViewById(R.id.rl_ad2);
        rl3 = (RelativeLayout) findViewById(R.id.rl_ad3);
        rl4 = (RelativeLayout) findViewById(R.id.rl_ad4);
        rl5 = (RelativeLayout) findViewById(R.id.rl_ad5);

        bt1 = (ImageButton) findViewById(R.id.id_img1);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();

        final String url = bundle.getString("url");
        final String email = bundle.getString("email");
        final String pwd = bundle.getString("pwd");
        String adsUrl = "http://139.129.132.60/api/getad";

        new AdsThread(adsUrl,1).start();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PersonActivity.class);

                //用Bundle携带数据
                Bundle bundle=new Bundle();

                //传递name参数为tinyphp
                bundle.putString("url", url);
                bundle.putString("email",email);
                bundle.putString("pwd",pwd);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    public void toggleMenu(View view){
        mLeftMenu.toggle();
    }

    public void showDetail1(View view){
        if (false == flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;
        }
        else {
            mRL.setVisibility(View.GONE);
            //mRL.setAnimation();
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            rl4.setVisibility(View.VISIBLE);
            rl5.setVisibility(View.VISIBLE);
            flag = false;
        }
    }

    public void showDetail2(View view){
        if (false == flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;
        }
        else {
            mRL.setVisibility(View.GONE);
            //mRL.setAnimation();
            rl1.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            rl4.setVisibility(View.VISIBLE);
            rl5.setVisibility(View.VISIBLE);
            flag = false;
        }
    }

    public void showDetail3(View view){
        if (false == flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;
        }
        else {
            mRL.setVisibility(View.GONE);
            //mRL.setAnimation();
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.VISIBLE);
            rl4.setVisibility(View.VISIBLE);
            rl5.setVisibility(View.VISIBLE);
            flag = false;
        }
    }

    public void showDetail4(View view){
        if (false == flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;
        }
        else {
            mRL.setVisibility(View.GONE);
            //mRL.setAnimation();
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            rl5.setVisibility(View.VISIBLE);
            flag = false;
        }
    }

    public void showDetail5(View view){
        if (false == flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            flag = true;
        }
        else {
            mRL.setVisibility(View.GONE);
            //mRL.setAnimation();
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            rl4.setVisibility(View.VISIBLE);
            flag = false;
        }
    }
}
