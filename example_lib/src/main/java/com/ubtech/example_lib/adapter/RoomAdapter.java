package com.ubtech.example_lib.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.ubtech.base_lib.view.recycleview.BaseHolder;
import com.ubtech.base_lib.view.recycleview.BaseRecycleAdapter;
import com.ubtech.example_lib.database.UserInfo;

import java.util.List;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
public class RoomAdapter extends BaseRecycleAdapter {

    private List<UserInfo> mData;
    public RoomAdapter(Context mContext) {
        super(mContext);
    }

    public RoomAdapter(Context mContext, List<UserInfo> mData) {
        super(mContext);
        this.mData = mData;
    }

    @Override
    protected BaseHolder getViewHolder(Context context, ViewGroup parent, int viewType) {
        RoomHolder holder = new RoomHolder(context);
        return holder;
    }

    @Override
    protected Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    protected int getAdapterItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
