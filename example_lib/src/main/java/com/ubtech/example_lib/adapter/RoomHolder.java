package com.ubtech.example_lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ubtech.base_lib.view.recycleview.BaseHolder;
import com.ubtech.example_lib.R;
import com.ubtech.example_lib.database.UserInfo;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
public class RoomHolder extends BaseHolder<UserInfo> {
    private TextView name;
    public RoomHolder(Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected View initView(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.room_holder_item,null,false);
        name = mRootView.findViewById(R.id.name);
        return mRootView;
    }

    @Override
    protected void updateUI(Context context, UserInfo data) {
        if (null == data){
            return;
        }

        name.setText(data.getName());
    }
}
