package com.finance.winport.map;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.dialog.SelectionDialog;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.map.model.MapAreaRequest;
import com.finance.winport.map.model.MapAreaResponse;
import com.finance.winport.map.model.MapShopDetailResponse;
import com.finance.winport.map.model.MapShopRequest;
import com.finance.winport.map.model.MapShopResponse;
import com.finance.winport.map.presenter.IMapView;
import com.finance.winport.map.presenter.MapPresenter;
import com.finance.winport.util.SelectDialogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MapActivity extends BaseActivity implements MyLocation.XLocationListener, IMapView {

    public static final int TYPE_RANGE = 0;
    public static final int TYPE_RANGE_PLATE = 1;
    public static final int TYPE_ITEM_SHOP = 2;
    //    public static final int TYPE_ITEM_INFO = 2;
    public static final int TYPE_ITEM_SELECTED = 3;

    @BindView(R.id.mapView)
    TextureMapView mMapView;
    @BindView(R.id.btn_locate)
    ImageButton btnLocate;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.map_filter)
    ImageView mapFilter;
    @BindView(R.id.rent_money)
    TextView rentMoney;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.money_line)
    View moneyLine;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.area_line)
    View areaLine;
    @BindView(R.id.ll_area)
    LinearLayout llArea;
    @BindView(R.id.select_area)
    LinearLayout selectArea;

    private BaiduMap mBaiduMap;
    private MyLocation myLocation;
    private LoadingDialog loadingDialog;
    private SelectionDialog selectionDialog;

    private MapPresenter mapPresenter;
    private int flag = 0;//0 租金  1 面积
    private List<MapShopResponse.DataBean> data = new ArrayList<>();

    private Marker selectMarker;

    private ShopRequset shopRequset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        loadingDialog = new LoadingDialog(this);

        EventBus.getDefault().register(this);

        initMap();
    }

    private void initMap() {

        shopRequset = (ShopRequset) getIntent().getSerializableExtra("shopRequest");
        mMapView.showScaleControl(false);
        mBaiduMap = mMapView.getMap();

        UiSettings settings = mBaiduMap.getUiSettings();
        settings.setAllGesturesEnabled(false);
        settings.setScrollGesturesEnabled(true);
        settings.setZoomGesturesEnabled(true);
        mBaiduMap.setMaxAndMinZoomLevel(19, 12);

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                Log.i("mapstatus", "" + mapStatus.toString());
                Log.i("jjjjjjjjjjjjjjjjjjk", "" + mapStatus.toString());
//                mBaiduMap.clear();
//
                if (mapStatus.zoom <= 14) {
                    selectArea.setVisibility(View.GONE);
                    MapAreaRequest request = new MapAreaRequest();
                    request.setMaxLat(mapStatus.bound.northeast.latitude + "");
                    request.setMaxLon(mapStatus.bound.northeast.longitude + "");
                    request.setMinLat(mapStatus.bound.southwest.latitude + "");
                    request.setMinLon(mapStatus.bound.southwest.longitude + "");
                    if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                        request.setRentList(shopRequset.rentList);
                    } else {
                        request.setRentList(null);
                    }
                    if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                        request.setTransferList(shopRequset.transferList);
                    } else {
                        request.setTransferList(null);
                    }
                    if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                        request.setAreaList(shopRequset.areaList);
                    } else {
                        request.setAreaList(null);
                    }
                    if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                        request.setFeatureTagList(shopRequset.featureTagList);
                    } else {
                        request.setFeatureTagList(null);
                    }
                    if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                        request.setSupportTagList(shopRequset.supportTagList);
                    } else {
                        request.setSupportTagList(null);
                    }
                    request.setWidth(shopRequset.width);
                    request.setType("0");
                    mapPresenter.getMapArea(request);
                } else if (mapStatus.zoom <= 16) {
                    selectArea.setVisibility(View.GONE);
                    MapAreaRequest request = new MapAreaRequest();
                    request.setMaxLat(mapStatus.bound.northeast.latitude + "");
                    request.setMaxLon(mapStatus.bound.northeast.longitude + "");
                    request.setMinLat(mapStatus.bound.southwest.latitude + "");
                    request.setMinLon(mapStatus.bound.southwest.longitude + "");
                    if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                        request.setRentList(shopRequset.rentList);
                    } else {
                        request.setRentList(null);
                    }
                    if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                        request.setTransferList(shopRequset.transferList);
                    } else {
                        request.setTransferList(null);
                    }
                    if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                        request.setAreaList(shopRequset.areaList);
                    } else {
                        request.setAreaList(null);
                    }
                    if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                        request.setFeatureTagList(shopRequset.featureTagList);
                    } else {
                        request.setFeatureTagList(null);
                    }
                    if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                        request.setSupportTagList(shopRequset.supportTagList);
                    } else {
                        request.setSupportTagList(null);
                    }
                    request.setWidth(shopRequset.width);
                    request.setType("1");
                    mapPresenter.getMapArea(request);
                } else {
                    selectArea.setVisibility(View.VISIBLE);
                    MapShopRequest request = new MapShopRequest();
                    request.setMaxLat(mBaiduMap.getMapStatus().bound.northeast.latitude + "");
                    request.setMaxLon(mBaiduMap.getMapStatus().bound.northeast.longitude + "");
                    request.setMinLat(mBaiduMap.getMapStatus().bound.southwest.latitude + "");
                    request.setMinLon(mBaiduMap.getMapStatus().bound.southwest.longitude + "");
                    if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                        request.setRentList(shopRequset.rentList);
                    } else {
                        request.setRentList(null);
                    }
                    if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                        request.setTransferList(shopRequset.transferList);
                    } else {
                        request.setTransferList(null);
                    }
                    if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                        request.setAreaList(shopRequset.areaList);
                    } else {
                        request.setAreaList(null);
                    }
                    if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                        request.setFeatureTagList(shopRequset.featureTagList);
                    } else {
                        request.setFeatureTagList(null);
                    }
                    if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                        request.setSupportTagList(shopRequset.supportTagList);
                    } else {
                        request.setSupportTagList(null);
                    }
                    request.setWidth(shopRequset.width);
                    mapPresenter.getMapShop(request);
                }
            }
        });

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int type = marker.getExtraInfo().getInt("type", -1);
                String id = marker.getExtraInfo().getString("id");

