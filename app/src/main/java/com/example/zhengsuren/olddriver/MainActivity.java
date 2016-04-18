package com.example.zhengsuren.olddriver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bt1;
    private TextView regist;
    private Context mContext;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button);
        regist = (TextView) findViewById(R.id.regist);
        mContext = this;
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://139.129.132.60/api/login";
                new HttpThread(url,username.getText().toString(),password.getText().toString()).start();
                Intent intent = new Intent(mContext,LoginSuccess.class);
                startActivity(intent);
            }
        });

        regist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RegistPage.class);
                startActivity(intent);
            }
        });
    }
}
