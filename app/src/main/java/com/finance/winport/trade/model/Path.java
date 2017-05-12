package com.finance.winport.trade.model;

/**
 * Created by xzw on 16/9/27.
 */

public class Path {
    private String id;
    private String url;
    private int type;

    public Path() {
    }

    public Path(String url, int type) {
        this.url = url;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