//                Logger.i("marker type : " + type);

                switch (type) {
                    case TYPE_ITEM_SHOP:
                        selectArea.setVisibility(View.VISIBLE);
                        showShopDetail(marker, type);
                        break;
                    case TYPE_RANGE:

                        selectArea.setVisibility(View.GONE);
                        showRangePlate(id);
                        break;
                    case TYPE_RANGE_PLATE:
                        selectArea.setVisibility(View.VISIBLE);
                        showRangeDetail(id);
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

    @OnClick({R.id.btn_locate, R.id.back, R.id.map_filter, R.id.ll_money, R.id.ll_area, R.id.btn_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_locate:
                loadingDialog.show();
                myLocation.start(this);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.btn_list:
                finish();
                break;
            case R.id.map_filter:
                showShaiXuandialog();
                break;
            case R.id.ll_money:
                rentMoney.setTextColor(Color.parseColor("#ffa73b"));
                moneyLine.setVisibility(View.VISIBLE);
                area.setTextColor(Color.parseColor("#666666"));
                areaLine.setVisibility(View.INVISIBLE);
                flag = 0;
                for (int i = 0; i < data.size(); i++) {

                    if (flag == 0) {

                        addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(data.get(i).getLatitude()), Double.parseDouble(data.get(i).getLongitude())), data.get(i).getRent() + "元/月", data.get(i).getShopId() + "");
                    } else {

                        addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(data.get(i).getLatitude()), Double.parseDouble(data.get(i).getLongitude())), data.get(i).getArea() + "㎡", data.get(i).getShopId() + "");
                    }
                }
                break;
            case R.id.ll_area:
                area.setTextColor(Color.parseColor("#ffa73b"));
                areaLine.setVisibility(View.VISIBLE);
                rentMoney.setTextColor(Color.parseColor("#666666"));
                moneyLine.setVisibility(View.INVISIBLE);
                flag = 1;
                for (int i = 0; i < data.size(); i++) {

                    if (flag == 0) {

                        addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(data.get(i).getLatitude()), Double.parseDouble(data.get(i).getLongitude())), data.get(i).getRent() + "元/月", data.get(i).getShopId() + "");
                    } else {

                        addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(data.get(i).getLatitude()), Double.parseDouble(data.get(i).getLongitude())), data.get(i).getArea() + "㎡", data.get(i).getShopId() + "");
                    }
                }
                break;

        }
    }

    @Override
    public void result(boolean result, BDLocation location) {
//        if (!isDestroyed()) {
//            return;
//        }

        if (result) {
            Log.e("定位", "定位成功！！！");
            MapUtil.setMyLocation(mBaiduMap, location);
            selectArea.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i("eeeeeeeeeeeeeeqqq", mBaiduMap.getMapStatus().bound.northeast.latitude + "");
                    Log.i("eeeeeeeeeeeeeeqqq", mBaiduMap.getMapStatus().bound.northeast.longitude + "");
                    Log.i("eeeeeeeeeeeeeeqqq", mBaiduMap.getMapStatus().bound.southwest.latitude + "");
                    Log.i("eeeeeeeeeeeeeeqqq", mBaiduMap.getMapStatus().bound.southwest.longitude + "");
                    if (mapPresenter == null) {
                        mapPresenter = new MapPresenter(MapActivity.this);
                    }

                    MapShopRequest request = new MapShopRequest();
                    request.setMaxLat(mBaiduMap.getMapStatus().bound.northeast.latitude + "");
                    request.setMaxLon(mBaiduMap.getMapStatus().bound.northeast.longitude + "");
                    request.setMinLat(mBaiduMap.getMapStatus().bound.southwest.latitude + "");
                    request.setMinLon(mBaiduMap.getMapStatus().bound.southwest.longitude + "");
                    if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                        request.setRentList(shopRequset.rentList);
                    } else {
                        request.setRentList(null);
                    }
                    if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                        request.setTransferList(shopRequset.transferList);
                    } else {
                        request.setTransferList(null);
                    }
                    if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                        request.setAreaList(shopRequset.areaList);
                    } else {
                        request.setAreaList(null);
                    }
                    if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                        request.setFeatureTagList(shopRequset.featureTagList);
                    } else {
                        request.setFeatureTagList(null);
                    }
                    if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                        request.setSupportTagList(shopRequset.supportTagList);
                    } else {
                        request.setSupportTagList(null);
                    }
                    request.setWidth(shopRequset.width);
                    mapPresenter.getMapShop(request);
                }
            }, 1000);

        } else {
            Log.e("定位", "定位失败！！！");
            selectArea.setVisibility(View.GONE);
            if (mapPresenter == null) {
                mapPresenter = new MapPresenter(MapActivity.this);
            }

            MapAreaRequest request = new MapAreaRequest();
            request.setType("0");
            if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                request.setRentList(shopRequset.rentList);
            } else {
                request.setRentList(null);
            }
            if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                request.setTransferList(shopRequset.transferList);
            } else {
                request.setTransferList(null);
            }
            if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                request.setAreaList(shopRequset.areaList);
            } else {
                request.setAreaList(null);
            }
            if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                request.setFeatureTagList(shopRequset.featureTagList);
            } else {
                request.setFeatureTagList(null);
            }
            if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                request.setSupportTagList(shopRequset.supportTagList);
            } else {
                request.setSupportTagList(null);
            }
            request.setWidth(shopRequset.width);
