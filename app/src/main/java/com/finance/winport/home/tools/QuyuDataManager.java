package com.finance.winport.home.tools;

import com.finance.winport.home.model.MetroResponse;
import com.finance.winport.home.model.RegionResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/5/19.
 */

public class QuyuDataManager {
    private static final QuyuDataManager ourInstance = new QuyuDataManager();
    private List<RegionResponse.Region> mRegionData = new ArrayList<>();
    private List<MetroResponse.Metro> mMetrosData = new ArrayList<>();


    public static QuyuDataManager getInstance() {
        return ourInstance;
    }

    private QuyuDataManager() {
    }

    public void addRegion(List<RegionResponse.Region> regionData) {
        mRegionData.clear();
        mRegionData.addAll(regionData);
    }

    public List<RegionResponse.Region> getRegionsNoAll() {
        return mRegionData;
    }
    public List<RegionResponse.Region> getRegions() {
        List<RegionResponse.Region> regions = new ArrayList<>();
        RegionResponse.Region allRegion = new RegionResponse.Region();
        allRegion.setRegionName("全部");
        allRegion.setRegionId("-1");
        regions.add(allRegion);
        regions.addAll(mRegionData);
        return regions;
    }

    public List<RegionResponse.Region.Block> getBlockByRegionId(String id) {
        RegionResponse.Region region = null;
        for (int i=0;i<mRegionData.size(); i++) {
            if (id.equals(mRegionData.get(i).getRegionId())) {
                region = mRegionData.get(i);
                break;
            }
        }
        return getBlocks(region);
    }

    public List<RegionResponse.Region.Block> getBlocks(RegionResponse.Region region) {
        List<RegionResponse.Region.Block> rets = new ArrayList<>();

        if (region == null) {
            return rets;
        }
        RegionResponse.Region.Block block = new RegionResponse.Region.Block();
        block.setBlockName("全部");
        block.setBlockId("-1");
        block.setRegionId(region.getRegionId());
        block.setRegionName(region.getRegionName());
        rets.add(block);
        for (int i=0; i< region.getBlockList().size(); i++) {
            RegionResponse.Region.Block block1 = region.getBlockList().get(i);
            block1.setRegionId(region.getRegionId());
            block1.setRegionName(region.getRegionName());
            rets.add(block1);
        }
        return rets;
    }


    public void addMetro(List<MetroResponse.Metro> metroData) {
        mMetrosData.clear();
        mMetrosData.addAll(metroData);
    }


    public List<MetroResponse.Metro> getMetrosNoAll() {
        return mMetrosData;
    }
    public List<MetroResponse.Metro> getMetros() {
        List<MetroResponse.Metro> metros = new ArrayList<>();
        MetroResponse.Metro allmetros = new MetroResponse.Metro();
        allmetros.setLineName("全部");
        allmetros.setLineId("-1");
        metros.add(allmetros);
        metros.addAll(mMetrosData);
        return metros;
    }

    public List<MetroResponse.Metro.Station> getStationByMetroId(String id) {
        MetroResponse.Metro metro = null;
        for (int i=0;i<mMetrosData.size(); i++) {
            if (id.equals(mMetrosData.get(i).getLineId())) {
                metro = mMetrosData.get(i);
                break;
            }
        }
        return getStations(metro);
    }

    public List<MetroResponse.Metro.Station> getStations(MetroResponse.Metro metro) {
        List<MetroResponse.Metro.Station> rets = new ArrayList<>();
        MetroResponse.Metro.Station station = new MetroResponse.Metro.Station();
        station.setStationName("全部");
        station.setStationId("-1");
        station.setLineId(metro.getLineId());
        station.setLineName(metro.getLineName());
        rets.add(station);
        for (int i=0; i< metro.getStationList().size(); i++) {
            MetroResponse.Metro.Station station1 = metro.getStationList().get(i);
            station1.setLineId(metro.getLineId());
            station1.setLineName(metro.getLineName());
            rets.add(station1);
        }
        return rets;
    }
}
