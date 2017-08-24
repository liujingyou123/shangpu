package com.finance.winport.mine.event;

import com.finance.winport.mine.model.PersonalInfoResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/3.
 */

public class ModifyEvent {
    public InfoType type;
    public String content;
    public List<PersonalInfoResponse.DataBean.Attention.Plate> plateList;
    public List<PersonalInfoResponse.DataBean.Attention.Vocation> vocationList;
    public List<Integer> areaList;

    public ModifyEvent() {
    }

    public ModifyEvent(InfoType type, String content) {
        this.type = type;
        this.content = content;
    }

    public enum InfoType {
        NICK_NAME, SIGN, PHONE, CONCERN_TYPE
    }
}
