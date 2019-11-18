package com.example.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public abstract class DelegationAdapter<T> extends RecyclerView.Adapter {

    private AdapterDelegatesManager<T> delegationManager;

    protected T items;

    public DelegationAdapter() {
        this(new AdapterDelegatesManager<T>());
    }

    public DelegationAdapter(@NonNull AdapterDelegatesManager<T> delegationManager) {
        if (delegationManager == null) {
            throw new NullPointerException("AdapterDelegatesManager is null");
        }

        this.delegationManager = delegationManager;
    }

    public AdapterDelegatesManager<T> getDelegationManager() {
        return delegationManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegationManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegationManager.onBindViewHolder(items, position, holder, null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        delegationManager.onBindViewHolder(items, position, holder, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return delegationManager.getItemViewType(items, position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        delegationManager.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return delegationManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        delegationManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        delegationManager.onViewDetachedFromWindow(holder);
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
