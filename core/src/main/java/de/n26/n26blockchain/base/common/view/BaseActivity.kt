package de.n26.n26blockchain.base.common.view

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutRes() : Int
    abstract fun loadFragment()

//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        //AndroidInjection.inject(this);
//        super.onCreate(savedInstanceState, persistentState)
//    }
}