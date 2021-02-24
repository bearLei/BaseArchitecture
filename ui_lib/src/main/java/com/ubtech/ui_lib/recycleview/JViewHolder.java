package com.ubtech.ui_lib.recycleview;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by lei on 2020/5/22
 * desc   :
 */
public class JViewHolder {

    private static int ID_HOLDER = -1;

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<Object> viewHolder = (SparseArray<Object>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        if (ID_HOLDER != id) {
            View childView = (View) viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        } else {
            return null;
        }
    }

    public static void setHolderTag(View view, BaseHolder<?> holder) {
        if (view == null) {
            return;
        }
        SparseArray<Object> viewHolder = (SparseArray<Object>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        viewHolder.put(ID_HOLDER, holder);
    }

    public static BaseHolder getHolderTag(View view) {
        SparseArray<Object> viewHolder = (SparseArray<Object>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        return (BaseHolder) viewHolder.get(ID_HOLDER);
    }
}
