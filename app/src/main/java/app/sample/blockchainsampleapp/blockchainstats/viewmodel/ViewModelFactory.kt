package app.sample.blockchainsampleapp.blockchainstats.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import app.sample.blockchainsampleapp.Injection
import app.sample.blockchainsampleapp.blockchainstats.usecase.FetchBlockchainStatsUseCase
import de.n26.n26blockchain.base.usecase.UseCaseHandler

class ViewModelFactory private constructor(
    private val fetchBlockchainStatsUseCase: FetchBlockchainStatsUseCase,
    private val useCaseHandler: UseCaseHandler
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(BlockchainStatsViewModel::class.java) ->
                    BlockchainStatsViewModel(useCaseHandler, fetchBlockchainStatsUseCase)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        //@SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE ?: synchronized(
                ViewModelFactory::class.java
            ) {
                INSTANCE
                    ?: ViewModelFactory(
                        Injection.provideFetchBlockchainStatsUseCase(),
                        Injection.provideUseCaseHandler()
                    )
                        .also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
