package com.gmail.pdnghiadev.challenge3;

import android.graphics.Color;

/**
 * Created by PDNghiaDev on 8/5/2015.
 */
public class MyItem {
    private int img;
    private String name;

    public MyItem(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }
}
