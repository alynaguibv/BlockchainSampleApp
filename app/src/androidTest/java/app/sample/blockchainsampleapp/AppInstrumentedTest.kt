package app.sample.blockchainsampleapp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import app.sample.blockchainsampleapp.blockchainstats.request.FetchBlockchainStatsRequest
import app.sample.blockchainsampleapp.network.RequestManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AppInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("app.sample.blockchainsampleapp", appContext.packageName)
    }

    @Test
    fun assertFetchBlockchainStatsResponseIsNotNull(){
        val requestManager = RequestManager.getInstance()
        val fetchBlockchainStatsRequest = FetchBlockchainStatsRequest()
        requestManager.start(fetchBlockchainStatsRequest)
        assertNotNull(fetchBlockchainStatsRequest.response)
    }

    @Test
    fun assertFetchBlockchainStatsResponseIsNull(){
        val requestManager = RequestManager.getInstance()
        val fetchBlockchainStatsRequest = FetchBlockchainStatsRequest()
        fetchBlockchainStatsRequest.baseURL = ""
        requestManager.start(fetchBlockchainStatsRequest)
        assertNull(fetchBlockchainStatsRequest.response)
    }
}
