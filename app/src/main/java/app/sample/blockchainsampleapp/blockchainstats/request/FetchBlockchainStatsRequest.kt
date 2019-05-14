package app.sample.blockchainsampleapp.blockchainstats.request

import app.sample.blockchainsampleapp.blockchainstats.response.BlockchainStatsResponse
import app.sample.blockchainsampleapp.buildconstants.BuildConstants
import de.n26.n26blockchain.base.network.request.BaseErrorModel
import de.n26.n26blockchain.base.network.request.BaseRequest

class FetchBlockchainStatsRequest : BaseRequest<BlockchainStatsResponse, BaseErrorModel>() {
    init {
        baseURL = BuildConstants.BASE_URL
        path = "charts/market-price?format=json"
    }
}