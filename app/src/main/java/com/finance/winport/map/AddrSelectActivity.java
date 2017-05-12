package com.finance.winport.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.finance.winport.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddrSelectActivity extends Activity implements OnItemClickListener<PoiInfo>, MyLocation.XLocationListener {
    public static final int ACTIVITY_REQUEST_CODE_ADDR_SELECT = 10;

    @BindView(R.id.bmapView)
    TextureMapView mMapView;

    BaiduMap mBaiduMap = null;
    // 地理编码
    GeoCoder mGeoCoder = null;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
//    @BindView(R.id.btn_back)
//    Button btnBack;

    private MyLocation myLocation;

    List<PoiInfo> mInfoList = new ArrayList<PoiInfo>();
    AddrSelectItemAdapter adapter;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btn_locate)
    ImageButton btnLocate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addr_select);
        ButterKnife.bind(this);
        initView();
        initMap();
        initLocalLocation();
    }

    private void initView() {
        tvFocusHouse.setText("选择地址");
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddrSelectItemAdapter(this, mInfoList, this);
        rv.setAdapter(adapter);
    }

    private void initMap() {
        mMapView = (TextureMapView) findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);

        mBaiduMap = mMapView.getMap();
        UiSettings settings = mBaiduMap.getUiSettings();
        settings.setAllGesturesEnabled(false);
        settings.setScrollGesturesEnabled(true);
        settings.setZoomGesturesEnabled(true);

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
//                Logger.i("" + mapStatus);
                // 发起反地理编码检索
                mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption()).location(new LatLng(mapStatus.target.latitude, mapStatus.target.longitude)));
            }
        });

        // 地理编码
        mGeoCoder = GeoCoder.newInstance();
        mGeoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有找到检索结果
//                    Logger.e("没有找到检索结果");
                } else {
                    // 获取反向地理编码结果
                    // 当前位置信息
                    PoiInfo mCurentInfo = new PoiInfo();
                    mCurentInfo.address = result.getAddress();
                    mCurentInfo.location = result.getLocation();
                    mCurentInfo.name = "[位置]";

                    mInfoList.clear();

                    mInfoList.add(mCurentInfo);

                    // 将周边信息加入表
                    if (result.getPoiList() != null) {
                        mInfoList.addAll(result.getPoiList());
                    }

                    Log.i("map", "#############");
                    for (PoiInfo info : mInfoList) {
                        Log.i("map", info.name + " , " + info.address);
                    }
                    Log.i("map", "#############");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            rv.smoothScrollToPosition(0);
                        }
                    });
                }
            }
        });
    }

    private void initLocalLocation() {
        MapUtil.setDefaultLocation(mBaiduMap);

        myLocation = new MyLocation(this);

        myLocation.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onClick(int position, PoiInfo data) {
//        Logger.i(position + ", " + data);
        Intent mIntent = new Intent();
        mIntent.putExtra("addr", "" + data.address);
        setResult(ACTIVITY_REQUEST_CODE_ADDR_SELECT, mIntent);
        finish();

    }

    @Override
    public void result(boolean result, BDLocation location) {
        if (result) {
//            Logger.e("定位成功！！！");
            MapUtil.setMyLocation(mBaiduMap, location);
            if (mInfoList.size() == 0) {
                // 发起反地理编码检索
                mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption()).location(new LatLng(location.getLatitude(), location.getLongitude())));
            }
        } else {
//            Logger.e("定位失败！！！");
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_locate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_locate:
                myLocation.start(this);
                break;
        }
    }
}
