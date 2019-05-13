package app.sample.blockchainsampleapp

import app.sample.blockchainsampleapp.blockchainstats.data.source.BlockchainStatsRepository
import app.sample.blockchainsampleapp.blockchainstats.data.source.local.BlockchainStatsLocalDataSource
import app.sample.blockchainsampleapp.blockchainstats.data.source.remote.BlockchainStatsRemoteDataSource
import app.sample.blockchainsampleapp.blockchainstats.usecase.FetchBlockchainStatsUseCase
import de.n26.n26blockchain.base.usecase.UseCase
import de.n26.n26blockchain.base.usecase.UseCaseHandler
import de.n26.n26blockchain.base.usecase.UseCaseObserversScheduler
import de.n26.n26blockchain.base.usecase.UseCaseScheduler

object Injection {
    fun provideBlockchainStatsRepository(): BlockchainStatsRepository {
        return BlockchainStatsRepository.getInstance(BlockchainStatsRemoteDataSource,
            BlockchainStatsLocalDataSource)
    }

    fun provideFetchBlockchainStatsUseCase(): FetchBlockchainStatsUseCase{
        return FetchBlockchainStatsUseCase(provideBlockchainStatsRepository())
    }

    fun provideUseCaseHandler(): UseCaseHandler{
        val useCaseObserversScheduler = UseCaseObserversScheduler()
        return UseCaseHandler(useCaseObserversScheduler)
    }
}