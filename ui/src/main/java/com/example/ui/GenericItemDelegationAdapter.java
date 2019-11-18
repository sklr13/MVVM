package com.example.ui;

import androidx.annotation.NonNull;

import java.util.List;

public class GenericItemDelegationAdapter<T extends List<?>> extends ListDelegationAdapter<T> {

    public GenericItemDelegationAdapter() {
    }

    public GenericItemDelegationAdapter(@NonNull AdapterDelegatesManager<T> delegatesManager) {
        super(delegatesManager);
    }
}
