package com.example.zhengsuren.olddriver;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by zhengsuren on 16/4/26.
 */
public class PersonActivity extends AppCompatActivity {

    private TextView username,realname,email1,phone,driverId,alipay,carnum;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_message);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();

        final String url = "http://139.129.132.60/api/login";
        final String email = bundle.getString("email");
        final String pwd = bundle.getString("pwd");

        username = (TextView) findViewById(R.id.info_username);
        realname = (TextView) findViewById(R.id.info_realname);
        email1 = (TextView) findViewById(R.id.info_email);
        phone = (TextView) findViewById(R.id.info_phone);
        driverId = (TextView) findViewById(R.id.info_driverId);
        alipay = (TextView) findViewById(R.id.info_alipay);
        carnum = (TextView) findViewById(R.id.info_carnum);
        context = this;

        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0: {
                        //完成主界面更新,拿到数据
                        UserInfo data = (UserInfo) msg.obj;
                        if (data != null){
                            if (data.getError()==0)
                            {
                                username.setText(data.getUsername());
                                realname.setText(data.getRealname());
                                email1.setText(data.getEmail());
                                phone.setText(data.getPhone());
                                driverId.setText(data.getDriverId());
                                alipay.setText(data.getAlipay());
                                carnum.setText(data.getCarnum());
                            }
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        };
        new HttpThread(url, email, pwd, mHandler, context).start();
    }
}
