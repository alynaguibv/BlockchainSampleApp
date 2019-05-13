package de.n26.n26blockchain.base.common.view

import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun getLayoutRes(): Int
    abstract fun showErrorView(throwable: Throwable)
    abstract fun hideErrorView()
    abstract fun loadViewData()
}