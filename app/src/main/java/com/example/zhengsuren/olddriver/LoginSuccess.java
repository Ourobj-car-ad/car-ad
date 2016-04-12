package com.example.zhengsuren.olddriver;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zhengsuren on 16/4/5.
 */
public class LoginSuccess extends AppCompatActivity {

    public SlidingMenu mLeftMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
    }

    public void toggleMenu(View view){
        mLeftMenu.toggle();
    }
}
