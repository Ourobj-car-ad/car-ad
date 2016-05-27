package com.example.zhengsuren.olddriver;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;

        import com.amap.api.location.AMapLocation;
        import com.amap.api.location.AMapLocationClient;
        import com.amap.api.location.AMapLocationClientOption;
        import com.amap.api.location.AMapLocationListener;
        import com.amap.api.maps.AMap;
        import com.amap.api.maps.CameraUpdateFactory;
        import com.amap.api.maps.LocationSource;
        import com.amap.api.maps.MapView;
        import com.amap.api.maps.model.BitmapDescriptorFactory;
        import com.amap.api.maps.model.CircleOptions;
        import com.amap.api.maps.model.LatLng;
        import com.amap.api.maps.model.Marker;
        import com.amap.api.maps.model.MarkerOptions;
        import com.amap.api.maps.overlay.PoiOverlay;
        import com.amap.api.services.core.LatLonPoint;
        import com.amap.api.services.core.PoiItem;
        import com.amap.api.services.core.SuggestionCity;
        import com.amap.api.services.poisearch.PoiResult;
        import com.amap.api.services.poisearch.PoiSearch;

        import java.util.List;

public class Map extends AppCompatActivity
        implements LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener
{
    private Marker locationMarker; // 选择的点
    private AMap aMap;
    private MapView mMapView = null;
    private LatLng cenpt;
    private PoiSearch poiSearch;
    private PoiOverlay poiOverlay;// poi图层
    private List<PoiItem> poiItems;// poi数据
    private PoiItem mPoi;
    private boolean hasThisType = false;
    private String[] search = {"科教文化服务","医疗保健服务","购物服务","餐饮服务","汽车服务","风景名胜","公司企业","生活服务"} ;
    private static Types types;

    public AMapLocationClientOption mLocationOption = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        mMapView = (MapView) findViewById(R.id.map);
        AMapLocationClient mLocationClient = null;
        types = new Types();


        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(10000);       //单位毫秒
        mLocationClient.setLocationOption(mLocationOption);

        aMap = mMapView.getMap();
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
        mLocationClient.startLocation();

        /*
        locationMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.point4)))
                .position(new LatLng(lp.getLatitude(), lp.getLongitude())));
        locationMarker.showInfoWindow();
        */


    }
    protected void doSearchQuery(String para){
        PoiSearch.Query query = new PoiSearch.Query("", para, "上海市");
        query.setPageSize(8);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查第一页
        if (cenpt != null) {
            LatLonPoint lp = new LatLonPoint(cenpt.latitude, cenpt.longitude);
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 1500, true));
            // 设置搜索区域为以lp点为圆心，其周围1500米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {

                double latitude = amapLocation.getLatitude();
                double longitude = amapLocation.getLongitude();
                cenpt = new LatLng(latitude, longitude);
                aMap.clear();
                aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(cenpt));
                aMap.addCircle(new CircleOptions()
                        .center(new LatLng(latitude,
                                longitude)).radius(1500)
                        .strokeColor(Color.BLUE)
                        .fillColor(Color.argb(10, 0, 0, 255))
                        .strokeWidth(2));

                for (int count = 0;count < 8;count++)
                {
                    //检查周边都有哪些服务，并更新结果字符串
                    doSearchQuery(search[count]);
                    if (hasThisType)
                    {
                        types.setTypes(search[count]);
                        break;
                    }
                }

                String mtypes = types.getTypes();
                System.out.println(mtypes);
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void onPoiSearched(PoiResult result, int i) {
        if (i == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                //清理之前搜索结果的marker
                if (poiOverlay !=null) {
                    poiOverlay.removeFromMap();
                }
                if(!result.getPois().toString().equals("[]")){
                    poiOverlay = new PoiOverlay(aMap, result.getPois());
                    poiOverlay.addToMap();
                    hasThisType = true;

                    Log.i("Poi", result.getPois().toString());
                }
                else {
                    Log.i("Poi", "周围没有");
                }

            } else {
                Log.i("Poi", "搜索好像出了点问题");
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
