package com.finance.winport.home.model;

import java.io.Serializable;

/**
 * Created by liuworkmac on 17/5/15.
 */

public class Tag implements Serializable{
    private String parentId; //业态 父id
    private String color;
    private int id;
    private String name;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
