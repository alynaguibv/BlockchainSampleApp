package app.sample.blockchainsampleapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.NonNull
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(@param:NonNull private val context: Context) {

    @Singleton
    @Provides
    @NonNull
    fun provideContext(): Context {
        return context
    }

    lateinit var application: Application

    fun AppModule(application: Application) {
        this.application = application
    }

    @Provides
    fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providePreferences(): SharedPreferences {
        return application.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    }
}