package com.ubtech.base_lib.view.recycleview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



/**
 * Created by lei on 2020/5/25
 * desc:
 */
public abstract class BaseRecycleAdapter extends RecyclerView.Adapter<BaseRecycleAdapter.ListHolder> {

    private Context mContext;

    public BaseRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseHolder holder = getViewHolder(mContext, parent, viewType);
        return new ListHolder(holder.getRootView());
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        View itemView = holder.itemView;
        BaseHolder itemHolder = JViewHolder.getHolderTag(itemView);
        itemHolder.setSysTemHolder(holder);
        itemHolder.setData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return getAdapterItemCount();
    }

    /**
     * 返回某种viewType对应的BaseHolder
     *
     * @param context
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract BaseHolder getViewHolder(Context context, ViewGroup parent, int viewType);

    protected abstract Object getItem(int position);

    protected abstract int getAdapterItemCount();


    class ListHolder extends RecyclerView.ViewHolder {
        ListHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
