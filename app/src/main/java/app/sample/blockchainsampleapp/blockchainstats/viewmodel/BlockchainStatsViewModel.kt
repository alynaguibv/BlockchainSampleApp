package app.sample.blockchainsampleapp.blockchainstats.viewmodel

import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsDataSource
import app.sample.blockchainsampleapp.blockchainstats.usecase.FetchBlockchainStatsUseCase
import de.n26.n26blockchain.base.common.presentation.BaseViewModel
import de.n26.n26blockchain.base.usecase.UseCase
import de.n26.n26blockchain.base.usecase.UseCaseHandler

class BlockchainStatsViewModel(
    private val useCaseHandler: UseCaseHandler,
    private val fetchBlockchainStatsUseCase: FetchBlockchainStatsUseCase
) : BaseViewModel() {
    fun fetchBlockchainStats(callback: BlockchainStatsDataSource.FetchStatsCallback) {
        val requestValue = FetchBlockchainStatsUseCase.RequestValues()
        useCaseHandler.execute(fetchBlockchainStatsUseCase, requestValue, object :
            UseCase.UseCaseCallback<FetchBlockchainStatsUseCase.ResponseValue> {
            override fun onSuccess(response: FetchBlockchainStatsUseCase.ResponseValue) {
                callback.onStatsFetched(response.chartPoints)
            }

            override fun onError(t: Throwable) {
                callback.onStatsNotAvailable(t)
            }
        })
    }
}