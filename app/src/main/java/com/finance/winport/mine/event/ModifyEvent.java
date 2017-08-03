package com.finance.winport.mine.event;

/**
 * Created by xzw on 2017/8/3.
 */

public class ModifyEvent {
    public InfoType type;
    public String content;

    public ModifyEvent(InfoType type, String content) {
        this.type = type;
        this.content = content;
    }

    public enum InfoType {
        NICK_NAME, SIGN, PHONE, CONCERN_TYPE
    }
}
