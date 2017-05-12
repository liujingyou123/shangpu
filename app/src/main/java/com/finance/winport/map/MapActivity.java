package com.finance.winport.map;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MapActivity extends BaseActivity implements MyLocation.XLocationListener {

    public static final int TYPE_RANGE = 0;
    public static final int TYPE_ITEM_SHOP = 1;
//    public static final int TYPE_ITEM_INFO = 2;
    public static final int TYPE_ITEM_SELECTED = 3;

    @BindView(R.id.mapView)
    TextureMapView mMapView;
    @BindView(R.id.btn_locate)
    ImageButton btnLocate;

    private BaiduMap mBaiduMap;
    private MyLocation myLocation;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        loadingDialog = new LoadingDialog(this);

        initMap();
    }

    private void initMap() {

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
                Log.i("mapstatus","" + mapStatus.toString());
                mBaiduMap.clear();

                if (mapStatus.zoom <= 14) {
                    addRange(new LatLng(31.181564, 121.440023), "徐家汇\n200间");
                }

                if (mapStatus.zoom >= 16) {
                    addItem(TYPE_ITEM_SHOP, new LatLng(31.310116, 121.498283), "财大科技园");
                    addItem(TYPE_ITEM_SHOP, new LatLng(31.309165, 121.496214), "永和大王");
                    addItem(TYPE_ITEM_SHOP, new LatLng(31.307385, 121.499802), "KFC");
                }
            }
        });

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int type = marker.getExtraInfo().getInt("type", -1);

//                Logger.i("marker type : " + type);

                switch (type) {
                    case TYPE_ITEM_SHOP:
                        showShopDetail(marker, type);
                        break;
                    case TYPE_RANGE:
                        showRangeDetail();
                        break;
                    default:
//                        Logger.e("unknow type !!!!");
                        break;
                }

                return false;
            }
        });

        MapUtil.setDefaultLocation(mBaiduMap);

        myLocation = new MyLocation(MapActivity.this);

        loadingDialog.show();
        myLocation.start(this);
    }



    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myLocation.stop();
        mMapView.onDestroy();
    }

    @OnClick({R.id.btn_locate})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_locate:
                loadingDialog.show();
                myLocation.start(this);
                break;

        }
    }

    @Override
    public void result(boolean result, BDLocation location) {
//        if (!isDestroyed()) {
//            return;
//        }

        if (result) {
            Log.e("定位","定位成功！！！");
            MapUtil.setMyLocation(mBaiduMap, location);
        } else {
            Log.e("定位","定位失败！！！");
        }

        loadingDialog.dismiss();
    }

    private void showRangeDetail() {
        MapUtil.changeMapStatus(mBaiduMap, new LatLng(31.308071, 121.498149), MapUtil.DEFAULT_ZOOM);

        mBaiduMap.clear();

        addItem(TYPE_ITEM_SHOP, new LatLng(31.310116, 121.498283), "财大科技园");
        addItem(TYPE_ITEM_SHOP, new LatLng(31.309165, 121.496214), "永和大王");
        addItem(TYPE_ITEM_SHOP, new LatLng(31.307385, 121.499802), "KFC");
    }

    private void showShopDetail(final Marker marker, final int type) {
        final String msg = marker.getExtraInfo().getString("msg", "default");

        System.out.println("marker click  type : " + type + "  msg : " + msg);

        ShopDetailDialog dialog = new ShopDetailDialog(MapActivity.this, msg);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                MapUtil.changeMarker(marker, getItemView(type, msg));
            }
        });
        dialog.show();

        MapUtil.changeMarker(marker, getItemView(TYPE_ITEM_SELECTED, msg));
    }



    private void addRange(LatLng latLng, String msg) {
        View view = LayoutInflater.from(MapActivity.this).inflate(R.layout.map_range, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_msg);
        tv.setText(msg);
        Bundle bundle = new Bundle();
        bundle.putInt("type", TYPE_RANGE);
        MapUtil.setMarker(mBaiduMap, latLng, view, bundle);
    }

    private void addItem(int type, LatLng latLng, String msg) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("msg", msg);
        MapUtil.setMarker(mBaiduMap, latLng, getItemView(type, msg), bundle);
    }

    private View getItemView(int type, String msg) {
        View view = LayoutInflater.from(MapActivity.this).inflate(R.layout.map_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_msg);
        tv.setText(msg);

        if (type == TYPE_ITEM_SHOP) {
            tv.setBackgroundResource(R.drawable.map_bg_item_shop);
        } else {
            tv.setBackgroundResource(R.drawable.map_bg_item_select);
        }

        return view;
    }

}
