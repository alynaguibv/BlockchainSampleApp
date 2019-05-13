package app.sample.blockchainsampleapp.blockchainstats.data.source

import app.sample.blockchainsampleapp.blockchainstats.response.ChartPoint

interface BlockchainStatsDataSource {

    interface FetchStatsCallback {

        fun onStatsFetched(chartPoints: List<ChartPoint>)

        fun onStatsNotAvailable(throwable: Throwable)
    }

    fun fetchBlockchainStats(callback: FetchStatsCallback)

    fun saveBlockchainStats(callback: FetchStatsCallback)
}