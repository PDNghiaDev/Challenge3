package com.gmail.pdnghiadev.challenge3;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gmail.pdnghiadev.challenge3.Helper.ItemTouchHelperViewHolder;

/**
 * Created by PDNghiaDev on 8/6/2015.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    public ImageView imgView;
    public TextView name;
    public RelativeLayout item;

    public MyViewHolder(View itemView) {
        super(itemView);
        imgView = (ImageView) itemView.findViewById(R.id.imgView);
        name = (TextView) itemView.findViewById(R.id.txtName);
        item = (RelativeLayout) itemView.findViewById(R.id.item);
    }


    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void onItemClear() {
        int color = Color.parseColor("#99CC00");
        itemView.setBackgroundColor(color);
    }
}
