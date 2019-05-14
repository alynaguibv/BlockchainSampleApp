package app.sample.blockchainsampleapp

import android.support.annotation.NonNull
import app.sample.blockchainsampleapp.blockchainstats.request.FetchBlockchainStatsRequest
import app.sample.blockchainsampleapp.blockchainstats.usecase.FetchBlockchainStatsUseCase
import app.sample.blockchainsampleapp.buildconstants.BuildConstants
import app.sample.blockchainsampleapp.network.RequestManager
import de.n26.n26blockchain.base.usecase.UseCase
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
class AppUnitTest {

    @Before
    fun setup() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { run { } }, false)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @Test
    fun assertFetchBlockchainStatsResponseIsNotNull() {
        val requestManager = RequestManager.getInstance()
        val fetchBlockchainStatsRequest = FetchBlockchainStatsRequest()
        requestManager.start(fetchBlockchainStatsRequest)
        assertNotNull(fetchBlockchainStatsRequest.response)
    }

    @Test
    fun assertFetchBlockchainStatsResponseIsNull() {
        val requestManager = RequestManager.getInstance()
        val fetchBlockchainStatsRequest = FetchBlockchainStatsRequest()
        fetchBlockchainStatsRequest.baseURL = "http://"
        requestManager.start(fetchBlockchainStatsRequest)
        Assert.assertNull(fetchBlockchainStatsRequest.response)
    }


    @Test
    fun assertChartDataNotNull() {
        val fetchBlockchainStatsRequest = FetchBlockchainStatsRequest()
        fetchBlockchainStatsRequest.baseURL = BuildConstants.BASE_URL
        val useCaseHandler = Injection.provideUseCaseHandler()
        val requestValue = FetchBlockchainStatsUseCase.RequestValues()
        useCaseHandler.execute(Injection.provideFetchBlockchainStatsUseCase(), requestValue, object :
            UseCase.UseCaseCallback<FetchBlockchainStatsUseCase.ResponseValue> {
            override fun onSuccess(response: FetchBlockchainStatsUseCase.ResponseValue) {
                assertNotNull(response.chartPoints)
            }

            override fun onError(t: Throwable) {

            }
        })
    }

    @Test
    fun assertChartDataIsNull() {
        val fetchBlockchainStatsRequest = FetchBlockchainStatsRequest()
        fetchBlockchainStatsRequest.baseURL = ""
        val useCaseHandler = Injection.provideUseCaseHandler()
        val requestValue = FetchBlockchainStatsUseCase.RequestValues()
        useCaseHandler.execute(Injection.provideFetchBlockchainStatsUseCase(), requestValue, object :
            UseCase.UseCaseCallback<FetchBlockchainStatsUseCase.ResponseValue> {
            override fun onSuccess(response: FetchBlockchainStatsUseCase.ResponseValue) {
                assertNotNull(response.chartPoints)
            }

            override fun onError(t: Throwable) {

            }
        })
    }

}