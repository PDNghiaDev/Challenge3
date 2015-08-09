package com.gmail.pdnghiadev.challenge3;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.gmail.pdnghiadev.challenge3.Adapter.MyAdapter;

/**
 * Created by PDNghiaDev on 8/6/2015.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private MyAdapter adapter;

    public MyItemDecoration(int space, MyAdapter adapter) {
        this.space = space;
        this.adapter = adapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
        if (parent.getLayoutManager() instanceof GridLayoutManager){ //GridLayoutManager
            decorationGrid(outRect, parent, itemPosition);
        }else if (parent.getLayoutManager() instanceof LinearLayoutManager){ // LinearLayoutManager
            if (((LinearLayoutManager) parent.getLayoutManager()).getOrientation() == LinearLayoutManager.VERTICAL){
                decorationLinearVertical(outRect, itemPosition);
            }else {
                decorationLinearHorizontal(outRect, itemPosition);
            }
        }else { // StaggeredGridLayoutManager
            decorationStaggeredGrid(outRect, parent, itemPosition);
        }
    }

    private void decorationGrid(Rect outRect, RecyclerView parent, int itemPosition) {
        int mGridSize = 3;
        int frameWidth = (int) ((parent.getWidth() - (float) space * (mGridSize - 1)) / mGridSize);
        int padding = parent.getWidth() / mGridSize - frameWidth;

        // Padding Top
        if (itemPosition < mGridSize) {
            outRect.top = 0;
        } else {
            outRect.top = space;
        }
        if (itemPosition % mGridSize == 0){ // Position 0
            outRect.left = 0;
            outRect.right = padding;
        }else if (itemPosition % mGridSize == 1){ // Position 1
            outRect.left = padding;
            outRect.right = padding;
        }else {
            outRect.right = 0;
            outRect.left = padding;
        }

        outRect.bottom = 0;
    }

    private void decorationLinearHorizontal(Rect outRect, int itemPosition) {
        int count = adapter.getItemCount();
        if (itemPosition == 0){
            outRect.left = 0;
            outRect.right = space / 2;
        }else if (itemPosition == (count - 1)){
            outRect.left = space / 2;
            outRect.right = 0;
        }else {
            outRect.left = space / 2;
            outRect.right = space / 2;
        }
        outRect.top = 0;
        outRect.bottom = 0;
    }

    private void decorationLinearVertical(Rect outRect, int itemPosition) {
        int count = adapter.getItemCount();
        if (itemPosition == 0){
            outRect.top = 0;
            outRect.bottom = space / 2;
        }else if (itemPosition == (count - 1)){
            outRect.top = space / 2;
            outRect.bottom = 0;
        }else{
            outRect.top = space / 2;
            outRect.bottom = space / 2;
        }
        outRect.left = 0;
        outRect.right = 0;
    }

    private void decorationStaggeredGrid(Rect outRect, RecyclerView parent, int itemPosition) {
        int spanCount = ((StaggeredGridLayoutManager)parent.getLayoutManager()).getSpanCount();
        int mStaggeredGridSize = spanCount;
        int frameWidth = (int) ((parent.getWidth() - (float) space * (mStaggeredGridSize - 1)) / mStaggeredGridSize);
        int padding = parent.getWidth() / mStaggeredGridSize - frameWidth;
        // Padding Top
        if (itemPosition < mStaggeredGridSize) {
            outRect.top = 0;
        } else {
            outRect.top = space;
        }
        if (itemPosition % mStaggeredGridSize == 0){
            outRect.left = 0;
            outRect.right = padding;
        }else {
            outRect.left = padding;
            outRect.right = 0;
        }
        outRect.bottom = 0;
    }

}