//            List<Integer> list = new ArrayList<>();
//            list.add(4);
//            list.add(5);
//            request.setAreaList(list);
            mapPresenter.getMapArea(request);

        }

        loadingDialog.dismiss();


    }

    private void showRangePlate(String id) {


        if (mapPresenter == null) {
            mapPresenter = new MapPresenter(MapActivity.this);
        }

        MapAreaRequest request = new MapAreaRequest();
        request.setType("1");
        request.setDistrictId(id);
        if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
            request.setRentList(shopRequset.rentList);
        } else {
            request.setRentList(null);
        }
        if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
            request.setTransferList(shopRequset.transferList);
        } else {
            request.setTransferList(null);
        }
        if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
            request.setAreaList(shopRequset.areaList);
        } else {
            request.setAreaList(null);
        }
        if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
            request.setFeatureTagList(shopRequset.featureTagList);
        } else {
            request.setFeatureTagList(null);
        }
        if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
            request.setSupportTagList(shopRequset.supportTagList);
        } else {
            request.setSupportTagList(null);
        }
        request.setWidth(shopRequset.width);
        mapPresenter.getMapArea(request);

    }

    private void showRangeDetail(String id) {
        MapShopRequest request = new MapShopRequest();
        request.setBlockId(id);
        if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
            request.setRentList(shopRequset.rentList);
        } else {
            request.setRentList(null);
        }
        if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
            request.setTransferList(shopRequset.transferList);
        } else {
            request.setTransferList(null);
        }
        if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
            request.setAreaList(shopRequset.areaList);
        } else {
            request.setAreaList(null);
        }
        if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
            request.setFeatureTagList(shopRequset.featureTagList);
        } else {
            request.setFeatureTagList(null);
        }
        if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
            request.setSupportTagList(shopRequset.supportTagList);
        } else {
            request.setSupportTagList(null);
        }
        request.setWidth(shopRequset.width);
        mapPresenter.getMapShop(request);
    }

    private void showShopDetail(final Marker marker, final int type) {
        final String msg = marker.getExtraInfo().getString("msg", "default");

        System.out.println("marker click  type : " + type + "  msg : " + msg);

        if (mapPresenter == null) {
            mapPresenter = new MapPresenter(MapActivity.this);
        }

        selectMarker = marker;
//        InfoWindow mInfiWindow = new InfoWindow(BitmapDescriptorFactory.fromResource(R.mipmap.btn_map_selected), selectMarker.getPosition(), 0, new InfoWindow.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick() {
//
//            }
//        });
//
//        mBaiduMap.showInfoWindow(mInfiWindow);
        mapPresenter.getMapShopDetail(marker.getExtraInfo().getString("id"));

//        ShopDetailDialog dialog = new ShopDetailDialog(MapActivity.this, msg);
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                MapUtil.changeMarker(marker, getItemView(type, msg));
//            }
//        });
//        dialog.show();
//
//        MapUtil.changeMarker(marker, getItemView(TYPE_ITEM_SELECTED, msg));

    }


    private void addRange(LatLng latLng, String msg, String id) {
        View view = LayoutInflater.from(MapActivity.this).inflate(R.layout.map_range, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_msg);
        tv.setText(msg);
        Bundle bundle = new Bundle();
        bundle.putInt("type", TYPE_RANGE);
        bundle.putString("id", id);
        MapUtil.setMarker(mBaiduMap, latLng, view, bundle);
    }

    private void addRangePlate(LatLng latLng, String msg, String id) {
        View view = LayoutInflater.from(MapActivity.this).inflate(R.layout.map_range, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_msg);
        tv.setText(msg);
        Bundle bundle = new Bundle();
        bundle.putInt("type", TYPE_RANGE_PLATE);
        bundle.putString("id", id);
        MapUtil.setMarker(mBaiduMap, latLng, view, bundle);
    }


    private void addItem(int type, LatLng latLng, String msg, String id) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("msg", msg);
        bundle.putString("id", id);
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

    private void showShaiXuandialog() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (selectionDialog == null) {
//                    selectionDialog = new SelectionDialog(MapActivity.this);
//                    selectionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                        @Override
//                        public void onDismiss(DialogInterface dialog) {
//                        }
//                    });
//                }
//                selectionDialog.show();
//            }
//        }, 300);


//        SelectDialogUtil.getInstance().showDialog();

        if (selectionDialog == null) {
            selectionDialog = new SelectionDialog(this);
            selectionDialog.setOnSelectListener(new SelectionDialog.OnSelectListener() {
                @Override
                public void onSelect(ShopRequset request) {

                    EventBus.getDefault().post(request);
                }
            });
        }

        if (!selectionDialog.isShowing()) {
            selectionDialog.initAreaData(shopRequset.areaList);
            selectionDialog.initRentData(shopRequset.rentList);
            selectionDialog.initPriceData(shopRequset.transferList);
            selectionDialog.initWidth(shopRequset.width);
            selectionDialog.initFeatureData(shopRequset.featureTagList);
            selectionDialog.initSupportData(shopRequset.supportTagList);
            selectionDialog.show();
        }


    }

    @Override
    public void showMapShop(MapShopResponse response) {
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(new LatLng(Double.parseDouble(response.getData().get(0).getLatitude()), Double.parseDouble(response.getData().get(0).getLongitude())))
                .zoom(17)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        mBaiduMap.clear();

        data = response.getData();

        for (int i = 0; i < response.getData().size(); i++) {
//            if(mBaiduMap.getMapStatus().bound.contains(new LatLng(Double.parseDouble(response.getData().get(i).getLatitude().toString()), Double.parseDouble(response.getData().get(i).getLongitude().toString())))){

            if (flag == 0) {

                addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(response.getData().get(i).getLatitude()), Double.parseDouble(response.getData().get(i).getLongitude())), response.getData().get(i).getRent() + "元/月", response.getData().get(i).getShopId() + "");
            } else {

                addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(response.getData().get(i).getLatitude()), Double.parseDouble(response.getData().get(i).getLongitude())), response.getData().get(i).getArea() + "㎡", response.getData().get(i).getShopId() + "");
            }
