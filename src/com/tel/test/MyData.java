package com.tel.test;

import java.io.Serializable;

/**
 * Created by violintel on 7/15/14.
 */
public class MyData implements Serializable {
    String name;

    public MyData(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
