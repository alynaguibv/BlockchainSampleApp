package app.sample.blockchainsampleapp.blockchainstats.ui

import android.os.Bundle
import app.sample.blockchainsampleapp.R
import de.n26.n26blockchain.base.common.view.BaseActivity

/*
* Block chain stats activity - main activity for sample application
*
* */
class BlockchainStatsActivity : BaseActivity() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_blockchainstats
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        loadFragment()
    }

    override fun loadFragment() {
        supportFragmentManager!!.beginTransaction().replace(
            R.id.content_layout,
            BlockchainStatsFragment()
        ).commit()
    }
}
