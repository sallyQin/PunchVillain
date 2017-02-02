package com.example.apc.punchvillain;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by 1 on 2017/1/10.
 */

public class UniversalHolder extends RecyclerView.ViewHolder {      // * 全通用 （通用Holder）

    private SparseArray<View> mViews = new SparseArray<>();

    @SuppressWarnings("WeakerAccess")
    public UniversalHolder(View itemView) {

        super(itemView);
    }

    public <T extends View>T get(@IdRes int id) {
        View view = mViews.get(id);
        if (null == view) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        //noinspection unchecked
        return (T) view;
    }

}
