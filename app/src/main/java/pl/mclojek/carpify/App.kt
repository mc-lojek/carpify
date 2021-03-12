package pl.mclojek.carpify

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import pl.mclojek.carpify.data.db.AppDatabase
import timber.log.Timber

class App: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        AppDatabase.setContext(this)
    }
}