package app.sample.blockchainsampleapp.blockchainstats.data.source

import app.sample.blockchainsampleapp.blockchainstats.response.ChartPoint
import app.sample.blockchainsampleapp.util.EspressoIdlingResource
import java.util.*
import kotlin.collections.ArrayList

/**
 * Concrete implementation to load blockchain stats from the data sources into a cache.
 *
 *
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
class BlockchainStatsRepository(
    val chartPointsRemoteDataSource: BlockchainStatsDataSource,
    val chartPointsLocalDataSource: BlockchainStatsDataSource
) : BlockchainStatsDataSource {

    /**
     * This variable has public visibility so it can be accessed from tests.
     */
    var cachedChartPoints: ArrayList<ChartPoint> = ArrayList()

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    var cacheIsDirty = true

    /**
     * Gets points from cache, local data source (SharedPreferences) or remote data source, whichever is
     * available first.
     *
     *
     * Note: [FetchStatsCallback.onDataNotAvailable] is fired if all data sources fail to
     * get the data.
     */
    override fun fetchBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        // Respond immediately with cache if available and not dirty
        if (cachedChartPoints.isNotEmpty() && !cacheIsDirty) {
            callback.onStatsFetched(ArrayList(cachedChartPoints))
            return
        }

        EspressoIdlingResource.increment() // Set app as busy.

        if (cacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            fetchBlockchainStatsPointsFromRemoteDataSource(callback)
        } else {
            // Query the local storage if available. If not, query the network.
            chartPointsLocalDataSource.fetchBlockchainStats(object : BlockchainStatsDataSource.FetchStatsCallback {
                override fun onStatsFetched(chartPoints: List<ChartPoint>) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                    callback.onStatsFetched(cachedChartPoints)
                }
                override fun onStatsNotAvailable(throwable: Throwable) {
                    fetchBlockchainStatsPointsFromRemoteDataSource(callback)
                }
            })
        }
    }

    private fun fetchBlockchainStatsPointsFromRemoteDataSource(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        chartPointsRemoteDataSource.fetchBlockchainStats(object : BlockchainStatsDataSource.FetchStatsCallback {
            override fun onStatsFetched(chartPoints: List<ChartPoint>) {
                EspressoIdlingResource.decrement() // Set app as idle.
                cachedChartPoints.clear()
                cachedChartPoints.addAll(chartPoints)
                callback.onStatsFetched(cachedChartPoints)
            }

            override fun onStatsNotAvailable(throwable: Throwable) {
                EspressoIdlingResource.decrement() // Set app as idle.
                callback.onStatsNotAvailable(throwable)
            }
        })
    }

    override fun saveBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {

    }

    companion object {

        private var INSTANCE: BlockchainStatsRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param blockchainRemoteDataSource the backend data source
         * *
         * @param blockchainLocalDataSource  the device storage data source
         * *
         * @return the [BlockchainStatsRepository] instance
         */
        @JvmStatic
        fun getInstance(
            blockchainRemoteDataSource: BlockchainStatsDataSource,
            blockchainLocalDataSource: BlockchainStatsDataSource
        ) =
            INSTANCE ?: synchronized(BlockchainStatsRepository::class.java) {
                INSTANCE ?: BlockchainStatsRepository(blockchainRemoteDataSource, blockchainLocalDataSource)
                    .also { INSTANCE = it }
            }


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
