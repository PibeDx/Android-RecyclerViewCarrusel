package com.josecuentas.android_recyclerviewcarrusel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements
        DiscreteScrollView.OnItemChangedListener<ItemAdapter.ItemViewHolder>,
        DiscreteScrollView.ScrollStateChangeListener<ItemAdapter.ItemViewHolder> {

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
//        mDsvItem.addOnItemChangedListener(this);
//        mDsvItem.addScrollStateChangeListener(this);
        //mDsvItem.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        mDsvItem.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        mDsvItem.setAdapter(InfiniteScrollAdapter.wrap(mItemAdapter));

    }

    @Override public void onCurrentItemChanged(@Nullable ItemAdapter.ItemViewHolder viewHolder, int adapterPosition) {
        viewHolder.showLine();
    }

    @Override public void onScrollStart(@NonNull ItemAdapter.ItemViewHolder currentItemHolder, int adapterPosition) {
        currentItemHolder.hideLine();
    }

    @Override public void onScrollEnd(@NonNull ItemAdapter.ItemViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override public void onScroll(float scrollPosition, @NonNull ItemAdapter.ItemViewHolder currentHolder, @NonNull ItemAdapter.ItemViewHolder newCurrent) {

    }
}
