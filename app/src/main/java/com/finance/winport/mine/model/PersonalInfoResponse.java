package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class PersonalInfoResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"customerId":4,"phone":"168****883","headPortrait":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","cityId":4,"cityName":"合肥","districtId":4,"districtName":"大牛区","blockId":4,"blockName":"www.sogou.com","industryId":4,"industryName":"休闲","list":[1,2]}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        public String phone;
        public String headPortrait;
        public String nickName;
        public String signature;
        public int isBanned;
        public int isNew;
        public String myService;
        public Attention attention;

        public static class Attention implements Serializable {
            public List<Plate> plateList;
            public List<Vocation> vocationList;
            public List<Integer> areaList;

            public static class Plate implements Serializable {
                public String cityId;
                public String cityName;
                public String districtId;
                public String districtName;
                public String plateId;
                public String plateName;
            }

            public static class Vocation implements Serializable {
                public String vocationId;
                public String vocationName;
            }
        }
    }

}
