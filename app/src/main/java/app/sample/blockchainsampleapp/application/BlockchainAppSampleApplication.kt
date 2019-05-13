package app.sample.blockchainsampleapp.application

import android.app.Application
import app.sample.blockchainsampleapp.di.AppComponent
import app.sample.blockchainsampleapp.di.AppModule
import app.sample.blockchainsampleapp.di.DaggerAppComponent
import javax.inject.Inject


class BlockchainAppSampleApplication @Inject constructor() : Application() {

    private var appComponent: AppComponent? = null

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = initDaggerComponent()
    }

    fun initDaggerComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build();
    }
}