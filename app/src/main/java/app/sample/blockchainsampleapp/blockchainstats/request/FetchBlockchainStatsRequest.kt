package app.sample.blockchainsampleapp.blockchainstats.request

import app.sample.blockchainsampleapp.blockchainstats.response.BlockchainStatsResponse
import de.n26.n26blockchain.base.network.request.BaseErrorModel
import de.n26.n26blockchain.base.network.request.BaseRequest

class FetchBlockchainStatsRequest : BaseRequest<BlockchainStatsResponse, BaseErrorModel>() {
    init {
        baseURL = "https://api.blockchain.info/"
        path = "charts/market-price?format=json"
    }
}