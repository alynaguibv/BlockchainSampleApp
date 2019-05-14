package app.sample.blockchainsampleapp.blockchainstats.data.source.local

import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsDataSource

/*
* Block chain stats local data source
* */

object BlockchainStatsLocalDataSource : BlockchainStatsDataSource {
    override fun saveBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        callback.onStatsNotAvailable(Throwable())
    }

    override fun fetchBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        callback.onStatsNotAvailable(Throwable())
    }

}