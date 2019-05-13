package app.sample.blockchainsampleapp

import android.app.Activity
import app.sample.blockchainsampleapp.blockchainstats.ui.BlockchainStatsActivity
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RopolectricUnitTest {

    var activity: Activity? = null

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(BlockchainStatsActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun assertActivityNotNull() {

    }
}