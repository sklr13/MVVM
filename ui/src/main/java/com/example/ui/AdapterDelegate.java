package com.example.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class AdapterDelegate<T> {

    protected abstract boolean isForViewType(@NonNull T items, int position);

    @NonNull
    abstract protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull T items, int position,
                                             @NonNull RecyclerView.ViewHolder holder,
                                             @NonNull List<Object> payloads);

    void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
    }

    boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        return false;
    }

    void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
    }

    void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
    }

    protected View createView(ViewGroup parent, @LayoutRes int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }
}
