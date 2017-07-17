package com.josecuentas.android_recyclerviewcarrusel;

/**
 * Created by jcuentas on 17/07/17.
 */

public class Item {

    public int id;
    public String name;
    public String url;
    public String urlHolder;
    public boolean isCheck;

    public void check() {
        isCheck = true;
    }

    public void unCheck() {
        isCheck = false;
    }
}
