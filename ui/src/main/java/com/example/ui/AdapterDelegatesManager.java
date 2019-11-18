package com.example.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class AdapterDelegatesManager<T> {

    private static final List<Object> PAYLOADS_EMPTY_LIST = Collections.emptyList();

    protected SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat<>();

    public AdapterDelegatesManager<T> addDelegate(@NonNull AdapterDelegate<T> delegate) {
        int viewType = delegates.size();
        while (delegates.get(viewType) != null) {
            viewType++;
        }
        return addDelegate(viewType, false, delegate);
    }

    public AdapterDelegatesManager<T> addDelegate(int viewType, @NonNull AdapterDelegate<T> delegate) {
        return addDelegate(viewType, false, delegate);
    }

    public AdapterDelegatesManager<T> addDelegate(int viewType, boolean allowReplacingDelegate, @NonNull AdapterDelegate<T> delegate) {

        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        }

        if (!allowReplacingDelegate && delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An AdapterDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered AdapterDelegate is "
                            + delegates.get(viewType));
        }

        delegates.put(viewType, delegate);

        return this;
    }

    public int getItemViewType(T items, int position) {
//        if (items == null) {
//            throw new NullPointerException("Items dataSource is null!");
//        }

        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i);
            }
        }

        throw new NullPointerException(
                "No AdapterDelegate added that matches position=" + position + " in data source");
    }


    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final AdapterDelegate<T> delegate = getDelegateForViewType(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        final RecyclerView.ViewHolder vh = delegate.onCreateViewHolder(parent);
        if (vh == null) {
            throw new NullPointerException("ViewHolder returned from AdapterDelegate "
                    + delegate
                    + " for ViewType ="
                    + viewType
                    + " is null!");
        }
        return vh;
    }

    public void onBindViewHolder(@NonNull T items, int position,
                                 @NonNull RecyclerView.ViewHolder viewHolder,
                                 @Nullable List payloads) {

        AdapterDelegate<T> delegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for item at position = "
                    + position
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onBindViewHolder(items, position, viewHolder, payloads != null ? payloads : PAYLOADS_EMPTY_LIST);
    }

    public void onBindViewHolder(@NonNull T items, int position,
                                 @NonNull RecyclerView.ViewHolder viewHolder) {
        onBindViewHolder(items, position, viewHolder, PAYLOADS_EMPTY_LIST);
    }

    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        return delegate.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onViewDetachedFromWindow(viewHolder);
    }

    @Nullable
    public AdapterDelegate<T> getDelegateForViewType(int viewType) {
        return delegates.get(viewType);
    }
}