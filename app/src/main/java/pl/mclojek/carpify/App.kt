package pl.mclojek.carpify

import android.app.Application
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        val kodein = Kodein {
            import(networkModule)
            import(repositoryModule)
        }
    }
}