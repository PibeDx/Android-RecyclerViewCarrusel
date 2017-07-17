package com.josecuentas.android_recyclerviewcarrusel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcuentas on 17/07/17.
 */

public class ItemRepository {

    public static final int SIZE_DUMMY = 20;
    private List<Item> mItemList;

    public ItemRepository() {
        mItemList = new ArrayList<>();
        generateItemDummy();
    }

    public List<Item> getItemList() {
        return mItemList;
    }

    private void generateItemDummy() {
        for (int i = 0; i < SIZE_DUMMY; i++) {
            Item item = new Item();
            item.id = i + 1;
            item.name = "Item " + item.id;


            if (i % 2 == 0) item.url = "https://cdn3.iconfinder.com/data/icons/vol-6/128/train-256.png";
            else item.url = "https://cdn3.iconfinder.com/data/icons/vol-6/128/zoom-256.png";
            item.urlHolder = "https://cdn3.iconfinder.com/data/icons/vol-6/128/usb-256.png";
            mItemList.add(item);

        }
    }
}
