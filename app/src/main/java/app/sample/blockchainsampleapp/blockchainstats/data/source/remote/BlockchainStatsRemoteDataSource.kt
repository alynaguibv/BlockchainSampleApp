package app.sample.blockchainsampleapp.blockchainstats.data.source.remote

import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsDataSource
import app.sample.blockchainsampleapp.blockchainstats.request.FetchBlockchainStatsRequest
import app.sample.blockchainsampleapp.network.RequestManager

object BlockchainStatsRemoteDataSource : BlockchainStatsDataSource {
    override fun saveBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        callback.onStatsNotAvailable(Throwable())
    }

    override fun fetchBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        val blockchainStatsRequest = FetchBlockchainStatsRequest()
        with(RequestManager) {
            getInstance().start(blockchainStatsRequest)
            if (blockchainStatsRequest.response != null) {
                callback.onStatsFetched(blockchainStatsRequest.response!!.values)
            } else if (blockchainStatsRequest.baseErrorModel != null) {
                callback.onStatsNotAvailable(blockchainStatsRequest.baseErrorModel!!.throwable)
            }
        }
    }
}