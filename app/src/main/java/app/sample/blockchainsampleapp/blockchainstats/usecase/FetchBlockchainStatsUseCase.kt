package app.sample.blockchainsampleapp.blockchainstats.usecase

import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsDataSource
import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsRepository
import app.sample.blockchainsampleapp.blockchainstats.response.ChartPoint
import de.n26.n26blockchain.base.usecase.UseCase

class FetchBlockchainStatsUseCase(private val blockchainStatsRepository: BlockchainStatsRepository) :
    UseCase<FetchBlockchainStatsUseCase.RequestValues, FetchBlockchainStatsUseCase.ResponseValue>() {
    override fun executeUseCase(requestValues: RequestValues?) {
        blockchainStatsRepository.fetchBlockchainStats(object : BlockchainStatsDataSource.FetchStatsCallback {
            override fun onStatsFetched(chartPoints: List<ChartPoint>) {
                val responseValue = ResponseValue(chartPoints)
                useCaseCallback!!.onSuccess(responseValue)
            }

            override fun onStatsNotAvailable(throwable: Throwable) {
                useCaseCallback!!.onError(throwable)
            }

        })
    }

    class RequestValues : UseCase.RequestValues
    class ResponseValue(val chartPoints : List<ChartPoint>) : UseCase.ResponseValue
}