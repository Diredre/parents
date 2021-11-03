package com.example.parents.Homework;


import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public abstract class SwipeAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private LayoutInflater mInflater;

    public SwipeAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }

    public abstract void notifyDataSetChanged(List<HomeworkBean> dataList);

}