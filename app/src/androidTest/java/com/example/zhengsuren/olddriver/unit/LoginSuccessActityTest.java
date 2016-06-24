package com.example.zhengsuren.olddriver.unit;

/**
 * Created by zhengsuren on 16/6/25.
 */


import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.zhengsuren.olddriver.LoginSuccess;
import com.example.zhengsuren.olddriver.MainActivity;
import com.example.zhengsuren.olddriver.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginSuccessActityTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<LoginSuccess> mActivityRule = new ActivityTestRule<>(
            LoginSuccess.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }

    @Test
    public void LoginSuccessActivity() {
        //attempt login
        //onView(ViewMatchers.withId(R.id.button)).perform(click());
        onView(ViewMatchers.withId(R.id.id_menu)).perform(click());
    }
}
