package com.finance.winport.map;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.view.BottomTabView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 演示poi搜索功能
 */
public class PoiSearchRoundActivity extends BaseActivity implements
        OnGetPoiSearchResultListener, BottomTabView.OnTabSelectedListener {

    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.tabView)
    BottomTabView tabView;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    private PoiSearch mPoiSearch = null;
    private BaiduMap mBaiduMap = null;
    private List<String> suggest;
    private String centerAddress;
    /**
     * 搜索关键字输入窗口
     */
//    private EditText editCity = null;
//    private AutoCompleteTextView keyWorldsView = null;
//    private ArrayAdapter<String> sugAdapter = null;
//    private int loadIndex = 0;

    LatLng center;
    int radius = 2000;
    LatLng southwest = new LatLng(39.92235, 116.380338);
    LatLng northeast = new LatLng(39.947246, 116.414977);
    LatLngBounds searchbound = new LatLngBounds.Builder().include(southwest).include(northeast).build();

    int searchType = 0;  // 搜索的类型，在显示时区分
    private LocationClient locationClient = null;
    private Boolean isFirseLoc = true;//是否首次定位
    private LatLng ll;
    private int type;
    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || mapView == null)
                return;
//            MyLocationData locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius())
//                    .direction(100).latitude(bdLocation.getLatitude()).longitude(bdLocation.getLongitude()).build();
//            mapView.getMap().setMyLocationData(locData);
            if (isFirseLoc) {
                isFirseLoc = false;
                ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
//                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);
//                mapView.getMap().animateMapStatus(u);
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_map);
        ButterKnife.bind(this);
        getBundle();
        mBaiduMap = mapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMaxAndMinZoomLevel(21, 18);
        mapView.showZoomControls(true);
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        tabView.setOnTabSelectedListener(this);
        tabView.setTabResIds(new int[]{R.drawable.selector_bottom_tab_food
                , R.drawable.selector_bottom_tab_shopping, R.drawable
                .selector_bottom_tab_hotel, R.drawable.selector_bottom_tab_community, R.drawable.selector_bottom_tab_school, R.drawable.selector_bottom_tab_traffic});
        tvFocusHouse.setText("商铺位置");
//        tvFocusRight.setText("导航");
        tvFocusRight.setVisibility(View.GONE);

        locationClient = new LocationClient(this);
