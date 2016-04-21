package com.example.zhengsuren.olddriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by zhengsuren on 16/4/5.
 */
public class LoginSuccess extends AppCompatActivity {

    public SlidingMenu mLeftMenu;
    private RelativeLayout mRL,rl1,rl2,rl3,rl4,rl5;
    private boolean flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        mRL = (RelativeLayout) findViewById(R.id.RL);
        rl1 = (RelativeLayout) findViewById(R.id.rl_ad1);
        rl2 = (RelativeLayout) findViewById(R.id.rl_ad2);
        rl3 = (RelativeLayout) findViewById(R.id.rl_ad3);
        rl4 = (RelativeLayout) findViewById(R.id.rl_ad4);
        rl5 = (RelativeLayout) findViewById(R.id.rl_ad5);
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
