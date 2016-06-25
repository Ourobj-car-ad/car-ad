package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/6/25.
 */


import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class RegistActivityTest extends ActivityInstrumentationTestCase2<RegistPage> {

    private RegistPage mActivity;

    public RegistActivityTest() {
        super(RegistPage.class);
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
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(MainActivity.class.getName(), null, false);

        String username = "abc668@qq.com";
        String password = "123456";
        String carNum = "666";
        String carTravelCode = "089991";
        String phone = "15317899683";
        String userID = "669991";
        String realname = "Mike";


        //attempt login
        // Type text into an EditText view, then close the soft keyboard
        onView(withId(R.id.r_email)).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.r_password)).perform(typeText(password),closeSoftKeyboard());
        onView(withId(R.id.r_carNum)).perform(typeText(carNum),closeSoftKeyboard());
        onView(withId(R.id.r_carTravelCode)).perform(typeText(carTravelCode),closeSoftKeyboard());
        onView(withId(R.id.r_phone)).perform(typeText(phone),closeSoftKeyboard());
        onView(withId(R.id.r_userID)).perform(typeText(userID),closeSoftKeyboard());
        onView(withId(R.id.r_realname)).perform(typeText(realname),closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.regist_button)).perform(click());
        // Wait for it to start...
        Activity currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
        assertThat(currentActivity, is(notNullValue()));
    }


}
