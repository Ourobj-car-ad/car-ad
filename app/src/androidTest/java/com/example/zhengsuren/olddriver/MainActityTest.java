package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/6/25.
 */



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityTestCase;
import android.test.InstrumentationTestCase;
import android.app.Instrumentation.ActivityResult;
import android.widget.TextView;

import com.example.zhengsuren.olddriver.MainActivity;
import com.example.zhengsuren.olddriver.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class MainActityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public MainActityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @UiThreadTest
    public void testmainActivity() {

        Instrumentation instrumentation = getInstrumentation();

        // Register we are interested in the authentication activiry...
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(LoginSuccess.class.getName(), null, false);

        String username = "abc@qq.com";
        String password = "123456";

        //attempt login
        // Type text into an EditText view, then close the soft keyboard
        onView(withId(R.id.username)).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.button)).perform(click());
        // Wait for it to start...
        Activity currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
        assertThat(currentActivity, is(notNullValue()));
    }


}
