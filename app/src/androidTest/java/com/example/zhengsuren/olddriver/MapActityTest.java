package com.example.zhengsuren.olddriver;

/**
 * Created by zhengsuren on 16/6/25.
 */


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.zhengsuren.olddriver.Network.AdsThread;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class MapActityTest extends ActivityInstrumentationTestCase2<Map> {

    private Map mActivity;
    private AMap aMap;
    private String email = "abc@qq.com";
    private String pwd = "123456";
    private String id = "19";
    private Bundle bundle;
    public AMapLocationClientOption mLocationOption = null;


    public MapActityTest() {
        super(Map.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Intent i = new Intent();
        bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("username",email);
        bundle.putString("password",pwd);
        bundle.putInt("earnings",0);
        i.putExtras(bundle);
        setActivityIntent(i);
        mActivity = getActivity();
    }

    @UiThreadTest
    public void test_map_with_adsThread() {

        AdsThread adsThread = new AdsThread("http://139.129.132.60/api/getbytype",id,"科教",
                new AdsThread.onResponseListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onfailure(String reason) {
                Log.i("reason",reason);
                fail(reason);
            }
        });

        adsThread.start();

        try {
            adsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @UiThreadTest
    public void test_onLocationChange(){
        MapView mMapView = (MapView) mActivity.findViewById(R.id.map);
       // mActivity.onLocationChanged();
        AMapLocationClient mLocationClient = null;

        //mMapView.onCreate(bundle);
        mLocationClient = new AMapLocationClient(mActivity.getApplicationContext());
        mLocationClient.setLocationListener(mActivity);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(10000);       //单位毫秒
        mLocationClient.setLocationOption(mLocationOption);

        aMap = mMapView.getMap();
        aMap.setLocationSource(mActivity);
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
        mLocationClient.startLocation();
    }
}