//            }
        }


    }

    @Override
    public void showRemoveMapShop(MapShopResponse response) {
        mBaiduMap.clear();

        data = response.getData();
        for (int i = 0; i < response.getData().size(); i++) {
//            if(mBaiduMap.getMapStatus().bound.contains(new LatLng(Double.parseDouble(response.getData().get(i).getLatitude().toString()), Double.parseDouble(response.getData().get(i).getLongitude().toString())))){

            if (flag == 0) {

                addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(response.getData().get(i).getLatitude()), Double.parseDouble(response.getData().get(i).getLongitude())), response.getData().get(i).getRent() + "元/月", response.getData().get(i).getShopId() + "");
            } else {

                addItem(TYPE_ITEM_SHOP, new LatLng(Double.parseDouble(response.getData().get(i).getLatitude()), Double.parseDouble(response.getData().get(i).getLongitude())), response.getData().get(i).getArea() + "㎡", response.getData().get(i).getShopId() + "");
            }
//            }
        }
    }

    @Override
    public void showMapArea(MapAreaResponse response) {

        mBaiduMap.clear();

        for (int i = 0; i < response.getData().size(); i++) {
            if (!TextUtils.isEmpty(response.getData().get(i).getName()) && !TextUtils.isEmpty(response.getData().get(i).getLatitude()) && !TextUtils.isEmpty(response.getData().get(i).getLongitude()))
                addRange(new LatLng(Double.parseDouble(response.getData().get(i).getLatitude().toString()), Double.parseDouble(response.getData().get(i).getLongitude().toString())), response.getData().get(i).getName().toString(), response.getData().get(i).getBizId() + "");
        }


    }

    @Override
    public void showMapPlate(MapAreaResponse response) {
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(new LatLng(Double.parseDouble(response.getData().get(0).getLatitude()), Double.parseDouble(response.getData().get(0).getLongitude())))
                .zoom(15)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        mBaiduMap.clear();
        for (int i = 0; i < response.getData().size(); i++) {
            if (!TextUtils.isEmpty(response.getData().get(i).getName()) && !TextUtils.isEmpty(response.getData().get(i).getLatitude()) && !TextUtils.isEmpty(response.getData().get(i).getLongitude())) {
//                if(mBaiduMap.getMapStatus().bound.contains(new LatLng(Double.parseDouble(response.getData().get(i).getLatitude().toString()), Double.parseDouble(response.getData().get(i).getLongitude().toString())))){

                addRangePlate(new LatLng(Double.parseDouble(response.getData().get(i).getLatitude().toString()), Double.parseDouble(response.getData().get(i).getLongitude().toString())), response.getData().get(i).getName().toString(), response.getData().get(i).getBizId() + "");
//                }
            }
        }
    }

    @Override
    public void showRemoveMapPlate(MapAreaResponse response) {

        mBaiduMap.clear();
        for (int i = 0; i < response.getData().size(); i++) {
            if (!TextUtils.isEmpty(response.getData().get(i).getName()) && !TextUtils.isEmpty(response.getData().get(i).getLatitude()) && !TextUtils.isEmpty(response.getData().get(i).getLongitude())) {
//                if(mBaiduMap.getMapStatus().bound.contains(new LatLng(Double.parseDouble(response.getData().get(i).getLatitude().toString()), Double.parseDouble(response.getData().get(i).getLongitude().toString())))){

                addRangePlate(new LatLng(Double.parseDouble(response.getData().get(i).getLatitude().toString()), Double.parseDouble(response.getData().get(i).getLongitude().toString())), response.getData().get(i).getName().toString(), response.getData().get(i).getBizId() + "");
//                }
            }
        }
    }

    @Override
    public void showMapShopDetail(MapShopDetailResponse response) {

        final int type = selectMarker.getExtraInfo().getInt("type", -1);

        final String msg = selectMarker.getExtraInfo().getString("msg", "default");

        ShopDetailDialog dialog = new ShopDetailDialog(MapActivity.this, response.getData());
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                MapUtil.changeMarker(selectMarker, getItemView(type, msg));
            }
        });
        dialog.show();

        MapUtil.changeMarker(selectMarker, getItemView(TYPE_ITEM_SELECTED, msg));
    }

    @Subscribe
    public void mainThread(ShopRequset requset){

        shopRequset = requset;
        if (mBaiduMap.getMapStatus().zoom <= 14) {
            selectArea.setVisibility(View.GONE);
            MapAreaRequest request = new MapAreaRequest();
            request.setMaxLat(mBaiduMap.getMapStatus().bound.northeast.latitude + "");
            request.setMaxLon(mBaiduMap.getMapStatus().bound.northeast.longitude + "");
            request.setMinLat(mBaiduMap.getMapStatus().bound.southwest.latitude + "");
            request.setMinLon(mBaiduMap.getMapStatus().bound.southwest.longitude + "");
            if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                request.setRentList(shopRequset.rentList);
            } else {
                request.setRentList(null);
            }
            if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                request.setTransferList(shopRequset.transferList);
            } else {
                request.setTransferList(null);
            }
            if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                request.setAreaList(shopRequset.areaList);
            } else {
                request.setAreaList(null);
            }
            if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                request.setFeatureTagList(shopRequset.featureTagList);
            } else {
                request.setFeatureTagList(null);
            }
            if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                request.setSupportTagList(shopRequset.supportTagList);
            } else {
                request.setSupportTagList(null);
            }
            request.setWidth(shopRequset.width);
            request.setType("0");
            mapPresenter.getMapArea(request);
        } else if (mBaiduMap.getMapStatus().zoom <= 16) {
            selectArea.setVisibility(View.GONE);
            MapAreaRequest request = new MapAreaRequest();
            request.setMaxLat(mBaiduMap.getMapStatus().bound.northeast.latitude + "");
            request.setMaxLon(mBaiduMap.getMapStatus().bound.northeast.longitude + "");
            request.setMinLat(mBaiduMap.getMapStatus().bound.southwest.latitude + "");
            request.setMinLon(mBaiduMap.getMapStatus().bound.southwest.longitude + "");
            if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                request.setRentList(shopRequset.rentList);
            } else {
                request.setRentList(null);
            }
            if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                request.setTransferList(shopRequset.transferList);
            } else {
                request.setTransferList(null);
            }
            if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                request.setAreaList(shopRequset.areaList);
            } else {
                request.setAreaList(null);
            }
            if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                request.setFeatureTagList(shopRequset.featureTagList);
            } else {
                request.setFeatureTagList(null);
            }
            if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                request.setSupportTagList(shopRequset.supportTagList);
            } else {
                request.setSupportTagList(null);
            }
            request.setWidth(shopRequset.width);
            request.setType("1");
            mapPresenter.getMapArea(request);
        } else {
            selectArea.setVisibility(View.VISIBLE);
            MapShopRequest request = new MapShopRequest();
            request.setMaxLat(mBaiduMap.getMapStatus().bound.northeast.latitude + "");
            request.setMaxLon(mBaiduMap.getMapStatus().bound.northeast.longitude + "");
            request.setMinLat(mBaiduMap.getMapStatus().bound.southwest.latitude + "");
            request.setMinLon(mBaiduMap.getMapStatus().bound.southwest.longitude + "");
            if (shopRequset.rentList != null && shopRequset.rentList.size() > 0) {
                request.setRentList(shopRequset.rentList);
            } else {
                request.setRentList(null);
            }
            if (shopRequset.transferList != null && shopRequset.transferList.size() > 0) {
                request.setTransferList(shopRequset.transferList);
            } else {
                request.setTransferList(null);
            }
            if (shopRequset.areaList != null && shopRequset.areaList.size() > 0) {
                request.setAreaList(shopRequset.areaList);
            } else {
                request.setAreaList(null);
            }
            if (shopRequset.featureTagList != null && shopRequset.featureTagList.size() > 0) {
                request.setFeatureTagList(shopRequset.featureTagList);
            } else {
                request.setFeatureTagList(null);
            }
            if (shopRequset.supportTagList != null && shopRequset.supportTagList.size() > 0) {
                request.setSupportTagList(shopRequset.supportTagList);
            } else {
                request.setSupportTagList(null);
            }

            request.setWidth(shopRequset.width);
            mapPresenter.getMapShop(request);
        }

    }
}
