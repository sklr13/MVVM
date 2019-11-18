package com.example.home

import com.example.core.data.domain_entity.RepositoryModel
import com.example.ui.GenericItemDelegationAdapter


internal class RepositoryAdapter : GenericItemDelegationAdapter<List<RepositoryModel>>() {
    init {
        delegationManager
            .addDelegate(RepositoryItemDelegate())
    }
}


