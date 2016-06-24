package com.example.zhengsuren.olddriver.unit;

/**
 * Created by zhengsuren on 16/6/25.
 */



        import org.junit.Before;
        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import android.support.test.espresso.matcher.ViewMatchers;
        import android.support.test.filters.LargeTest;
        import android.support.test.rule.ActivityTestRule;
        import android.support.test.runner.AndroidJUnit4;

        import com.example.zhengsuren.olddriver.MainActivity;
        import com.example.zhengsuren.olddriver.R;

        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.action.ViewActions.click;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActityTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "Espresso";
    }

    @Test
    public void MainActivity() {
        //attempt login
        onView(ViewMatchers.withId(R.id.button)).perform(click());
    }
}
