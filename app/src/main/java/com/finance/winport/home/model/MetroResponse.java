package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/19.
 */

public class MetroResponse extends BaseResponse{


    /**
     * lineId : 1
     * lineName : 1号线
     * stationList : [{"stationId":"3","stationName":"富锦路"},{"stationId":"4","stationName":"友谊西路"},{"stationId":"5","stationName":"宝安公路"},{"stationId":"6","stationName":"共富新村"},{"stationId":"7","stationName":"呼兰路"},{"stationId":"8","stationName":"通河新村"},{"stationId":"9","stationName":"共康路"},{"stationId":"10","stationName":"彭浦新村"},{"stationId":"11","stationName":"汶水路"},{"stationId":"12","stationName":"上海马戏城"},{"stationId":"13","stationName":"延长路"},{"stationId":"14","stationName":"中山北路"},{"stationId":"15","stationName":"上海火车站"},{"stationId":"16","stationName":"汉中路"},{"stationId":"17","stationName":"新闸路"},{"stationId":"18","stationName":"人民广场"},{"stationId":"19","stationName":"黄陂南路"},{"stationId":"20","stationName":"陕西南路"},{"stationId":"21","stationName":"常熟路"},{"stationId":"22","stationName":"衡山路"},{"stationId":"23","stationName":"徐家汇"},{"stationId":"24","stationName":"上海体育馆"},{"stationId":"25","stationName":"漕宝路"},{"stationId":"26","stationName":"上海南站"},{"stationId":"27","stationName":"锦江乐园"},{"stationId":"28","stationName":"莲花路"},{"stationId":"29","stationName":"外环路"},{"stationId":"30","stationName":"莘庄"}]
     */

    private List<Metro> data;

    public List<Metro> getData() {
        return data;
    }

    public void setData(List<Metro> data) {
        this.data = data;
    }

    public static class Metro {
        private String lineId;
        private String lineName;
        /**
         * stationId : 3
         * stationName : 富锦路
         */

        private List<Station> stationList;

        public String getLineId() {
            return lineId;
        }

        public void setLineId(String lineId) {
            this.lineId = lineId;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public List<Station> getStationList() {
            return stationList;
        }

        public void setStationList(List<Station> stationList) {
            this.stationList = stationList;
        }

        public static class Station {
            private String lineId;
            private String lineName;
            private String stationId;
            private String stationName;

            public String getStationId() {
                return stationId;
            }

            public void setStationId(String stationId) {
                this.stationId = stationId;
            }

            public String getStationName() {
                return stationName;
            }

            public void setStationName(String stationName) {
                this.stationName = stationName;
            }

            public String getLineId() {
                return lineId;
            }

            public void setLineId(String lineId) {
                this.lineId = lineId;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }
        }
    }
}
