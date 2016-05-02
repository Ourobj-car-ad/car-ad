package com.example.zhengsuren.olddriver;

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
    private RelativeLayout mRL,rl1,rl2,rl3,rl4,rl5;
    private TextView tv1,tv2,tv3,tv4,tv5,tvm;
    private boolean flag = false;
    private ImageButton bt1;
    private String adsUrl = "http://139.129.132.60/api/getad";

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

        tv1 = (TextView) findViewById(R.id.ad_title1);
        tv2 = (TextView) findViewById(R.id.ad_title2);
        tv3 = (TextView) findViewById(R.id.ad_title3);
        tv4 = (TextView) findViewById(R.id.ad_title4);
        tv5 = (TextView) findViewById(R.id.ad_title5);
        tvm = (TextView) findViewById(R.id.ad_content);

        bt1 = (ImageButton) findViewById(R.id.id_img1);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();

        final String url = bundle.getString("url");
        final String email = bundle.getString("email");
        final String pwd = bundle.getString("pwd");
        //String adsUrl = "http://139.129.132.60/api/getad";

        for (int i=1;i<6;i++)
        {
            if (1 == i)
            {
                 Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 0: {
                                //完成主界面更新,拿到数据
                                AdsInfo data = (AdsInfo) msg.obj;
                                if (data != null){
                                    tv1.setText(data.getUpdate_time());
                                   // tvm.setText(data.getContent());
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                };
                new AdsThread(adsUrl,i,handler,mContext).start();
            }
            if (2 == i)
            {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 0: {
                                //完成主界面更新,拿到数据
                                AdsInfo data = (AdsInfo) msg.obj;
                                if (data != null){
                                    tv2.setText(data.getUpdate_time());
                                   // tvm.setText(data.getContent());
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                };
                new AdsThread(adsUrl,i,handler,mContext).start();
            }
            if (3 == i)
            {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 0: {
                                //完成主界面更新,拿到数据
                                AdsInfo data = (AdsInfo) msg.obj;
                                if (data != null){
                                    tv3.setText(data.getUpdate_time());
                                   // tvm.setText(data.getContent());
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                };
                new AdsThread(adsUrl,i,handler,mContext).start();
            }
            if (4 == i)
            {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 0: {
                                //完成主界面更新,拿到数据
                                AdsInfo data = (AdsInfo) msg.obj;
                                if (data != null){
                                    tv4.setText(data.getUpdate_time());
                                   // tvm.setText(data.getContent());
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                };
                new AdsThread(adsUrl,i,handler,mContext).start();
            }
            if (5 == i)
            {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 0: {
                                //完成主界面更新,拿到数据
                                AdsInfo data = (AdsInfo) msg.obj;
                                if (data != null){
                                    tv5.setText(data.getUpdate_time());
                                   // tvm.setText(data.getContent());
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                };
                new AdsThread(adsUrl,i,handler,mContext).start();
            }
        }

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
        if (!flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0: {
                            //完成主界面更新,拿到数据
                            AdsInfo data = (AdsInfo) msg.obj;
                            if (data != null){
                                tvm.setText(data.getContent());
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            };
            new AdsThread(adsUrl,1,handler,mContext).start();
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
        if (!flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0: {
                            //完成主界面更新,拿到数据
                            AdsInfo data = (AdsInfo) msg.obj;
                            if (data != null){
                                tvm.setText(data.getContent());
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            };
            new AdsThread(adsUrl,2,handler,mContext).start();
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
        if (!flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0: {
                            //完成主界面更新,拿到数据
                            AdsInfo data = (AdsInfo) msg.obj;
                            if (data != null){
                                tvm.setText(data.getContent());
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            };
            new AdsThread(adsUrl,3,handler,mContext).start();
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
        if (!flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl5.setVisibility(View.GONE);
            flag = true;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0: {
                            //完成主界面更新,拿到数据
                            AdsInfo data = (AdsInfo) msg.obj;
                            if (data != null){
                                tvm.setText(data.getContent());
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            };
            new AdsThread(adsUrl,4,handler,mContext).start();
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
        if (!flag)
        {
            mRL.setVisibility(View.VISIBLE);
            //mRL.setAnimation();
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            flag = true;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0: {
                            //完成主界面更新,拿到数据
                            AdsInfo data = (AdsInfo) msg.obj;
                            if (data != null){
                                tvm.setText(data.getContent());
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            };
            new AdsThread(adsUrl,5,handler,mContext).start();
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
