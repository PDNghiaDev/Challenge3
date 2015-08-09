package com.gmail.pdnghiadev.challenge3.Adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gmail.pdnghiadev.challenge3.Helper.ItemTouchHelperAdapter;
import com.gmail.pdnghiadev.challenge3.Helper.OnStartDragListener;
import com.gmail.pdnghiadev.challenge3.MyItem;
import com.gmail.pdnghiadev.challenge3.MyViewHolder;
import com.gmail.pdnghiadev.challenge3.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by PDNghiaDev on 8/5/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements ItemTouchHelperAdapter {
    private List<MyItem> dataItems;
    private final OnStartDragListener mDragListener;
    private Context context;
    private int lastPosition = -1;

    public MyAdapter(List<MyItem> dataItems, OnStartDragListener dragListener, Context context){
        this.dataItems = dataItems;
        this.mDragListener = dragListener;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i) {
        MyItem dataItem = dataItems.get(i);
        viewHolder.imgView.setImageResource(dataItem.getImg());
        viewHolder.name.setText(dataItem.getName());

        // Animation
//        if (i > lastPosition)
//        {
//            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
//            viewHolder.item.startAnimation(animation);
//            lastPosition = i;
//        }



        //Start a drag whenever the handle view it touched
        viewHolder.imgView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(dataItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        dataItems.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(MyItem item){
        dataItems.add(item);
        notifyItemInserted(dataItems.size());
    }
}
