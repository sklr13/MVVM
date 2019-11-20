package com.example.details

import com.example.core.ui.BaseFragment

class DetailsFragment : BaseFragment() {
    override fun tryAgain(retryAction: (() -> Unit)?) {
        TODO("not implemented") // Add screen for displaying additional data on the separate screen
    }

    override fun getLayoutId(): Int = R.layout.details_fragment
}
