package com.josecuentas.android_recyclerviewcarrusel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DiscreteScrollView mDsvItem;
    private ItemAdapter mItemAdapter;
    private List<Item> mItemList;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectView();
        setup();
    }

    private void injectView() {
        mDsvItem = (DiscreteScrollView) findViewById(R.id.dsvItem);
    }

    private void setup() {
        setupRepository();
        setupAdapter();
    }

    private void setupRepository() {
        ItemRepository itemRepository = new ItemRepository();
        mItemList = itemRepository.getItemList();
    }

    private void setupAdapter() {
        mItemAdapter = new ItemAdapter();
        mItemAdapter.setItemList(mItemList);
        mDsvItem.setOrientation(Orientation.HORIZONTAL);
        mDsvItem.setAdapter(InfiniteScrollAdapter.wrap(mItemAdapter));
    }
}