//        locationClient.registerLocationListener(myListener);
//        setLocationOption();
//        locationClient.start();
//        locationClient.requestLocation();
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

                switch (type) {
                    case 0:
                        type = 0;
                        searchService("餐饮");
                        break;
                    case 1:
                        type = 1;
                        searchService("购物");
                        break;
                    case 2:
                        type = 2;
                        searchService("酒店");
                        break;
                    case 3:
                        type = 3;
                        searchService("小区");
                        break;
                    case 4:
                        type = 4;
                        searchService("学校");
                        break;
                    case 5:
                        type = 5;
                        searchService("公交站");
                        break;
                }
            }
        });

    }

    /**
     * 设置定位参数
     */
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);//打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setScanSpan(1000);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedAddress(true);
        option.setNeedDeviceDirect(true);
        locationClient.setLocOption(option);
    }

    private void getBundle() {
        Intent intent = getIntent();
        if (intent != null) {
            center = new LatLng(intent.getDoubleExtra("lat", 0), intent.getDoubleExtra("lon", 0));

            centerAddress = intent.getStringExtra("address");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        showNearbyArea(center, radius);
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                searchService("餐饮");
            }
        }, 1000);

        mapView.onResume();

    }

    @Override
    protected void onDestroy() {
        mPoiSearch.destroy();
        super.onDestroy();
        mapView.onDestroy();
        mapView = null;
        locationClient.unRegisterLocationListener(myListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    /**
     * 响应周边搜索按钮点击事件
     *
     * @param
     */
    public void searchNearbyProcess() {
        searchType = 2;
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword("医院").sortType(PoiSortType.distance_from_near_to_far).location(center)
                .radius(radius).pageNum(1);
        mPoiSearch.searchNearby(nearbySearchOption);
    }


    /**
     * 获取POI搜索结果，包括searchInCity，searchNearby，searchInBound返回的搜索结果
     *
     * @param result
     */
    public void onGetPoiResult(PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Toast.makeText(PoiSearchRoundActivity.this, "未找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        } else if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
            String strInfo = "在";
            for (CityInfo cityInfo : result.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }
            strInfo += "找到结果";
            Toast.makeText(PoiSearchRoundActivity.this, strInfo, Toast.LENGTH_LONG)
                    .show();
        } else {
            mBaiduMap.clear();
            BitmapDescriptor centerBitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.map_round_icon);
            MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap).anchor(0.5f,0.5f);
            mBaiduMap.addOverlay(ooMarker);
            View view = LayoutInflater.from(PoiSearchRoundActivity.this).inflate(R.layout.map_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_msg);
            tv.setText(centerAddress);
            tv.setTextColor(Color.parseColor("#666666"));
            tv.setBackgroundResource(R.drawable.map_bg_round);
            BitmapDescriptor centerBitmap1 = BitmapDescriptorFactory
                    .fromView(view);
            MarkerOptions ooMarker1 = new MarkerOptions().position(center).icon(centerBitmap1).anchor(0.5f,1f);
            mBaiduMap.addOverlay(ooMarker1);
            PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
            mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result, type);
            overlay.addToMap();
//            overlay.zoomToSpan();

//            switch (searchType) {
//                case 2:
//                    showNearbyArea(center, radius);
//                    break;
//                case 3:
//                    showBound(searchbound);
//                    break;
//                default:
//                    break;
//            }

            return;
        }
    }

    /**
     * 获取POI详情搜索结果，得到searchPoiDetail返回的搜索结果
     *
     * @param result
     */
    public void onGetPoiDetailResult(PoiDetailResult result) {
        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(PoiSearchRoundActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
                    .show();
        } else {
//            Toast.makeText(PoiSearchRoundActivity.this, result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT)
//                    .show();


//            TextView button = new TextView(PoiSearchRoundActivity.this);
//            button.setBackgroundResource(R.drawable.map_bg_item_select);
//            button.setText(result.getAddress()+result.getName());

            try{

                View view = LayoutInflater.from(PoiSearchRoundActivity.this).inflate(R.layout.map_item, null);
                TextView tv = (TextView) view.findViewById(R.id.tv_msg);
                tv.setText(result.getName());
                tv.setBackgroundResource(R.drawable.map_bg_item_select);
                if(view!=null&&result!=null&&result.getLocation()!=null){

                    InfoWindow mInfiWindow = new InfoWindow(view, result.getLocation(), -50);
                    mBaiduMap.showInfoWindow(mInfiWindow);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void onTabSelected(int index) {
        switch (index) {
            case 0:
                type = 0;
                searchService("餐饮");
                break;
            case 1:
                type = 1;
                searchService("购物");
                break;
            case 2:
                type = 2;
                searchService("酒店");
                break;
            case 3:
                type = 3;
                searchService("小区");
                break;
            case 4:
                type = 4;
                searchService("学校");
                break;
            case 5:
                type = 5;
                searchService("公交站");
                break;
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                startNavi();
                break;
        }
    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onClick() {
//        finish();
//    }


    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));
            // }



            return true;
        }
    }

    /**
     * 对周边检索的范围进行绘制
     *
     * @param center
     * @param radius
     */
    public void showNearbyArea(LatLng center, int radius) {
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(center)
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        BitmapDescriptor centerBitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.map_round_icon);
        MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap).anchor(0.5f,0.5f);
        mBaiduMap.addOverlay(ooMarker);
        View view = LayoutInflater.from(PoiSearchRoundActivity.this).inflate(R.layout.map_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_msg);
        tv.setText(centerAddress);
        tv.setTextColor(Color.parseColor("#666666"));
        tv.setBackgroundResource(R.drawable.map_bg_round);
        BitmapDescriptor centerBitmap1 = BitmapDescriptorFactory
                .fromView(view);
        MarkerOptions ooMarker1 = new MarkerOptions().position(center).icon(centerBitmap1).anchor(0.5f,1f);
        mBaiduMap.addOverlay(ooMarker1);

//        OverlayOptions ooCircle = new CircleOptions().fillColor(0xCCCCCC00)
//                .center(center).stroke(new Stroke(5, 0xFFFF00FF))
//                .radius(radius);
//        mBaiduMap.addOverlay(ooCircle);
    }

    /**
     * 对区域检索的范围进行绘制
     *
     * @param bounds
     */
    public void showBound(LatLngBounds bounds) {
        BitmapDescriptor bdGround = BitmapDescriptorFactory
                .fromResource(R.mipmap.map_icon_location_center);

        OverlayOptions ooGround = new GroundOverlayOptions()
                .positionFromBounds(bounds).image(bdGround).transparency(0.8f);
        mBaiduMap.addOverlay(ooGround);

        MapStatusUpdate u = MapStatusUpdateFactory
                .newLatLng(bounds.getCenter());
        mBaiduMap.setMapStatus(u);

        bdGround.recycle();
    }


    public void searchService(String keyword) {
//        MapStatus mMapStatus = new MapStatus.Builder()
//                .target(center)
//                .zoom(18)
//                .build();
//        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//        mBaiduMap.setMapStatus(mMapStatusUpdate);
//        BitmapDescriptor centerBitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.map_icon_location_center);
//        MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap);
//        mBaiduMap.addOverlay(ooMarker);
//        BitmapDescriptor centerBitmap1 = BitmapDescriptorFactory
//                .fromResource(R.mipmap.map_icon_hotel_selected);
//        MarkerOptions ooMarker1 = new MarkerOptions().position(mBaiduMap.getMapStatus().bound.northeast).icon(centerBitmap1);
//        mBaiduMap.addOverlay(ooMarker1);
//        BitmapDescriptor centerBitmap2 = BitmapDescriptorFactory
//                .fromResource(R.mipmap.map_icon_hotel_selected);
//        MarkerOptions ooMarker2 = new MarkerOptions().position(mBaiduMap.getMapStatus().bound.southwest).icon(centerBitmap2);
//        mBaiduMap.addOverlay(ooMarker2);
        searchType = 2;
        Log.i("kkkkkkkkkkkkk southwest", mBaiduMap.getMapStatus().bound.southwest.toString());
        Log.i("kkkkkkkkkkkkk southwest", mBaiduMap.getMapStatus().bound.northeast.toString());
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword(keyword).sortType(PoiSortType.distance_from_near_to_far).location(center)
                .radius(radius).pageNum(1).pageCapacity(50);
        PoiBoundSearchOption poiBoundSearchOption = new PoiBoundSearchOption().keyword(keyword).bound(mBaiduMap.getMapStatus().bound);
        mPoiSearch.searchInBound(poiBoundSearchOption);
//        mPoiSearch.searchNearby(nearbySearchOption);
    }

    /**
     * 开始导航
     */
    public void startNavi() {
//        int lat = (int) (mLat1 * 1E6);
//        int lon = (int) (mLon1 * 1E6);
//        GeoPoint pt1 = new GeoPoint(lat, lon);
//        lat = (int) (mLat2 * 1E6);
//        lon = (int) (mLon2 * 1E6);
//        GeoPoint pt2 = new GeoPoint(lat, lon);

//        LatLng pt1 = new LatLng(mLat1, mLon1);
//        LatLng pt2 = new LatLng(mLat2, mLon2);
        // 构建 导航参数
//        NaviParaOption para = new NaviParaOption();
//        para.startPoint(ll);
//        para.startName("从这里开始");
//        para.endPoint(center);
//        para.endName("到这里结束");
//
//        try {
//
//            BaiduMapNavigation.openBaiduMapNavi(para, this);
//
//        } catch (BaiduMapAppNotSupportNaviException e) {
//            e.printStackTrace();
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
//            builder.setTitle("提示");
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//
//
//            builder.create().show();
//        }

//        Intent intent = null;
//        try {
//            intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068| name:从这里开始&destination=latlng:48.264642646862,111.95108518068&mode=driving&src=上海房产#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        startActivity(intent);


        //移动APP调起Android百度地图方式举例
        Intent intent = null;
        try {
            intent = Intent.getIntent("intent://map/geocoder?location=" + center.latitude + "," + center.longitude + "&src=上海房产#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            startActivity(intent); //启动调用
        } catch (Exception e) {

            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse("http://api.map.baidu.com/geocoder?location=" + center.latitude + "," + center.longitude + "&output=html&src=上海房产");
            intent.setData(content_url);
//            intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
            startActivity(intent);
            e.printStackTrace();
        }

    }


}
