package com.example.core.ui

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {

    private var baseActivity: BaseActivity? = null

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    fun handleError(exc: Throwable?, r: (() -> Unit)?) {
        hideProgress()
        val activity = activity
        if (activity != null) {
            (activity as BaseActivity).handleError(exc, r)
        }
    }

    protected fun showProgress() {
        baseActivity?.showProgress()
    }

    protected fun hideProgress() {
        baseActivity?.hideProgress()
    }

    abstract fun tryAgain(retryAction: (() -> Unit)?)
}
