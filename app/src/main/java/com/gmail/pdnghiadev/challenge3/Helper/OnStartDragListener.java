package com.gmail.pdnghiadev.challenge3.Helper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by PDNghiaDev on 8/6/2015.
 */
public interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
