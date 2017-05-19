package com.finance.winport.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.finance.winport.R;

/**
 * Created by css_double on 17/5/3.
 */

public class MapUtil {
    public static final int DEFAULT_ZOOM = 17;

    public static void setDefaultLocation(BaiduMap baiduMap){
        BDLocation location = new BDLocation();
        //陆家嘴
        location.setLatitude(31.243280);
        location.setLongitude(121.508596);
        setMyLocation(baiduMap, location, 12);
    }

    public static void setMyLocation(BaiduMap baiduMap, BDLocation location){
        setMyLocation(baiduMap, location, DEFAULT_ZOOM);
    }

    public static void setMyLocation(BaiduMap baiduMap, BDLocation location, int zoom){

        setMapZoom(baiduMap, location, zoom);

        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        // 设置定位数据
        baiduMap.setMyLocationData(locData);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.map_icon_location_center);
//
//        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, mCurrentMarker, Color.parseColor("#1A4287FF"), Color.TRANSPARENT);
//        baiduMap.setMyLocationConfiguration(config);
        // 当不需要定位图层时关闭定位图层
//        baiduMap.setMyLocationEnabled(false);
    }

    public static void setMapZoom(BaiduMap baiduMap, BDLocation location, int zoom){
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .zoom(zoom)
                .target(new LatLng(location.getLatitude(),location.getLongitude()))
                .build();

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate);
    }

    public static void changeMapStatus(BaiduMap baiduMap, LatLng point, int zoom){
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(zoom)
                .build();

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate);
    }

    public static void changeMarker(Marker marker, View view){
        marker.setIcon(BitmapDescriptorFactory.fromView(view));
    }

    public static void changeMarker(Marker marker, Bitmap bitmap){
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
    }

    public static void setMarker(BaiduMap baiduMap, LatLng latlng, View view, Bundle bundle){
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
        setMarker(baiduMap, latlng, bitmap, bundle);
    }

    public static void setMarker(BaiduMap baiduMap, LatLng latlng, Bitmap bitmap, Bundle bundle){
        BitmapDescriptor bmp = BitmapDescriptorFactory.fromBitmap(bitmap);
        setMarker(baiduMap, latlng, bmp, bundle);
    }

    public static void setMarker(BaiduMap baiduMap, LatLng latlng, BitmapDescriptor bitmap, Bundle bundle){
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latlng)
                .anchor(0.5f, 0.5f)
                .draggable(false)
                .extraInfo(bundle)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);
    }
}
