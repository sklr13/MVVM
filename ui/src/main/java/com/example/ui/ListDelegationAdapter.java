package com.example.ui;

import androidx.annotation.NonNull;

import java.util.List;

public class ListDelegationAdapter<T extends List<?>> extends DelegationAdapter<T> {

    public ListDelegationAdapter() {
    }

    public ListDelegationAdapter(@NonNull AdapterDelegatesManager<T> delegatesManager) {
        super(delegatesManager);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
