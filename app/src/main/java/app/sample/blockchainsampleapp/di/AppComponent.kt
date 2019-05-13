package app.sample.blockchainsampleapp.di

import android.content.SharedPreferences
import app.sample.blockchainsampleapp.blockchainstats.ui.BlockchainStatsActivity
import app.sample.blockchainsampleapp.network.RequestManager
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RequestManager::class])
interface AppComponent {
    fun inject(mainActivity: BlockchainStatsActivity)
    fun getSharedPrefs(): SharedPreferences

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(appModule: AppModule): Builder
    }
}