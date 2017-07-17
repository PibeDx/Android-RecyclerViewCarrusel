package com.josecuentas.android_recyclerviewcarrusel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcuentas on 17/07/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>
        implements
        DiscreteScrollView.OnItemChangedListener<ItemAdapter.ItemViewHolder>,
        DiscreteScrollView.ScrollStateChangeListener<ItemAdapter.ItemViewHolder> {

    private RecyclerView mRecyclerView;
    private DiscreteScrollView mDiscreteScrollView;
    private Context mContext;
    private List<Item> mItemList;
    private OnListener mOnListener;

    public ItemAdapter() {
        mItemList = new ArrayList<>();
    }

    @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mDiscreteScrollView = (DiscreteScrollView) mRecyclerView;
        mDiscreteScrollView.addOnItemChangedListener(this);
        mDiscreteScrollView.addScrollStateChangeListener(this);
        mContext = recyclerView.getContext();
    }

    public void setOnListener(OnListener onListener) {
        mOnListener = onListener;
    }

    public void setItemList(List<Item> itemList) {
        mItemList = itemList;
        notifyDataSetChanged();
    }

    @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = mItemList.get(position);
        holder.setValue(item);
        holder.populate();
    }

    @Override public int getItemCount() {
        return mItemList.size();
    }

    @Override public void onCurrentItemChanged(@Nullable ItemViewHolder viewHolder, int adapterPosition) {
        viewHolder.showLine();
    }

    @Override public void onScrollStart(@NonNull ItemViewHolder currentItemHolder, int adapterPosition) {
        currentItemHolder.hideLine();
    }

    @Override public void onScrollEnd(@NonNull ItemViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override public void onScroll(float scrollPosition, @NonNull ItemViewHolder currentHolder, @NonNull ItemViewHolder newCurrent) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder  implements
            View.OnClickListener {
        private ImageView iviIcon;
        private View vieLine;
        private Item mItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            injectView();
            events();
        }

        @Override public void onClick(View view) {
            mRecyclerView.smoothScrollToPosition(getAdapterPosition());
            vieLine.setVisibility(View.VISIBLE);

        }

        private void injectView() {
            iviIcon = itemView.findViewById(R.id.image_icon);
            vieLine = itemView.findViewById(R.id.view_line);
        }

        private void events() {
            itemView.setOnClickListener(this);
        }

        public void showLine() {
            mItem.check();
            if (mItem.isCheck) vieLine.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(mItem.urlHolder).into(iviIcon);
        }

        public void hideLine() {
            mItem.unCheck();
            if (!mItem.isCheck) vieLine.setVisibility(View.GONE);
            Picasso.with(mContext).load(mItem.url).into(iviIcon);
        }

        private void setValue(Item item) {
            mItem = item;
        }

        private void populate() {
            Picasso.with(mContext).load(mItem.url).into(iviIcon);
        }
    }

    interface OnListener {
        void onItemClick(View view);
    }
}
