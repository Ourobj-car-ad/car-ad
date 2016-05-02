package com.example.zhengsuren.olddriver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zhengsuren on 16/4/13.
 */
public class RegistPage extends AppCompatActivity {

    private Button bt2;
    private Context mContext;
    private EditText r_userID,r_password,r_email,r_realname,r_phone,r_carNum,r_carTravelCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_page);

        bt2 = (Button) findViewById(R.id.regist_button);
        mContext = this;
        r_carNum = (EditText) findViewById(R.id.r_carNum);
        r_carTravelCode = (EditText) findViewById(R.id.r_carTravelCode);
        r_email = (EditText) findViewById(R.id.r_email);
        r_password = (EditText) findViewById(R.id.r_password);
        r_phone = (EditText) findViewById(R.id.r_phone);
        r_userID = (EditText) findViewById(R.id.r_userID);
        r_realname = (EditText) findViewById(R.id.r_realname);

        bt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MainActivity.class);
                startActivity(intent);
                String url = "http://139.129.132.60/api/sign";
                new HttpThread2(url,r_userID.getText().toString(),r_email.getText().toString(),r_password.getText().toString(),
                        r_realname.getText().toString(),r_phone.getText().toString(), r_carTravelCode.getText().toString(),
                        r_carNum.getText().toString()).start();

            }
        });
    }
}
