package app.sample.blockchainsampleapp.blockchainstats.data.source.local

import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsDataSource

object BlockchainStatsLocalDataSource : BlockchainStatsDataSource {
    override fun saveBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        callback.onStatsNotAvailable(Throwable())
    }

    override fun fetchBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        callback.onStatsNotAvailable(Throwable())
    }

}