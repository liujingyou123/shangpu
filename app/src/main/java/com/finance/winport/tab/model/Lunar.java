package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xzw on 2017/5/20.
 * 农历接口
 */

public class Lunar extends BaseResponse {

    /**
     * errCode : 0
     * data : {"shengxiao":"猴","star":"射手座","month":"12","lunaryear":"2016","year":"2016","day":"12","lunarday":"十四","lunarmonth":"冬月","huangli":{"xishen":"东南","wuxing":"大林木","sha":"煞南","chong":"冲（壬戌）狗","ji":["加班","造庙","置产","掘井"],"fushen":"正北","caishen":"正北","taishen":"房床栖外正南","jiri":"天牢（黑道）定日","suici":["丙申年","庚子月","戊辰日"],"nongli":"农历二〇一六年十一月十四","jishenyiqu":"天恩 天仓 时德 三合 临日 圣心","zhiri":"天牢（黑道凶日）","xiongshen":"天牢","yi":["捡钱","交友","送礼","取钱","减肥","安香","出火","调休","会亲友","安机械","修车","搭车","竖柱","买彩票","造屋","运动","定磉","迟到","自拍","分手","租房","造桥","造船","跳槽","请客","熬夜"]},"week":"一","ganzhi":"丙申"}
     */
    public DataBean data;

    public static class DataBean {
        /**
         * shengxiao : 猴
         * star : 射手座
         * month : 12
         * lunaryear : 2016
         * year : 2016
         * day : 12
         * lunarday : 十四
         * lunarmonth : 冬月
         * huangli : {"xishen":"东南","wuxing":"大林木","sha":"煞南","chong":"冲（壬戌）狗","ji":["加班","造庙","置产","掘井"],"fushen":"正北","caishen":"正北","taishen":"房床栖外正南","jiri":"天牢（黑道）定日","suici":["丙申年","庚子月","戊辰日"],"nongli":"农历二〇一六年十一月十四","jishenyiqu":"天恩 天仓 时德 三合 临日 圣心","zhiri":"天牢（黑道凶日）","xiongshen":"天牢","yi":["捡钱","交友","送礼","取钱","减肥","安香","出火","调休","会亲友","安机械","修车","搭车","竖柱","买彩票","造屋","运动","定磉","迟到","自拍","分手","租房","造桥","造船","跳槽","请客","熬夜"]}
         * week : 一
         * ganzhi : 丙申
         */

        public String shengxiao;
        public String star;
        public String month;
        public String lunaryear;
        public String year;
        public String day;
        public String lunarday;
        public String lunarmonth;
        public HuangliBean huangli;
        public String week;
        public String ganzhi;

        public static class HuangliBean {
            /**
             * xishen : 东南
             * wuxing : 大林木
             * sha : 煞南
             * chong : 冲（壬戌）狗
             * ji : ["加班","造庙","置产","掘井"]
             * fushen : 正北
             * caishen : 正北
             * taishen : 房床栖外正南
             * jiri : 天牢（黑道）定日
             * suici : ["丙申年","庚子月","戊辰日"]
             * nongli : 农历二〇一六年十一月十四
             * jishenyiqu : 天恩 天仓 时德 三合 临日 圣心
             * zhiri : 天牢（黑道凶日）
             * xiongshen : 天牢
             * yi : ["捡钱","交友","送礼","取钱","减肥","安香","出火","调休","会亲友","安机械","修车","搭车","竖柱","买彩票","造屋","运动","定磉","迟到","自拍","分手","租房","造桥","造船","跳槽","请客","熬夜"]
             */

            public String xishen;
            public String wuxing;
            public String sha;
            public String chong;
            public String fushen;
            public String caishen;
            public String taishen;
            public String jiri;
            public String nongli;
            public String jishenyiqu;
            public String zhiri;
            public String xiongshen;
            public List<String> ji;
            public List<String> suici;
            public List<String> yi;
        }
    }
}
